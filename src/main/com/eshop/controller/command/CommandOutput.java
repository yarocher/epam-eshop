package com.eshop.controller.command;

public class CommandOutput {
	private String page;
	private boolean redirect;

	public CommandOutput (String page, boolean redirect) {
		setPage(page);
		setRedirect(redirect);
	}

	public CommandOutput (String page) {
		setPage(page);
	}

	public String getPage() {return page;}
	public boolean forRedirect () {return redirect;}

	public void setPage (String page) {this.page = page;}
	public void setRedirect (boolean redirect) {this.redirect = redirect;}
}
