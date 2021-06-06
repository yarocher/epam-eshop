package com.eshop.controller.jstl;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import java.util.logging.Logger;
import java.util.logging.Level;

public class DateFormatTag extends TagSupport {

	Logger logger = Logger.getLogger(DateFormatTag.class.getName());

	private LocalDateTime date;

	public void setDate (LocalDateTime date) {
		this.date = date;
	}

	public int doStartTag() throws JspTagException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateFormatted = formatter.format(date);
			pageContext.getOut().write(dateFormatted);	
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
