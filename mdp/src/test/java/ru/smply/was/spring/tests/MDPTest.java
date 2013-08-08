package ru.smply.was.spring.tests;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import ru.smply.was.spring.mdp.MDP;

import javax.jms.Destination;

import static org.mockito.Mockito.*;

public class MDPTest {
    @Test
    public void onRequestReplyCorrectly() {
        JmsTemplate jmsTemplate = mock(JmsTemplate.class);
        Destination requestOut = mock(Destination.class);

        MDP mdp = new MDP();
        mdp.setJmsTemplate(jmsTemplate);
        mdp.setRequestOut(requestOut);

        mdp.onMessage("request");

        verify(jmsTemplate, times(1)).convertAndSend(requestOut, "response: request");
    }
}
