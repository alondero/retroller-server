package org.londero.retroller;

import org.eclipse.jetty.server.Server;
import org.londero.retroller.handler.RetrollerHandler;


/**
 * Bootstraps the app by deploying a Jetty container
 * 
 * @author Adam Londero
 */
public class JettyBootstrapper {

	public static void main(String[] args) {
		Server server = new Server(7778);
        try {
			server.setHandler(new RetrollerHandler());
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
