/**
 * Project Name:ems-srv-config 
 * File Name:TaskExecutorTest.java <br/><br/>  
 * Description: TODO
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Jan 2, 2018 3:34:59 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.upload;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * ClassName: TaskExecutorTest <br/>
 * <br/>
 * Description: TODO
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
public class TaskExecutorTest
{
    public static void main(String[] args) throws IOException
    {
        testThreadPoolTaskExecutor();
    }

    private static void testSimpleAsyncTaskExecutor()
    {
        SimpleAsyncTaskExecutor sexc = new SimpleAsyncTaskExecutor();
        //最多执行10个任务
        sexc.setConcurrencyLimit(10);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        sexc.setThreadFactory(threadFactory);
        for (int i = 0; i < 30; i++)
        {
            System.out.println("提交了task" + i);
            sexc.execute(new MessagePrinterTask("I am printing" + i));
        }

    }


    private static void testSyncTaskExecutor()
    {
        SyncTaskExecutor sexc = new SyncTaskExecutor();
        for (int i = 0; i < 5; i++)
        {
            System.out.println("提交了task" + i);
            sexc.execute(new MessagePrinterTask("I am printing" + i));
        }
    }

    private static void testConcurrentTaskExecutor()
    {
        ConcurrentTaskExecutor cexc = new ConcurrentTaskExecutor();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(19);
        ThreadPoolExecutor exc = new ThreadPoolExecutor(3, 30, 30000, TimeUnit.SECONDS, queue);
        cexc.setConcurrentExecutor(exc);
        for (int i = 0; i < 10; i++)
        {
            System.out.println("提交了task" + i);
            cexc.execute(new MessagePrinterTask("I am printing" + i));
        }
    }

    private static void testThreadPoolTaskExecutor()
    {
        RejectedExecutionHandler rejectionHandler = new RejectHandler();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolTaskExecutor exc = new ThreadPoolTaskExecutor();
        exc.setThreadFactory(threadFactory);
        exc.setRejectedExecutionHandler(rejectionHandler);
        
        exc.setQueueCapacity(3 );
        exc.setCorePoolSize(5);
        exc.setMaxPoolSize(7);
        exc.initialize();
        for (int i = 0; i < 11; i++)
        {
            System.out.println("提交了task" + i);
            exc.execute(new MessagePrinterTask("I am printing" + i));
        }
        exc.getThreadPoolExecutor().shutdown();
    }

    private static class MessagePrinterTask implements Runnable
    {
        private SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
        private String message;

        public MessagePrinterTask(String message)
        {
            this.message = message;
        }

        @Override
        public void run()
        {
            System.out.println(format.format(System.currentTimeMillis()) + "--Thread : " + Thread.currentThread() + "   " + message);
            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    private static class RejectHandler implements RejectedExecutionHandler
    {
        @Override
        public void rejectedExecution(Runnable r, java.util.concurrent.ThreadPoolExecutor executor)
        {
            MessagePrinterTask task = (MessagePrinterTask) r;
            throw new RejectedExecutionException("我满了," + task.message + "失败");
        }

    }
}
