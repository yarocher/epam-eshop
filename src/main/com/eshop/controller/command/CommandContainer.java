package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class CommandContainer {
	private static Map <String, Command> commands = new HashMap <> ();
	private static Set <Command> guestCommands = new HashSet <> ();
	private static Set <Command> customerCommands = new HashSet <> ();
	private static Set <Command> adminCommands = new HashSet <> ();
	private static Command index = new IndexCommand ();
	static {
		Command logout = new LogoutCommand ();
		Command addItem = new AddItemCommand();
		Command orders = new ListOrdersCommand ();
		Command users = new ListUsersCommand ();
		Command makeOrder = new MakeOrderCommand ();
		Command login = new LoginCommand ();
		Command reg = new RegistrationCommand ();
		Command products = new SearchProductsCommand ();
		Command cart = new CartCommand ();
		Command account = new AccountCommand ();
		Command updateOrder = new UpdateOrderCommand ();
		Command updateUser = new UpdateUserCommand ();
		Command deleteUser = new DeleteUserCommand ();
		Command updateProduct = new UpdateProductCommand ();
		Command deleteProduct = new DeleteProductCommand ();
		Command createProduct = new CreateProductCommand ();

		commands.put("logout", logout);
		commands.put("add-item", addItem); 
		commands.put("orders", orders);
		commands.put("users", users);
		commands.put("make-order", makeOrder);
		commands.put("login", login);
		commands.put("reg", reg);
		commands.put("products", products);
		commands.put("cart", cart);
		commands.put("account", account);
		commands.put("update-order", updateOrder);
		commands.put("update-user", updateUser);
		commands.put("delete-user", deleteUser);
		commands.put("update-product", updateProduct);
		commands.put("delete-product", deleteProduct);
		commands.put("create-product", createProduct);

		guestCommands.add(index); 
		guestCommands.add(products);	
		guestCommands.add(addItem); 
		guestCommands.add(cart); 
		guestCommands.add(reg);	
		guestCommands.add(login);	

		customerCommands.add(index);	
		customerCommands.add(products);	
		customerCommands.add(addItem); 
		customerCommands.add(cart);	
		customerCommands.add(makeOrder);	
		customerCommands.add(account);	
		customerCommands.add(logout);	

		adminCommands.add(index);	
		adminCommands.add(products);	
		adminCommands.add(users);	
		adminCommands.add(orders);	
		adminCommands.add(logout);	
		adminCommands.add(updateOrder);	
		adminCommands.add(updateUser);	
		adminCommands.add(deleteUser);	
		adminCommands.add(updateProduct);	
		adminCommands.add(deleteProduct);	
		adminCommands.add(createProduct);	
	}

	public static Command get (String name) {
		return commands.getOrDefault(name, index);
	}

	public static boolean isAdminCommand (Command command) {
		return adminCommands.contains(command);
	}

	public static boolean isCustomerCommand (Command command) {
		return customerCommands.contains(command);
	}

	public static boolean isGuestCommand (Command command) {
		return guestCommands.contains(command);
	}

}
