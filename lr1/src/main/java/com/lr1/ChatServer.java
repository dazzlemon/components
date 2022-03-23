package com.lr1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * helloworld rmi server interface.
 */
public interface ChatServer extends Remote {
	/**
	 * this function registers userId on this server.
	 * @param id username
	 * @return "password" for the id or null if can't register such id(it's already registered for example)
	 * @throws RemoteException
	 */
	Integer registerId(String id) throws RemoteException;

	/**
	 * this function sends message for registered user.
	 * @param id username
	 * @param password returned from {@link registerId()}
	 * @return true if authorization data is correct and message was sent, false otherwise
	 * @throws RemoteException
	 */
	boolean sendMessage(String id, Integer password, String message) throws RemoteException;
}
