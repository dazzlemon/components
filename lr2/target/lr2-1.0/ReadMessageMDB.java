package com.lr2;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;

@MessageDriven(activationConfig = {
	@ActivationConfigProperty(
		propertyName = "destination",
		propertyValue = "tutorialQueue"),
	@ActivationConfigProperty(
		propertyName = "destinationType",
		propertyValue = "javax.jms.Queue")
})
@Log
public class ReadMessageMDB implements MessageListener {
	public void onMessage(Message message) {
		var textMessage = (TextMessage) message;
		try {
			log.info("Message received: " + textMessage.getText());
		} catch (JMSException e) {
			log.throwing("ReadMessageMDB", "onMessage", e);
		}
	}
}