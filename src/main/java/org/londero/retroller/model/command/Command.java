package org.londero.retroller.model.command;

/**
 * Encapsulates a command
 * @author Adam Londero
 */
public class Command {

	private int key;
	private int modifier;
	
	private Command() {}
	
	public int getKey() {
		return key;
	}
	
	public int getModifier() {
		return modifier;
	}

}
