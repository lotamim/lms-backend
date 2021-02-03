package com.newgen.lms.common;
/*
 Created by : Md.Mehedi Hasan Tamim
 Note:Plz not modified it without any permission :::
 Time : 08/01/2021 10:11 PM
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobScheduler {
    /*
      1000*60*5 = 30,0000 ==> 5 mint
      @Scheduled(cron ="0 1 1 * * ?");
     */
    private static final Logger log = LoggerFactory.getLogger(JobScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
