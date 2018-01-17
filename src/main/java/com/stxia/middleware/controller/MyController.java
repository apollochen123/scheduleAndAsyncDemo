
  
 /**
  * Project Name:ScheduleTest 
  * File Name:MyController.java <br/><br/>  
  * Description: TODO
  * Copyright: Copyright (c) 2017 
  * Company:SAP
  * 
  * @author SAP
  * @date Jan 16, 2018 11:10:27 AM
  * @version 
  * @see
  * @since 
  */
  package com.stxia.middleware.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stxia.middleware.upload.UploadData;

/**
  * ClassName: MyController <br/><br/> 
  * Description: TODO
  * @author SAP
  * @version 
  * @see
  * @since 
  */
@RestController
public class MyController
{
    @Autowired
    private UploadData uploadData;
    @RequestMapping("/testAsync")
    public String hello(@PathParam("a") String a){
        uploadData.testAsync(a);
        return "OK";
    }
    @RequestMapping("/testAsync2")
    public String hello2(@PathParam("a") String a){
        Future<String> f = uploadData.testAsync2(a);
        try
        {
            System.out.println(f.get());
        }
        catch (InterruptedException | ExecutionException e)
        {
             e.printStackTrace();  
        }
        return "OK2";
    }
}
 