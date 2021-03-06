
/**
 * Project Name:com.stxia.middleware 
 * File Name:MiddlewareMain.java <br/><br/>  
 * Description: MiddlewareMain
 * Copyright: Copyright (c) 2017 
 * Company:SAP
 * 
 * @author SAP
 * @date Nov 21, 2017 3:11:15 PM
 * @version 
 * @see
 * @since 
 */
package com.stxia.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: MiddlewareMain <br/>
 * <br/>
 * Description: MiddlewareMain
 * 
 * @author SAP
 * @version
 * @see
 * @since
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MiddlewareMain
{
    public static void main(String[] args)
    {
        SpringApplication.run(MiddlewareMain.class, args);
    }
}
