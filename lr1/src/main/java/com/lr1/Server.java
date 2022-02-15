package com.lr1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * helloworld rmi server implementation.
 */
public class Server extends UnicastRemoteObject implements Hello {
	protected Server() throws RemoteException {
		super();
	}

	public String sayHello() {
		return "Hello, world!";
	}

	/**
	 * run server.
	 * @param args empty
	 */
	public static void main(String[] args) throws SecurityException {
		var name = "Server";
		var logger = Logger.getLogger(name);
		var handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    logger.addHandler(handler);
    logger.setLevel(Level.ALL);

		try {
			var server = new Server();// on separate string for easier dbg
			Naming.rebind(name, server);
			logger.info("ready");
		} catch (RemoteException | MalformedURLException e) {
			logger.throwing(name, "main", e);
		}
	}
}
