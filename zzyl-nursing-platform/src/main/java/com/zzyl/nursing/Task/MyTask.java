package com.zzyl.nursing.Task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class MyTask {
    
    // 每5秒执行一次
    //@Scheduled(cron = "0/5 * * * * ?")
    public void reportCurrentTime() {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
       log.info("【定时任务执行】当前时间: " + currentTime + " - 每5秒执行一次的任务正在运行...");
    }

}
