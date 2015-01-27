
//this class was originally intended to keep message for each
//parking space
//some user don't want to give out contact information or 
//person picking up may have different intention then the message is evidence

package edu.csupomona.cs480.data;

import java.util.ArrayList;

public class Message {
	ArrayList<String> username;
	ArrayList<String> committ;
	
	private Message(){
		username = new ArrayList<String>();
		committ = new ArrayList<String>();
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

	public void setCommitt(String message) {
		this.committ.add(message);
	}

	
	
	
}