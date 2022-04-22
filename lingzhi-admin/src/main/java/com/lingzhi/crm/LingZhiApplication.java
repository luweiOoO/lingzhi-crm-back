package com.lingzhi.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author lingzhi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LingZhiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(LingZhiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  客户管理系统启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
