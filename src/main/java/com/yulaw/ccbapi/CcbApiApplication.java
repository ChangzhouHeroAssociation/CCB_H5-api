package com.yulaw.ccbapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.yulaw.ccbapi.model.dao")
@EnableCaching
public class CcbApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcbApiApplication.class, args);
    }

}
