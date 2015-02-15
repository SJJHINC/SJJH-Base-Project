package Test;

import static org.junit.Assert.*; 


import org.junit.Before;
import org.junit.Test;

import edu.csupomona.cs480.data.ParkedUser;

public class ParkedUserTest {

	public class ParkUserTest {
		private ParkedUser testParked;
		private ParkUserTest(){
			testParked = new ParkedUser();
			testParked.setUserName("sang");
			testParked.setContactInfo("sang@yahoo.com");
			testParked.setHour(2);
			testParked.setMinute(30);
			testParked.setPickup(true);
			testParked.setPickUpBuildingNum(8);
		}
		
		@Before
		public void parkUserShouldInsertPickUpFalse(){
			assertEquals(false, testParked.getPickup());
		}
		@Test
		public void parkUserShouldInsertParkedUser(){
			String name = testParked.getUserName();
			assertEquals("sang", name);
		}
		@Test
		public void parkUserShouldInsertHour(){
			
			assertEquals(2, testParked.getHour());
		}
		
		@Test
		public void parkUserShouldInsertMinute(){
			
			assertEquals(30, testParked.getMinute());
		}
		
		@Test
		public void parkUserShouldInsertPickUp(){
			
			assertEquals(true, testParked.getPickup());
		}
		@Test
		public void parkUserShouldInsertBuildingNum(){
			
			assertEquals(8, testParked.getPickUpBuildingNum());
		}
		
		
	}

}
