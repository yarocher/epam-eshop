package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;

public class CommandContainer {
	private static Map <String, Command> commands = new HashMap <> ();

	static {
		commands.put("logout", new LogoutCommand());
		commands.put("add-item", new AddItemCommand()); 
		commands.put("orders", new ListOrdersCommand());
		commands.put("users", new ListUsersCommand());
		commands.put("make-order", new MakeOrderCommand());
		commands.put("login", new LoginCommand());
		commands.put("reg", new RegistrationCommand());
		commands.put("products", new SearchProductsCommand());
		commands.put("cart", new CartCommand());
		commands.put("account", new AccountCommand());
		commands.put("update-order", new UpdateOrderCommand());
		commands.put("update-user", new UpdateUserCommand());
		commands.put("delete-user", new DeleteUserCommand());
		commands.put("update-product", new UpdateProductCommand());
		commands.put("delete-product", new DeleteProductCommand());
		commands.put("create-product", new CreateProductCommand());
	}

	public static Command get (String name) {
		return commands.getOrDefault(name, new IndexCommand ());
	}

}
