
/**
 * Project Name:com.stxia.middleware 
 * File Name:ScheduleConfig.java <br/><br/>  
 * Description: ScheduleConfig
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:57:56 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * ClassName: ScheduleConfig <br/>
 * <br/>
 * Description: ScheduleConfig
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer
{
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
    {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.initialize();
        //申请5个线程池给Schedule任务
        taskRegistrar.setScheduler(scheduler);
//        taskRegistrar.addFixedRateTask(new IntervalTask(
//            new Runnable() {
//                @Override
//                public void run() {
//                    myUploadData().sendData();
//                }
//            },
//            10000, 0));
    }
}
