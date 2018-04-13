package com.dus.dusframework.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dus.dusframework"})
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class DusWebConfig { 

}
