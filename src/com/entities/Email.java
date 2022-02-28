package com.entities;

public class Email {
	
	private String to;
	private String sub;
	private String content;
	private String from;
	
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Email(String to, String sub, String content, String from) {
		super();
		this.to = to;
		this.sub = sub;
		this.content = content;
		this.from = from;
	}
	

}
