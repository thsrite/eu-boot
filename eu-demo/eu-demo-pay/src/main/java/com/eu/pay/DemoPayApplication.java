package com.eu.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "com.eu")
public class DemoPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPayApplication.class, args);
        log.info("============================ DEMO PAY * System startup completed ===================================================");
    }

}
