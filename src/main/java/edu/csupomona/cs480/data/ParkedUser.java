package edu.csupomona.cs480.data;

import java.util.Set;

public class ParkedUser {
	private String userName;
	private int pickUpBuildingNum;
	private int hour;
	private int minute;
	private boolean pickup;	//some people may not like to be pickup
	private String contactInfo;
	
	//default constructor
	public ParkedUser(){
		hour =0;
		minute=0;
		pickUpBuildingNum=0;
		pickup=false;
		userName = null;
		contactInfo = null;
		
	}
	
	//get user name-----------------------------------------------
	public String getUserName() {
		return userName;
	}
	//set user name/
	public void setUserName(String name) {
		this.userName = name;
	}

	//get building number to be pickup
	public int getPickUpBuildingNum() {
		return pickUpBuildingNum;
	}

	//set building number----------------------------------------
	public void setPickUpBuildingNum(int pickUpBuildingNum) {
		this.pickUpBuildingNum = pickUpBuildingNum;
	}

	
	//set the hour
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getHour() {
		return hour;
	}

	//set minute to be pick up
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public int getMinute() {
		return  minute;
	}
	
	//Time the person with the parking spot is leaving------------------------
	public String getLeaveTime(){
		return Integer.toString(hour) + ":" + Integer.toString(minute);
	}
	
	//return whether the person what to be pick up
	public boolean getPickup() {
		return pickup;
	}

	//set pick up yes or no
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}

	//contact infor
	public String getContactInfo() {
		return contactInfo;
	}

	//set the contact information
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
}
