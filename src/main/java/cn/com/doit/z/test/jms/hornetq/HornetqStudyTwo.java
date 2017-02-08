package cn.com.doit.z.test.jms.hornetq;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientConsumer;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//hornetq学习2：通过Hornetq内核api方式访问队列 
//疑问：address配置
public class HornetqStudyTwo {
	private InitialContext  jms;
	Hashtable<String, String> icEnv = null;
	private MessageProducer producer ;//消息生产者
	private  MessageConsumer consumer;//消息消费者
	private Session session;//jms会话
	private Connection connection;
	private ServerLocator locator;
	@Before
	public void config() throws Exception{
		System.out.println("start time:"+new Date());
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("host", "192.168.49.131");
		 map.put("port", 5445);
		locator=HornetQClient.createServerLocatorWithoutHA(new
				TransportConfiguration(NettyConnectorFactory.class.getName(),map));
		ClientSessionFactory factory = locator.createSessionFactory();
		ClientSession session = factory.createSession();
		session.createQueue("example", "example", true);
		ClientProducer producer = session.createProducer("example");
		ClientMessage message = session.createMessage(true);
		message.getBodyBuffer().writeString("Hello");
		producer.send(message);
		session.start();
		ClientConsumer consumer = session.createConsumer("example");
		ClientMessage msgReceived = consumer.receive();
		System.out.println("message = " + msgReceived.getBodyBuffer().readString());
		session.close();
			System.out.println("end time:"+new Date()); 
	}
	@Test
	public void pruduct() throws JMSException{
		System.out.println("pruduct start time:"+new Date()); 
		TextMessage message = session.createTextMessage("This is an lllppp");
		producer.send(message);
		producer.close();
		System.out.println("pruduct end time:"+new Date()); 
	}
	@Test
	public void consumer() throws JMSException{
		System.out.println("consumer start time:"+new Date()); 
		TextMessage receivedMessage = (TextMessage)consumer.receive();
		consumer.close();
		System.out.println("Got order: " + receivedMessage.getText());
		System.out.println("consumer end time:"+new Date()); 
	}
	@After
	public void close() throws NamingException, JMSException{
		session.close();
		connection.close();
	}
private void mian() {
}
}
