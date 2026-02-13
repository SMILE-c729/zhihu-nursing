package com.zzyl.nursing.Task;

import com.zzyl.nursing.service.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class contractTask {
    @Autowired
    private ContractServiceImpl ContractServiceImpl;

    public void updateContractStatus() {
        System.out.println("定时修改合同状态");
        ContractServiceImpl.updateContractStatus();
        System.out.println("定时修改合同状态结束");
    }


}

