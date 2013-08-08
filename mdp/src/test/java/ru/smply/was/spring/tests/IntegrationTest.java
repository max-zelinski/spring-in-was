package ru.smply.was.spring.tests;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/applicationContext.xml")
public class IntegrationTest {
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    Destination requestIn = new ActiveMQQueue("Request.IN");
    Destination requestOut = new ActiveMQQueue("Request.OUT");

    public IntegrationTest() throws Exception {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("jms/cf/MQ", connectionFactory);
        builder.bind("jms/queues/Request.IN", requestIn);
        builder.bind("jms/queues/Request.OUT", requestOut);
        builder.activate();
    }

    @Test
    public void contextSetupIsCorrect() {
    }

    @Test
    public void onRequestMDPSendsCorrectResponse() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.convertAndSend(requestIn, "Message");
        String response = (String)jmsTemplate.receiveAndConvert(requestOut);

        assertEquals("response: Message", response);
    }
}
