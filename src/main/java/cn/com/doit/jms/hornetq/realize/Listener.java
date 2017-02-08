package cn.com.doit.jms.hornetq.realize;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

public class Listener {
//	@Autowired
//	private ConnectionFactory connectionFactory;
//
//	@Bean
//	public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
//		return new ScheduledAnnotationBeanPostProcessor();
//	}
//
//	@Bean
//	public DefaultMessageListenerContainer messageListener() {
//		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//		container.setConnectionFactory(this.connectionFactory);
//		container.setDestinationName("GYQueue");
//		container.setMessageListener(new MessageListener() {
//			public void onMessage(Message message) {
//				try {
//					System.out.println(";;;;;;;;;;;;;;;;"+message.getBody(Object.class));
//				}
//				catch (JMSException ex) {
//					ex.printStackTrace();
//				}
//			}
//		});
//		return container;
//	}
//
}
