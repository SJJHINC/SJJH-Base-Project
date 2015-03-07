package edu.csupomona.cs480.data;

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
