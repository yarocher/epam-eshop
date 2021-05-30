package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	CommandOutput execute (HttpServletRequest req);
}
