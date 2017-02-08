package cn.com.doit.z.test.jms.hornetq;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.client.impl.ClientSessionFactoryImpl;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
   //疑问jms jdni 如何查看hornetq队列状态  nginx 通过spart过滤黑IP maven
//hornetq学习1：通过jms+JDNI方式访问队列
//Message Persistence:如果某消息 使用的是持久传输，则该消息具有 Persistence性质，否则不是。
//Durability of messages:持久订阅和非持久订阅是针对Topic而言的，不是针对Queue的。
public class HornetqStudyOne {
	private InitialContext  jms;
	Hashtable<String, String> icEnv = null;
	private MessageProducer producer ;//消息生产者
	private  MessageConsumer consumer;//消息消费者
	private Session session;//jms会话
	private Connection connection;
	private ConnectionFactory  zero;
	private   Queue emailQueue;
// --@Before--
	public void config() throws NamingException, JMSException{
		System.out.println("start time:"+new Date());
		icEnv= new Hashtable<String,String>(3);
		icEnv.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
		icEnv.put("java.naming.provider.url", "jnp://192.168.49.131:1099");
		icEnv.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		 jms=new InitialContext(icEnv);
		 ConnectionFactory cf = (ConnectionFactory)jms.lookup("/ConnectionFactory");
		  emailQueue = (Queue)jms.lookup("/queue/EmailProduct");
		  connection = cf.createConnection();
		 session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		  producer = session.createProducer(emailQueue);
		  consumer = session.createConsumer(emailQueue);
		 connection.start();
			System.out.println("end time:"+new Date()); 
	}
	//疑问，未设置持久化，为何可以消费
	@Before
	public void configWithOutJDNI() throws NamingException, JMSException{
		System.out.println("start time:"+new Date());
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("host", "192.168.49.131");
		 map.put("port", 5445);
		TransportConfiguration transportConfiguration = new
		TransportConfiguration(NettyConnectorFactory.class.getName(),map);
		zero =HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF,transportConfiguration);
	 //   ClientSessionFactory client=new ClientSessionFactoryImpl(null, transportConfiguration, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null);
	     HornetQJMSConnectionFactory cf=(HornetQJMSConnectionFactory) zero;
	     cf.setConnectionTTL(1000L);//设置连接超时时间
	      emailQueue = HornetQJMSClient.createQueue("EmailProduct");
		  connection = cf.createConnection();
		  //持久化订阅
		  connection.setClientID("oopp");
		 session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		 connection.start();
			System.out.println("end time:"+new Date()); 
	}
	@Test
	public void pruduct(){
		System.out.println("pruduct start time:"+new Date()); 
		  try {
			producer = session.createProducer(emailQueue);
			TextMessage message = session.createTextMessage("This is an uggdd uupp");
			producer.send(message);
			producer.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pruduct end time:"+new Date()); 
	}
	@Test
	public void consumer() {
		System.out.println("consumer start time:"+new Date()); 
		  try {
			consumer = session.createConsumer(emailQueue);	
		TextMessage receivedMessage = (TextMessage)consumer.receive();	
		System.out.println("Got order: " + receivedMessage.getText());
		consumer.close();
		  } catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("consumer end time:"+new Date()); 
	}
	@After
	public void close(){
		try {
			session.close();
		connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("close..........");
	}
private void mian() {
}

}
