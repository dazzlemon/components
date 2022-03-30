package com.lr2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.InitialContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.common.base.MoreObjects;

public class Producer extends HttpServlet {
	@Override
	protected void doGet(
		HttpServletRequest req, HttpServletResponse res
	) throws ServletException, IOException {
		var text = MoreObjects.firstNonNull(req.getParameter("text"), "Hello World");
		var out = res.getWriter();
		try {
			var ic = new InitialContext();
			var cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
			var q = (Queue) ic.lookup("queue/tutorialQueue");
			try (var context = cf.createContext()) {
				context.createProducer().send(q, text);
			}
		} catch (Exception e) {
			out.printf("Error while trying to send <%s> message: %s", text, e);
		}
	}
}
