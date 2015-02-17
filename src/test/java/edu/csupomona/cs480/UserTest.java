// Hesham Junit Test

package edu.csupomona.cs480;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.csupomona.cs480.data.User;

public class UserTest {

	private User testUser;

	public UserTest() {
		testUser = new User();
		testUser.setId("010939825");
		testUser.setPassword("pass");
		testUser.setName("Hesham");
		testUser.setMajor("CS");
		testUser.setLeave("2");
	}

	@Test
	public void userShouldInsertId() {
		String id = testUser.getId();
		assertEquals("010939825", id);
	}

	@Test
	public void userShouldInsertPassword() {
		String password  = testUser.getPassword();
		assertEquals("pass", password);
	}


	@Test
	public void userShouldInsertName() {

		String name  = testUser.getName();
		assertEquals("Hesham", name);
	}
	
	@Test
	public void userShouldInsertMajor() {
		
		String major = testUser.getMajor();
		assertEquals("CS", major);
	}

	@Test
	public void userShouldInsertLeaveTime() {
		
		String leaveTime = testUser.getLeave();
		assertEquals("2", leaveTime);
	}

	/*@Test
	public void UserShouldFailOnBadInputTest() {
		String name = testUser.getName();
		assertEquals("Darwish" , name);
	}*/
}
