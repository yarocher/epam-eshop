package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;

public class CommandContainer {
	private static Map <String, Command> commands = new HashMap <> ();
	static {
		commands.put("products", new ListProductsCommand());	
		commands.put("orders", new ListOrdersCommand());	
		commands.put("users", new ListUsersCommand());	
		commands.put("login", new LoginCommand());	
		commands.put("logout", new LogoutCommand());	
		commands.put("reg", new RegistrationCommand());	
		commands.put("add-item", new AddItemCommand());	
		commands.put("make-order", new MakeOrderCommand());	
	}

	public static Command get (String name) {
		return commands.getOrDefault(name, req -> new CommandOutput (req.getContextPath() + "/index.html", true));
	}

}
