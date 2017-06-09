package com.yangcb.seckill.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-09 17:17
 **/

public class ConsumerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("接收线程"+Thread.currentThread().getName()+"接收消息:"+((TextMessage)message).getText());


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
