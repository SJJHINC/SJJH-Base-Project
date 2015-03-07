package edu.csupomona.cs480.data;

/**
 * @author SJJH INC This class was part of our original attempt to hold and
 *         manipulate the users that both needed spaces and that were drivers.
 *         As mentioned else where in the code, this attempt was set aside, but
 *         as we continue to work on the code, we hope to return to these
 *         original methods and get it working properly
 */

public class UserNeedSpace {
	private String postId;
	private String userName;
	private int hour;
	private int minute;
	private String contactInfo;

	// default constructor
	public UserNeedSpace() {
		hour = 0;
		minute = 0;
		userName = null;
		contactInfo = null;

	}

	// get user name-----------------------------------------------
	public String getUserName() {
		return userName;
	}

	// set user name/
	public void setUserName(String name) {
		this.userName = name;
	}

	// set the hour
	public void setHour(int hour) {
		this.hour = hour;
	}

	// set minute to be pick up
	public void setMinute(int minute) {
		this.minute = minute;
	}

	// Time the person with the parking spot is leaving------------------------
	public String getNeedTime() {
		return Integer.toString(hour) + ":" + Integer.toString(minute);
	}

	// contact infor
	public String getContactInfo() {
		return contactInfo;
	}

	// set the contact information
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

}
