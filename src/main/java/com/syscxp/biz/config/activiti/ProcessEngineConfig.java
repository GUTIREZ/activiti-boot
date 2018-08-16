package com.syscxp.biz.config.activiti;

import com.syscxp.biz.interceptor.avtiviti.InterceptorA;
import com.syscxp.biz.interceptor.avtiviti.InterceptorB;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-15.
 * @Description: .
 */

@Configuration
public class ProcessEngineConfig extends ProcessEngineConfigurationImpl {
    @Override
    public CommandInterceptor createTransactionInterceptor() {
// 不实现事务拦截器
        return null;
    }

    /**
     * 重写初始化命令拦截器方法
     * TODO: 拦截器未生效
     */
    @Override
    public void initCommandInterceptors() {
// 为父类的命令集合添加拦截器
        customPreCommandInterceptors = new ArrayList<CommandInterceptor>();
// 依次将A 和B 两个拦截器加入集合（责任链）
        customPreCommandInterceptors.add(new InterceptorA());
        customPreCommandInterceptors.add(new InterceptorB());
// 再调用父类的实始化方法
        super.initCommandInterceptors();
    }
}