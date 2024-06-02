package com.ssafy.fitty.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.fitty.model.dao.*")
public class DBConfig {

}
