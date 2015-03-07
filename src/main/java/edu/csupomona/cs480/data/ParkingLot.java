package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SJJH INC This class is our representation of each parking lot. It
 *         holds a name, by which we reference it within the rest of the
 *         webcontroller, and a list of the users that are currently parked.
 * 
 *         We use the user's email as a key for this data, so if a user tries to
 *         re add themselves to a building but still have the same email, their
 *         old data will be replaced with the new information, allowing them to
 *         update their code.
 *
 */
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
		boolean add = true;
		for (int i = 0; i < parkedUsers.size(); i++) {
			if (u.getEmail().compareTo(parkedUsers.get(i).getEmail()) == 0) {
				parkedUsers.remove(i);
				parkedUsers.add(u);
				add = false;
			}
		}
		if (add) {
			parkedUsers.add(u);
		}

	}

	public void displayParkedUsers() {
		for (int i = 0; i < parkedUsers.size(); i++) {
			System.out.println(parkedUsers.get(i).getName());
		}
	}

	public User get(int index) {
		return parkedUsers.get(index);
	}

	public List<User> getUserList() {
		return parkedUsers;

	}

	public User getFirstUser() {
		return parkedUsers.get(0);
	}
}
