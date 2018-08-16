package com.syscxp.biz.listener.activiti;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-15.
 * @Description: 事件监听器.
 * ActivitiEventType，事件类型
 * onEvent()发生异常，返回给调用者的值
 * 如果api调用，则事务会回滚，所以监听器中的行为不是关键业务，则建议返回false
 * 需在processEngineConfiguration中设置eventListeners属性，可以对个别event单独配置监听器
 */
public class MyEventListener implements ActivitiEventListener {

    @Override
    public void onEvent(ActivitiEvent event) {
        switch (event.getType()) {

            case ENTITY_CREATED:
                System.out.println("A entity created!");
                break;

            case ENGINE_CLOSED:
                System.out.println("A entity closed...");
                break;

            default:
                System.out.println("Event received: " + event.getType());
        }
    }

    @Override
    public boolean isFailOnException() {
        // The logic in the onEvent method of this listener is not critical, exceptions
        // can be ignored if logging fails...
        return false;
    }
}