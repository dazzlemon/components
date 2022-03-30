package com.lr2;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Producer extends HttpServlet {
	private Destination queue;
	Connection initConnection() throws NamingException, JMSException {
		Context ic = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
		queue = (Destination) ic.lookup("queue/tutorialQueue");
		return cf.createConnection();
	}

	@Override
	protected void doGet(
		HttpServletRequest req,
		HttpServletResponse res
	) throws ServletException, IOException {
		res.setContentType("text/html");
		var out = res.getWriter();
		out.println("<html><body>");
		out.println("Hello World!");
		out.println("</body></html>");
		out.close();

		// String text = req.getParameter("text") != null ? req.getParameter("text") : "Hello World";
		// try (Connection connection = initConnection()) {
		// 	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 	MessageProducer publisher = session.createProducer(queue);
		// 	connection.start();
		// 	TextMessage message = session.createTextMessage(text);
		// 	publisher.send(message);
		// } catch (NamingException | JMSException e) {
		// 	res.getWriter().println("Error while trying to send <" + text + "> message: " + e.getMessage());
		// }
		// res.getWriter().println("Message sent: " + text);
	}
}
