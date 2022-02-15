package com.lr1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * helloworld rmi client.
 */
public final class Client {
	private Client() {}

	/**
	 * run client.
	 * @param args empty
	 */
	public static void main(String[] args) {
		var logger = Logger.getLogger("Client");
		var handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    logger.addHandler(handler);
    logger.setLevel(Level.ALL);
		try {
			var stub = (Hello) Naming.lookup("Server");
			var response = stub.sayHello();
			logger.info(() -> MessageFormat.format("response: {0}", response));
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			logger.throwing("Client", "main", e);
		}
	}
}
