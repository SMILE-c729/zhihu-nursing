package com.zzyl.hospital.Task;

import com.zzyl.hospital.service.IWarningRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WarningTask {

    @Autowired
    private IWarningRuleService warningRuleService;
    /*
    * 定时任务: 设备数据过滤
    * */
    public void vitalSignDataWarningFilter() {
        warningRuleService.warningFilter();
    }
}