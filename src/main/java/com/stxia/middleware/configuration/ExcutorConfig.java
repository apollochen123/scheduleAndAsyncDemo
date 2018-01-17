
/**
 * Project Name:ScheduleTest 
 * File Name:ExcutorConfig.java <br/><br/>  
 * Description: ExcutorConfig
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Jan 17, 2018 2:21:40 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.configuration;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * ClassName: ExcutorConfig <br/>
 * <br/>
 * Description: ExcutorConfig
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
@Configuration
public class ExcutorConfig implements AsyncConfigurer
{

    /**
     * {@inheritDoc}
     * 
     * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncExecutor()
     */
    @Override
    public Executor getAsyncExecutor()
    {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setBeanName("myExecutor");
        executor.setPoolSize(5);
        executor.initialize();
        return executor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncUncaughtExceptionHandler()
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler()
    {
        return new AsyncUncaughtExceptionHandler()
        {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params)
            {
                System.out.println("methodName:"+method.getName()+"   Modifier"+method.getModifiers()+"  param:"+(String)params[0]);
                System.out.println("执行出错了！！！"+ex);
            }
        };
    }
    
    @Bean("secondExecutor")
    public Executor MyExecutor()
    {
        ThreadPoolTaskScheduler beanExecutor = new ThreadPoolTaskScheduler();
        beanExecutor.setBeanName("secondExecutor");
        beanExecutor.setPoolSize(5);
        beanExecutor.initialize();
        return beanExecutor;
    }
}
