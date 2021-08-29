package com.bnpparibas.irb.dsira.prospects.model;

public class Greeting {
	public Greeting(String message) {
		this.message = message;
	}
	public Greeting() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;


}
