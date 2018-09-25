package com.syscxp.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "com.syscxp.biz.core")
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.activiti.spring.boot.SecurityAutoConfiguration.class
})
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

//    @Bean
//    public CommandLineRunner init(final MyService myService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                myService.createDemoUsers();
//            }
//        };
//
//    }
}
