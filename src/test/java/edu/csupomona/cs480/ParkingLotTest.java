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
		User j = new User();
		j.setName("Jeremy");
		j.setId("jdporcu");
		j.setPassword("Pass");
		j.setLeave("12:30");
		pl.addUserToLot(j);
	}

	@Test
	public void lotShouldHoldNameTest() {
		String name = pl.getUserName("Jeremy", "jdporcu");
		assertEquals("Jeremy", name);
	}

	@Test
	public void lotShouldGetTimeForUserTest() {
		String time = pl.getUser("Jeremy", "jdporcu").getLeave();
		assertEquals("12:30", time);
	}

	@Test
	public void lotShouldFailOnBadInputTest() {
		String name = pl.getUserName("Waffles", "jdporcu");
		assertEquals(null, name);
	}
}
