package com.syscxp.biz.config.activiti;

import com.syscxp.biz.interceptor.avtiviti.InterceptorA;
import com.syscxp.biz.interceptor.avtiviti.InterceptorB;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.ArrayList;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-15.
 * @Description: .
 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

}
