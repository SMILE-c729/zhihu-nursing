package com.zzyl.hospital.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.hospital.config.WebSocketServer;
import com.zzyl.hospital.domain.WarningData;
import com.zzyl.hospital.domain.VitalSignData;
import com.zzyl.hospital.mapper.MonitoringDeviceMapper;
import com.zzyl.hospital.service.IWarningDataService;
import com.zzyl.hospital.vo.WarningNotifyVo;
import com.zzyl.system.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.WarningRuleMapper;
import com.zzyl.hospital.domain.WarningRule;
import com.zzyl.hospital.service.IWarningRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 预警规则功能Service业务层处理
 *
 * @author alexis
 * @date 2026-02-24
 */
@Service
@Slf4j
public class WarningRuleServiceImpl extends ServiceImpl<WarningRuleMapper, WarningRule> implements IWarningRuleService {
    @Autowired
    private WarningRuleMapper warningRuleMapper;

    /**
     * 查询预警规则功能
     *
     * @param id 预警规则功能主键
     * @return 预警规则功能
     */
    @Override
    public WarningRule selectWarningRuleById(Long id) {
        return getById(id);
    }

    /**
     * 查询预警规则功能列表
     *
     * @param warningRule 预警规则功能
     * @return 预警规则功能
     */
    @Override
    public List<WarningRule> selectWarningRuleList(WarningRule warningRule) {
        return warningRuleMapper.selectWarningRuleList(warningRule);
    }

    /**
     * 新增预警规则功能
     *
     * @param warningRule 预警规则功能
     * @return 结果
     */
    @Override
    public int insertWarningRule(WarningRule warningRule) {
        return save(warningRule) ? 1 : 0;
    }

    /**
     * 修改预警规则功能
     *
     * @param warningRule 预警规则功能
     * @return 结果
     */
    @Override
    public int updateWarningRule(WarningRule warningRule) {
        return updateById(warningRule) ? 1 : 0;
    }

