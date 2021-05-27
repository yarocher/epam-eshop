package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;

public class CommandContainer {
	private static Map <String, Command> commands = new HashMap <> ();
	static {
		commands.put("list-products", new ListProductsCommand());	
	}

	public static Command get (String name) {
		return commands.get(name);
	}

}
