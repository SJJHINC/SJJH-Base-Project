package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.List;



public class ParkingLot {

	private String LotName;
	private List<User> parkedUsers;
	
	public ParkingLot(String name){
		this.LotName = name;
		parkedUsers = new ArrayList<User>();
		
	}

	public String getLotName() {
		return LotName;
	}

	public void setLotName(String lotName) {
		LotName = lotName;
	}

	public List<User> getParkedUsers() {
		return parkedUsers;
	}

	public void setParkedUsers(List<User> parkedUsers) {
		this.parkedUsers = parkedUsers;
	}
	
	
	
	
}
