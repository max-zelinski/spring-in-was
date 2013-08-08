package ru.smply.was.spring.mdp;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

public class MDP {
    private JmsTemplate jmsTemplate;
    private Destination requestOut;

    @Required
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Required
    public void setRequestOut(Destination requestOut) {
        this.requestOut = requestOut;
    }

    public void onMessage(String message) {
        System.out.println("Message received: " + message);
        System.out.println("Sending reply");

        jmsTemplate.convertAndSend(requestOut, "response: " + message);
    }
}
