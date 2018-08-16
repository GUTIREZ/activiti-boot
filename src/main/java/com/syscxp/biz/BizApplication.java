package com.syscxp.biz;

import com.syscxp.biz.service.MyService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
public class BizApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizApplication.class, args);

    }

    // 多数据源配置（不能自动配置流程）
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.biz")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder
//                .create()
//                .url("jdbc:mysql://localhost:3306/biz?useUnicode=yes&characterEncoding=UTF-8&useSSL=false")
//                .username("root")
//                .password("123456")
//                .driverClassName("com.mysql.jdbc.Driver")
//                .build();
//    }

    @Bean
    public CommandLineRunner init(final MyService myService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                myService.createDemoUsers();
            }
        };

    }
}
