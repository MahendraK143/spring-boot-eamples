package com.emp.bean;

public class MessageBean {
	private String message;
	private String type;// error, success
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "MessageBean [message=" + message + ", type=" + type + "]";
	}
	
}
