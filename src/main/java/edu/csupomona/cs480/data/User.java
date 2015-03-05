package edu.csupomona.cs480.data;

import java.util.Date;

/**
 * The basic user object.
 */
public class User {

	private String name;
	private String building;
	private String estimateLeavingtime;
	private String email;

	public User(String name, String building, String time, String email) {
		this.name = name;
		this.building = building;
		this.estimateLeavingtime = time;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeave() {
		return estimateLeavingtime;
	}

	public void setLeave(String time) {
		this.estimateLeavingtime = time;
	}

	public String getBuilding() {
		return building;
	}

	public String getEmail() {
		return email;
	}

}
