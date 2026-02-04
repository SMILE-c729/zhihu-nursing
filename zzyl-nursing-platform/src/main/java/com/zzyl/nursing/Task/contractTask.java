package com.zzyl.nursing.Task;
import com.zzyl.nursing.service.IContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* 定时任务：合同状态修改
* */
@Component
@Slf4j
public class contractTask {
    @Autowired
    private IContractService contractService;
    public void updateContractStatus() {
        log.info("开始执行合同状态修改");
        contractService.updateContractStatus();
        log.info("合同状态修改完成");
    }
}
