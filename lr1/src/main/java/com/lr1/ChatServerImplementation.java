package com.lr1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.EqualsAndHashCode;

/**
 * helloworld rmi server implementation.
 */
@EqualsAndHashCode
public class ChatServerImplementation extends UnicastRemoteObject implements ChatServer {
	private Random random;
	private Map<String, Integer> users = new ConcurrentHashMap<>();
	private Logger logger;
	/**
	 */
	protected ChatServerImplementation(Random random, Logger logger) throws RemoteException {
		super();
		this.random = random;
		this.logger = logger;
	}

	@Override
	public Integer registerId(String id) {
		if (users.containsKey(id)) {
			return null;// already registered
		}
		var password = this.random.nextInt();
		this.users.put(id, password);
		return password;
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
			var random = SecureRandom.getInstanceStrong();
			var server = new ChatServerImplementation(random, logger);// on separate string for easier dbg
			Naming.rebind(name, server);
			logger.info("ready");
		} catch (RemoteException | MalformedURLException | NoSuchAlgorithmException e) {
			logger.throwing(name, "main", e);
		}
	}

	@Override
	public boolean sendMessage(String id, Integer password, String message) throws RemoteException {
		if (this.logger.isLoggable(Level.INFO) &&
		    this.users.containsKey(id) &&
		    users.get(id).equals(password)
		) {
			logger.info(
				MessageFormat.format("Received message \"{0}\" from user with id {1}", message, id));
			return true;
		}
		return false;
	}
}
