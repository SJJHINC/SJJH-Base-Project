package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private String LotName;
	private List<User> parkedUsers;

	public ParkingLot(String name) {
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

	public void addUserToLot(User u) {
		parkedUsers.add(u);
	}

	public void displayParkedUsers() {
		for (int i = 0; i < parkedUsers.size(); i++) {
			System.out.println(parkedUsers.get(i).getName());
		}
	}

	public String getUserName(String n, String id) {
		for (int i = 0; i < parkedUsers.size(); i++) {
			if (parkedUsers.get(i).getName() == n
					&& parkedUsers.get(i).getId() == id) {
				return parkedUsers.get(i).getName();
			}

		}
		return null;
	}

	public User get(int index) {
		return parkedUsers.get(index);
	}

	public User getUser(String n, String id) {
		for (int i = 0; i < parkedUsers.size(); i++) {
			if (parkedUsers.get(i).getName() == n
					&& parkedUsers.get(i).getId() == id) {
				return parkedUsers.get(i);
			}
		}
		return null;
	}
}
