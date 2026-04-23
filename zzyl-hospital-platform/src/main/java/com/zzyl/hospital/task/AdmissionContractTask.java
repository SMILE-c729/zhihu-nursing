package com.zzyl.hospital.Task;

import com.zzyl.hospital.service.IAdmissionContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdmissionContractTask {
    @Autowired
    private IAdmissionContractService admissionContractService;

    public void updateAdmissionContractStatus() {
        log.info("开始更新合同状态");
        admissionContractService.updateAdmissionContractStatus();
        log.info("更新合同状态结束");
    }
}
