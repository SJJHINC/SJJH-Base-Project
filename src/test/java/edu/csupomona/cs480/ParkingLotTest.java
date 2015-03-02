//Jeremiahs Maven Junit Test

package edu.csupomona.cs480;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.csupomona.cs480.data.ParkingLot;
import edu.csupomona.cs480.data.User;

public class ParkingLotTest {

	private ParkingLot pl;

	public ParkingLotTest() {
		pl = new ParkingLot("Building 8");
		User j = new User("Jeremy", "8", "12:30");
		pl.addUserToLot(j);
	}

	// Had to refactor because of changes to user class.
	// @Test
	// public void lotShouldGetTimeForUserTest() {
	// String time = pl.getUser("Jeremy", "jdporcu").getLeave();
	// assertEquals("12:30", time);
	// }
	//
	// @Test
	// public void lotShouldFailOnBadInputTest() {
	// String name = pl.getUserName("Waffles", "jdporcu");
	// assertEquals(null, name);
	// }
}
