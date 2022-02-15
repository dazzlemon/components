package com.lr1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * helloworld rmi server interface.
 */
public interface Hello extends Remote {
	String sayHello() throws RemoteException;
}
