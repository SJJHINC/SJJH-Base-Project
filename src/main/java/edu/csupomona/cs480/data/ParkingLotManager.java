package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {

	private List<ParkingLot> calpoly;

	public ParkingLotManager() {
		calpoly = new ArrayList<ParkingLot>();

	}

	public int lotLocation(String ln) {
		for (int i = 0; i < calpoly.size(); i++) {
			if (calpoly.get(i).getLotName().compareTo(ln) == 0) {
				return i;
			}
		}
		return -1;
	}

	public User getFirstUser(String ln) {
		int index = lotLocation(ln);
		return calpoly.get(index).get(0);
	}

	public void addUser(User u, String ln) {
		int index = lotLocation(ln);
		calpoly.get(index).addUserToLot(u);
	}

	public void addLot(ParkingLot p) {
		calpoly.add(p);
	}
	
	public List<User> getUserList(String ln){
		int index = lotLocation(ln);
		return calpoly.get(index).getUserList();	
	}

}
