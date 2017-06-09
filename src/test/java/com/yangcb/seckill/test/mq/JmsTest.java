package com.yangcb.seckill.test.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Random;

/**
 * ${DESCRIPTION}
 *
 * @author yangcb
 * @create 2017-06-09 14:26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-mq.xml" })
public class JmsTest {


    @Autowired
    private JmsTemplate notifyJmsTemplate;
    @Autowired
    private ActiveMQQueue mQueue;


    @Test
    public  void sendMsg(){

        for (int i = 1; i <= 4; i++) {



                    for(int j=0;j<10;j++){


                        notifyJmsTemplate.send(mQueue, new MessageCreator() {
                            @Override
                            public Message createMessage(Session session) throws JMSException {
                                return session.createTextMessage("你好:"+Thread.currentThread().getName()+"的消息");
                            }
                        });
                    }




        }


    }






}
