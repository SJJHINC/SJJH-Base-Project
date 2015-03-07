package edu.csupomona.cs480.data;

/**
 * @author SJJH INC This class is our representation of the Single set of
 *         Parking Lots that make up Cal Poly. It manages all of the individual
 *         lots together into one List of Parking lots, from which the
 *         webcontroller can call methods to add or print users in the
 *         individual parking lots.
 * 
 *         This method is created in a way that uses the concept of Singletons.
 *         Since we only want one real set of ParkingLots, the singleton method
 *         seems a good way to secure ourselves from accidentally creating and
 *         filling the lots again. Our final product will hopefully contain this
 *         method being used completely, but for now, it was just run as a test.
 *
 */
import java.util.ArrayList;
import java.util.List;

public class ParkingLotManagerSingleton {

	private static List<ParkingLot> plm;
	private static ParkingLotManagerSingleton CalPoly;

	private ParkingLotManagerSingleton() {
		plm = new ArrayList<ParkingLot>();

	}

	public static ParkingLotManagerSingleton getInstance() {
		if (CalPoly == null) {
			synchronized (ParkingLotManager.class) {
				if (CalPoly == null) {
					CalPoly = new ParkingLotManagerSingleton();
				}
			}
		}
		return CalPoly;
	}

	public int lotLocation(String ln) {
		for (int i = 0; i < plm.size(); i++) {
			if (plm.get(i).getLotName().compareTo(ln) == 0) {
				return i;
			}
		}
		return -1;
	}

	public User getFirstUser(String ln) {
		int index = lotLocation(ln);
		return plm.get(index).get(0);
	}

	public void addUser(User u, String ln) {
		int index = lotLocation(ln);
		plm.get(index).addUserToLot(u);
	}

	public void addLot(ParkingLot p) {
		plm.add(p);
	}

	public void printLots() {
		for (int i = 0; i < plm.size(); i++) {
			System.out.println(plm.get(i).getLotName());
		}
	}

}
