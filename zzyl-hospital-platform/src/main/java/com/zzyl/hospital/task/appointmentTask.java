package com.zzyl.hospital.Task;

import com.zzyl.hospital.service.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class appointmentTask {
    @Autowired
    private AppointmentServiceImpl appointmentService;

    public void updateAppointmentStatus() {
        System.out.println("定时修改预约状态");
        appointmentService.cancelAppointmentByTime();
        System.out.println("定时修改预约状态结束");
    }
}