    /**
     * 批量删除预警规则功能
     *
     * @param ids 需要删除的预警规则功能主键
     * @return 结果
     */
    @Override
    public int deleteWarningRuleByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除预警规则功能信息
     *
     * @param id 预警规则功能主键
     * @return 结果
     */
    @Override
    public int deleteWarningRuleById(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private MonitoringDeviceMapper monitoringDeviceMapper;

    @Autowired
    private IWarningDataService warningDataService;

    @Value("${warning.monitoringDeviceMaintainerRole}")
    private String monitoringDeviceMaintainerRole;

    @Value("${warning.managerRole}")
    private String managerRole;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 预警过滤
     */
    @Override
    public void warningFilter() {
        // 查询所有规则，遍历规则
        long count = count(Wrappers.<WarningRule>lambdaQuery().eq(WarningRule::getStatus, 1));
        if (count <= 0) {
            return;
        }
        // 查询所有上报的数据
        List<Object> values = redisTemplate.opsForHash().values(CacheConstants.IOT_MONITORING_DEVICE_LAST_DATA);
        if (CollUtil.isEmpty(values)) {
            return;
        }
        // 解析上报的数据
        List<VitalSignData> vitalSignDatas = new ArrayList<>();
        // v.toString()将Object转换为String
        values.forEach(v -> vitalSignDatas.addAll(JSONUtil.toList(v.toString(), VitalSignData.class)));

        // 遍历预警数据，逐条处理
        vitalSignDatas.forEach(d -> warningFilter(d));
    }

    /**
     * 逐条过滤预警数据
     *
     * @param vitalSignData 设备数据
     */
    private void warningFilter(VitalSignData vitalSignData) {
        // 判断当前上报的数据是否超过了1分钟
        LocalDateTime alarmTime = vitalSignData.getAlarmTime();
        //计算插值
        long between = LocalDateTimeUtil.between(alarmTime, LocalDateTime.now(), ChronoUnit.SECONDS);
        if (between > 60) {
            return;
        }
        // 查询所有的该产品规则和该物模型的规则（为当前设备数据查找匹配的预警规则）
        //1.查询通用产品规则(匹配当前设备下同一功能的所有产品)
        List<WarningRule> allRules = list(Wrappers.<WarningRule>lambdaQuery()
                .eq(WarningRule::getProductKey, vitalSignData.getProductKey())
                .eq(WarningRule::getIotId, "-1")
                .eq(WarningRule::getFunctionId, vitalSignData.getFunctionId())
                .eq(WarningRule::getStatus, 1));
        //2.查询特定设备规则
        List<WarningRule> iotIdRules = list(Wrappers.<WarningRule>lambdaQuery()
                .eq(WarningRule::getProductKey, vitalSignData.getProductKey())//产品匹配
                .eq(WarningRule::getIotId, vitalSignData.getIotId())
                .eq(WarningRule::getFunctionId, vitalSignData.getFunctionId())
                .eq(WarningRule::getStatus, 1));
        // 合并
        Collection<WarningRule> allArertRules = CollUtil.addAll(allRules, iotIdRules);
        // 如果为空，则中断
        if (CollUtil.isEmpty(allArertRules)) {
            return;
        }
        // 按照过滤规则和上报的数据进行匹配
        allArertRules.forEach(warningRule -> vitalSignDataAlarmHandler(warningRule, vitalSignData));

    }

    /**
     * 过滤数据是否触发预警规则
     *
     * @param rule 表示匹配的预警规则
     * @param vitalSignData 设备数据
     */
    private void vitalSignDataAlarmHandler(WarningRule rule, VitalSignData vitalSignData) {
        // 判断上报时间是否在规则的生效时段内 00:00:00~23:59:59
        String[] split = rule.getWarningEffectivePeriod().split("~");
        LocalTime startTime = LocalTime.parse(split[0]);
        LocalTime endTime = LocalTime.parse(split[1]);
        // 获取上报时间
        LocalTime time = LocalDateTimeUtil.of(vitalSignData.getAlarmTime()).toLocalTime();
        // 不在上报时间内，则结束请求
        if (time.isBefore(startTime) || time.isAfter(endTime)) {
            return;
        }
        // 获取IOTID
        String iotId = vitalSignData.getIotId();
        // 统计次数的key
        String aggCountKey = CacheConstants.WARNING_TRIGGER_COUNT_PREFIX + iotId + ":" + vitalSignData.getFunctionId() + ":" + rule.getId();

        // 数据对比，上报的数据与规则中的阈值进行对比
        // 两个参数x,y(参数有顺序要求，左边是上报的数据，后边是规则的数据)  x==y 返回0  x>y 返回大于0  x<y 返回小于0的数值
        int compare = NumberUtil.compare(Double.valueOf(vitalSignData.getDataValue()), rule.getValue());
        if ((rule.getOperator().equals(">=") && compare >= 0) || (rule.getOperator().equals("<") && compare < 0)) {
            log.info("当前上报的数据符合规则异常");
        } else {
            // 正常的数据
            redisTemplate.delete(aggCountKey);
            return;
        }
        // 异常的数据会走到这里
        // 判断是否在沉默周期内
        String silentKey = CacheConstants.WARNING_SILENT_PREFIX + iotId + ":" + vitalSignData.getFunctionId() + ":" + rule.getId();
        String silentData = redisTemplate.opsForValue().get(silentKey);
        if (StringUtils.isNotEmpty(silentData)) {
            return;
        }
        // 持续周期的逻辑
        String aggData = redisTemplate.opsForValue().get(aggCountKey);
        int count = StringUtils.isEmpty(aggData) ? 1 : Integer.parseInt(aggData) + 1;
        // 如果count与持续周期的值相等，则触发预警
        if (ObjectUtil.notEqual(count, rule.getDuration())) {
            // 不相等
            redisTemplate.opsForValue().set(aggCountKey, count + "");
            return;
        }
        //到了这一步相当于次数达到了预期值并且是连续执行
        // 删除redis的预警数据
        redisTemplate.delete(aggCountKey);
        // 存储数据到沉默周期，设置一个过期时间，规则中的沉默周期
        redisTemplate.opsForValue().set(silentKey, "1", rule.getWarningSilentPeriod(), TimeUnit.MINUTES);

        // 预警数据，需要找到对应的人
        List<Long> userIds = new ArrayList<>();
        if (rule.getWarningDataType().equals(0)) {
            // 患者异常数据
            if (vitalSignData.getLocationType().equals(0)) {
                // 说明是预警手表，直接可以找到患者的id,通过患者ID,找到对应的责任护士
                userIds = monitoringDeviceMapper.selectNurseIdsByIotIdWithPatient(iotId);
            } else if (vitalSignData.getLocationType().equals(1) && vitalSignData.getPhysicalLocationType().equals(2)) {
                // 说明是病床设备，可以通过病床id找到患者，通过患者ID,找到对应的责任护士
                userIds = monitoringDeviceMapper.selectNurseIdsByIotIdWithWardBed(iotId);
            }
        } else {//表示是烟雾预警器
            // 设备异常数据，找设备维修员，或者是行政人员
            userIds = userRoleMapper.selectUserIdByRoleName(monitoringDeviceMaintainerRole);
        }
        // 不论是哪种情况，都要通知超级管理员
        List<Long> managerIds = userRoleMapper.selectUserIdByRoleName(managerRole);
        Collection<Long> allUserIds = CollUtil.addAll(userIds, managerIds);
        // 去重
        allUserIds = CollUtil.distinct(allUserIds);

        // 批量保存异常数据
        List<WarningData> warningDataList = insertWarningData(allUserIds, vitalSignData, rule);

        // websocket推送消息
        webSocketNotity(warningDataList.get(0), rule, allUserIds);
    }

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * websocket推送消息
     * @param warningData
     * @param rule
     * @param allUserIds
     */
    private void webSocketNotity(WarningData warningData, WarningRule rule, Collection<Long> allUserIds) {

        //属性拷贝
        WarningNotifyVo warningNotifyVo = BeanUtil.toBean(warningData, WarningNotifyVo.class);
        warningNotifyVo.setAccessLocation(warningData.getRemark());
        warningNotifyVo.setFunctionName(rule.getFunctionName());
        warningNotifyVo.setWarningDataType(rule.getWarningDataType());
        warningNotifyVo.setNotifyType(1);
        // 向指定的人推送消息
        webSocketServer.sendMessageToConsumer(warningNotifyVo, allUserIds);

    }



    /**
     * 保存预警数据
     *
     * @param allUserIds
     * @param rule
     * @param vitalSignData
     */
    private List<WarningData> insertWarningData(Collection<Long> allUserIds, VitalSignData vitalSignData, WarningRule rule) {        // 对象拷贝
        WarningData warningData = BeanUtil.toBean(vitalSignData, WarningData.class);
        warningData.setWarningRuleId(rule.getId());
        // 心率<60,持续3个周期就预警
        String warningReason = CharSequenceUtil.format("{}{}{},持续{}个周期就预警", rule.getFunctionName(), rule.getOperator(), rule.getValue(), rule.getDuration());
        warningData.setWarningReason(warningReason);
        warningData.setStatus(0);
        warningData.setType(rule.getWarningDataType());
        // 遍历allUserIds
        List<WarningData> list = allUserIds.stream().map(userId -> {
            WarningData dbWarningData = BeanUtil.toBean(warningData, WarningData.class);
            dbWarningData.setUserId(userId);
            dbWarningData.setId(null);
            return dbWarningData;
        }).collect(Collectors.toList());

        // 批量保存
        warningDataService.saveBatch(list);
        return list;
    }
}
