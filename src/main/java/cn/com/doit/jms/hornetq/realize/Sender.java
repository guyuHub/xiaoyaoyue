package cn.com.doit.jms.hornetq.realize;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class Sender {
//
//	@Autowired
//	private JmsTemplate jmsTemplate;
//	
//// private ConnectionFactory connectionFactory;
////	
////	public JmsTemplate getJmsTemplate(){
////		JmsTemplate ss=new JmsTemplate(connectionFactory);
////	}
//	@Scheduled(fixedRate =5000)
//	public void send() {
//	    String message = "Timestamp from Server: " + System.currentTimeMillis();
//        System.out.println("Sending message: " + message);
//		this.jmsTemplate.convertAndSend("GYQueue", "Hello I am you farther,hq");
//	}
//
//	public JmsTemplate getJmsTemplate() {
//		return jmsTemplate;
//	}
//
//	public void setJmsTemplate(JmsTemplate jmsTemplate) {
//		this.jmsTemplate = jmsTemplate;
//	}
//
}