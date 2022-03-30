package com.lr2;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.common.base.MoreObjects;

public class Producer extends HttpServlet {
	private Destination queue;
	Connection initConnection() throws NamingException, JMSException {
		var ic = new InitialContext();
		var cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
		queue = (Destination) ic.lookup("queue/tutorialQueue");
		return cf.createConnection();
	}

	@Override
	protected void doGet(
		HttpServletRequest req, HttpServletResponse res
	) throws ServletException, IOException {
		var text = MoreObjects.firstNonNull(req.getParameter("text"), "Hello World");
		var out = res.getWriter();
		try (var connection = initConnection()) {
			var session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			var publisher = session.createProducer(queue);
			connection.start();
			var message = session.createTextMessage(text);
			publisher.send(message);
			out.printf("Message sent: %s", text);
		} catch (NamingException | JMSException e) {
			out.printf("Error while trying to send <%s> message: %s", text, e.getMessage());
		}
	}
}
