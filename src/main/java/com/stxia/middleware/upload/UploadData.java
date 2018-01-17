
/**
 * Project Name:com.stxia.middleware 
 * File Name:UploadData.java <br/><br/>  
 * Description: TODO
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:19:37 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware.upload;

import java.text.SimpleDateFormat;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * ClassName: UploadData <br/>
 * <br/>
 * Description: 
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
@Component
public class UploadData
{
//    private static Logger LOG = Logger.getLogger(UploadData.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH时mm分ss秒 ");
//
//    // 每天早上6点执行
//    //@Scheduled(cron="0/5 * *  * * ? ")
//    @Scheduled(initialDelay=10000,fixedRate=4000)
//    public void sendData()
//    {
//        System.out.println("Thread:"+Thread.currentThread()+"开始");
//        System.out.println("Thread:"+Thread.currentThread()+"---"+dateFormat.format(System.currentTimeMillis())+"--- 我正在执行sendData!");
//        try
//        {
//            Thread.sleep(6000);
//        }
//        catch (InterruptedException e)
//        {
//             e.printStackTrace();  
//        }
//        System.out.println("Thread:"+Thread.currentThread()+"结束");
//    }
//    
//    @Scheduled(cron="*/5 * * * * MON-FRI")
//    public void sendData2()
//    {
//        System.out.println("Thread2:"+Thread.currentThread()+"开始");
//        System.out.println("Thread2:"+Thread.currentThread()+"---"+dateFormat.format(System.currentTimeMillis())+"--- 我正在执行sendData2!");
//        try
//        {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e)
//        {
//             e.printStackTrace();  
//        }
//        System.out.println("Thread2:"+Thread.currentThread()+"结束");
//    }
//
//    public static void main(String[] args)
//    {
//        UploadData u = new UploadData();
//        u.sendData();
//    }
    @Async("secondExecutor")
    public void testAsync(String a){
      System.out.println("Thread:"+Thread.currentThread()+"开始");
      System.out.println("Thread:"+Thread.currentThread()+"---"+dateFormat.format(System.currentTimeMillis())+"--- 我正在执行sendData!"+a);
      try
      {
          Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
           e.printStackTrace();  
      }
      if("AAAA".equals(a)){
          throw new RuntimeException("666666666666666666666666666");
      }
      System.out.println("Thread:"+Thread.currentThread()+"结束");
    }
    
    @Async
    public Future<String> testAsync2(String a){
      System.out.println("Thread2:"+Thread.currentThread()+"开始");
      System.out.println("Thread2:"+Thread.currentThread()+"---"+dateFormat.format(System.currentTimeMillis())+"--- 我正在执行sendData2!"+a);
      try
      {
          Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
           e.printStackTrace();  
      }
      if("AAAA".equals(a)){
          throw new RuntimeException("22222222222222");
      }
      System.out.println("Thread2:"+Thread.currentThread()+"结束");
      //return CompletableFuture.completedFuture(a);
      return new AsyncResult<String>(a);
    }


}
