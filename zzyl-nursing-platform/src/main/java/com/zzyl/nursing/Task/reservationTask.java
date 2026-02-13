package com.zzyl.nursing.Task;

import com.zzyl.nursing.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class reservationTask {
    @Autowired
    private ReservationServiceImpl reservationService;

    public void updateReservationStatus() {
        System.out.println("定时修改预约状态");
        reservationService.cancelReservationByTime();
        System.out.println("定时修改预约状态结束");
    }
}
