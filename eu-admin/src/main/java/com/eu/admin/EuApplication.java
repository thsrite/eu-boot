package com.eu.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 */
@Slf4j
@MapperScan(basePackages = "com.eu.**.mapper")
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "com.eu")
public class EuApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuApplication.class, args);
        log.info("============================ EU * System startup completed ===================================================");
    }

}
