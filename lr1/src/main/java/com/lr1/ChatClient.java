package com.lr1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.EqualsAndHashCode;

/**
 * rmi chat client.
 */
@EqualsAndHashCode
public final class ChatClient extends UnicastRemoteObject {
	private static final long serialVersionUID = 1L;
	protected ChatClient() throws RemoteException {
		super();
	}


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
			var stub = (ChatServer) Naming.lookup("Server");
			var sc = new Scanner(System.in);
			System.out.println("What username would you like to use?");
			var name = sc.nextLine();
			var password = stub.registerId(name);
			if (password == null) {
				System.out.println("Sorry this name is taken");
			} else {
				var exit = false;
				while (!exit) {
					System.out.println("Message to send to server:");
					var msg = sc.nextLine();
					stub.sendMessage(name, password, msg);

					System.out.println("Continue?(Y if yes / Anything else otherwise)");
					var ans = sc.nextLine();
					if (!ans.equals("Y")) {
						exit = true;
					}
				}
			}
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			logger.throwing("Client", "main", e);
		}
	}
}
