package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zzyl.common.config.RuoYiConfig;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.utils.PDFUtil;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.common.utils.file.FileUtils;
import com.zzyl.oss.AliyunOSSOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.PatientAssessment;
import com.zzyl.hospital.service.IPatientAssessmentService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 入院评估Controller
 *
 * @author alexis
 * @date 2026-02-06
 */
@Api(tags = "入院评估管理")
@RestController
@RequestMapping("/hospital/patientAssessment")
public class PatientAssessmentController extends BaseController {
    @Autowired
    private IPatientAssessmentService patientAssessmentService;
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 查询入院评估列表
     */
    @ApiOperation("查询入院评估列表")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:list')")
    @GetMapping("/list")
    public TableDataInfo<List<PatientAssessment>> list(@ApiParam("查询条件对象") PatientAssessment patientAssessment) {
        startPage();
        List<PatientAssessment> list = patientAssessmentService.selectPatientAssessmentList(patientAssessment);
        return getDataTable(list);
    }

    /**
     * 导出入院评估列表
     */
    @ApiOperation("导出入院评估列表")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:export')")
    @Log(title = "入院评估", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, PatientAssessment patientAssessment) {
        List<PatientAssessment> list = patientAssessmentService.selectPatientAssessmentList(patientAssessment);
        ExcelUtil<PatientAssessment> util = new ExcelUtil<PatientAssessment>(PatientAssessment.class);
        util.exportExcel(response, list, "入院评估数据");
    }

    /**
     * 获取入院评估详细信息
     */
    @ApiOperation("获取入院评估详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:query')")
    @GetMapping(value = "/{id}")
    public R<PatientAssessment> getInfo(@PathVariable("id") @ApiParam("入院评估ID") Long id) {
        return R.ok(patientAssessmentService.selectPatientAssessmentById(id));
    }

    /**
     * 新增入院评估
     */
    @ApiOperation("新增入院评估")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:add')")
    @Log(title = "入院评估", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的入院评估对象") PatientAssessment patientAssessment) {
        Long result = patientAssessmentService.insertPatientAssessment(patientAssessment);
        return success(result);
    }

    /**
     * 修改入院评估
     */
    @ApiOperation("修改入院评估")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:edit')")
    @Log(title = "入院评估", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的入院评估对象") PatientAssessment patientAssessment) {
        return toAjax(patientAssessmentService.updatePatientAssessment(patientAssessment));
    }

    /**
     * 删除入院评估
     */
    @ApiOperation("删除入院评估")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:remove')")
    @Log(title = "入院评估", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的入院评估ID") Long[] ids) {
        return toAjax(patientAssessmentService.deletePatientAssessmentByIds(ids));
    }

    /**
     * 上传体检报告
     */
    @ApiOperation("上传体检报告")
    @PreAuthorize("@ss.hasPermi('hospital:patientAssessment:upload')")
    @Log(title = "入院评估", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult upload(@ApiParam("身份证号") @NotBlank(message = "身份证号不能为空") String idCardNo,
                           @ApiParam("体检报告文件") @NotNull(message = "体检报告文件不能为空") MultipartFile file) {
        try {
            // 参数校验
            if (StringUtils.isNull(idCardNo) ||
                    StringUtils.isEmpty(idCardNo) ||
                    "null".equals(idCardNo)) {
                return AjaxResult.error("身份证号不能为空");
            }
            if (file == null || file.isEmpty()) {
                return AjaxResult.error("体检报告文件不能为空");
            }
            
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            //String fileName = FileUploadUtils.upload(filePath, file);

            String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
            // 读取PDF内容
            String content = PDFUtil.pdfToString(file.getInputStream());
            // 存入缓存
            redisTemplate.opsForHash().put("healthReport", idCardNo, content);
            //  String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", url);
            ajax.put("newFileName", FileUtils.getName(url));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
