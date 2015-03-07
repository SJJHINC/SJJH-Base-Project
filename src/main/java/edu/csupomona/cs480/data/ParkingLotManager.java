package edu.csupomona.cs480.data;

/**
 * @author SJJH INC This class is our representation of the Single set of
 *         Parking Lots that make up Cal Poly. It manages all of the individual
 *         lots together into one List of Parking lots, from which the
 *         webcontroller can call methods to add or print users in the
 *         individual parking lots.
 *
 */
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

	public List<User> getUserList(String ln) {
		int index = lotLocation(ln);
		return calpoly.get(index).getUserList();
	}

	public User getFirstuser(String ln) {
		int index = lotLocation(ln);
		return calpoly.get(index).getFirstUser();
	}
}
