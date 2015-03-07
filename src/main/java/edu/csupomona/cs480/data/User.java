package edu.csupomona.cs480.data;

/**
 * The basic user object, to be created through the webcontroller and stored in
 * the parkingLots. The user itself stores what building they are currently in,
 * as well as what time they will be leaving.
 */
public class User {

	private String name;
	private String building;
	private String estimateLeavingtime;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

}
