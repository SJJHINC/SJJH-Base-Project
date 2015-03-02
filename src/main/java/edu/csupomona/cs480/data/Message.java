
//this class was originally intended to keep message for each
//parking space
//some user don't want to give out contact information or 
//person picking up may have different intention then the message is evidence

package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class Message {
	private String messageID;
	private String messageTitle;
	private ArrayList<String> username;
	private ArrayList<String> committ;
	private static Message messageObject;
	
	private Message(){
		username = new ArrayList<String>();
		committ = new ArrayList<String>();
	}
	public static Message getMessageObject(){
		if(messageObject == null){
			messageObject = new Message();
		}
		return messageObject;
	} 
	
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	public ArrayList<String> getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username.add(name);
	}

	public ArrayList<String> getCommitt() {
		return committ;
	}
	public ArrayList<String> getuserName() {
		return username;
	}

	public void setCommitt(String message, String name) {
		this.committ.add(message);
		this.username.add(name);
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	
	
	
}