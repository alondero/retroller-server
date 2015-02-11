package org.londero.retroller.handler;

import static java.awt.event.KeyEvent.getKeyText;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.londero.retroller.model.command.Command;

/**
 * Handles incoming requests from a Retroller Client and issues keyboard commands
 * @author Adam Londero
 */
public class RetrollerHandler extends AbstractHandler {

	private Robot robot = null;
	private ObjectMapper objectMapper = new ObjectMapper();

	public RetrollerHandler() {
		super();
		try {
			robot = new Robot();
			robot.setAutoDelay(40);
		    robot.setAutoWaitForIdle(true);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServletInputStream inputStream = baseRequest.getInputStream();
		String json = IOUtils.toString(inputStream);
		System.out.println(json);
		Command command = objectMapper.readValue(json, Command.class);
		issue(command);
		baseRequest.setHandled(true);
	}

	private void issue(Command command) {
		int key = command.getKey();
		int modifier = command.getModifier();
		
		System.out.println("Pressing key "+getKeyText(key)+" with modifier "+getKeyText(modifier));
		
		robot.delay(40);
		if (modifier != -1) {
			robot.keyPress(modifier);
		}
		
		robot.keyPress(key);
		robot.delay(5);
		robot.keyRelease(key);
		
		if (modifier != -1) {
			robot.keyRelease(modifier);
		}
	}
 
}
