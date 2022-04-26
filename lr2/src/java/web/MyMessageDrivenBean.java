package web;

import jakarta.ejb.MessageDriven;
import jakarta.ejb.ActivationConfigProperty;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.logging.Level;

import lombok.extern.java.Log;

/**
 *
 * @author dazzlemon
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "destination",
        propertyValue = "myDestinationResourcePhysical"),
    @ActivationConfigProperty(
        propertyName = "destinationType",
        propertyValue = "jakarta.jms.Queue")
})
@Log
public class MyMessageDrivenBean implements MessageListener {
    @Override
    public void onMessage(Message message) {
        var textMessage = (TextMessage) message; 
        try {
            log.log(
                Level.INFO, "Message received: \"{0}\"", textMessage.getText());
        } catch (JMSException e) {
            log.throwing("MyMessageDrivenBean", "onMessage", e);
        }
    }
}
