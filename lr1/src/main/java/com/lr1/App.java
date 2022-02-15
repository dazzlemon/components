package com.lr1;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * hello world.
 */
public final class App {
	private App() {}
	/**
	 * hello world.
	 * @param args empty
	 */
	public static void main(String[] args) {
		var logger = Logger.getAnonymousLogger();
		logger.log(Level.INFO, "Hello World!");
	}
}
