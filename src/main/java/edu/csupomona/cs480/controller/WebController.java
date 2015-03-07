package edu.csupomona.cs480.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.csupomona.cs480.data.ParkedUser;
import edu.csupomona.cs480.data.ParkingLot;
import edu.csupomona.cs480.data.ParkingLotManager;
import edu.csupomona.cs480.data.ParkingLotManagerSingleton;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserNeedSpace;
import edu.csupomona.cs480.data.provider.MessageManager;
import edu.csupomona.cs480.data.provider.ParkSpaceManager;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.data.provider.UserNeedSpaceManager;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

/**
 * @author Jeremiah Porcu, Jose Silva, Hesham Darwish, Sang Pham
 *
 */
@RestController
public class WebController {

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;
	private MessageManager messageManager;
	public boolean full = false;
	private ParkingLotManager PL;

	/**
	 * These objects are part of our original design that we had to move away
	 * from. As explained below, they are kept here because we will eventually
	 * rewrite everything to work with these methods, but for now, they don't do
	 * anything.
	 */
	// private ParkSpaceManager parkSpaceManager = new ParkSpaceManager();
	// private ParkedUser currentParkedUser;
	// private UserNeedSpaceManager userNeedSpaceManager = new
	// UserNeedSpaceManager();
	// private UserNeedSpace userNeedSpace;
	// private UserManager userManager;
	// private MessageManager messageManager;

	/**
	 * This method instantiates and fills the Parking Lot Manager with all of
	 * the parking lots used in our code. It gets called on the first user
	 * signing in, and only gets called once.
	 * 
	 */
	@RequestMapping(value = "/fill", method = RequestMethod.GET)
	String fill() {
		if (full == false) {
			PL = new ParkingLotManager();
			ParkingLot j = new ParkingLot("Lot J");
			ParkingLot m = new ParkingLot("Lot M");
			ParkingLot f234 = new ParkingLot("Lot F2, F3, F4");
			ParkingLot f5910 = new ParkingLot("Lot F5, F9, F10");
			ParkingLot p = new ParkingLot("Parking Structure");
			ParkingLot f8 = new ParkingLot("Lot F8");
			ParkingLot q = new ParkingLot("Lot Q");
			ParkingLot bclose = new ParkingLot("Lot B-Close");
			ParkingLot bfar = new ParkingLot("Lot B-Far");
			ParkingLot kclose = new ParkingLot("Lot K-Close");
			ParkingLot kfar = new ParkingLot("Lot K-Far");
			PL.addLot(j);
			PL.addLot(m);
			PL.addLot(f234);
			PL.addLot(f5910);
			PL.addLot(p);
			PL.addLot(f8);
			PL.addLot(q);
			PL.addLot(bclose);
			PL.addLot(bfar);
			PL.addLot(kclose);
			PL.addLot(kfar);
			full = true;
			return "\nAdded Lots";

		}
		return "";
	}

	/**
	 * This method created and instantiated a ParkingLot singleton. For the sake
	 * of our own code, we haven't implemented this into the final product yet,
	 * however we plan on eventually using this to simplify the process.
	 * 
	 */
	@RequestMapping(value = "/cs480/SingletonTest", method = RequestMethod.GET)
	String Singletontest() {

		ParkingLotManagerSingleton plm = ParkingLotManagerSingleton
				.getInstance();
		ParkingLot p = new ParkingLot("Lot 8");
		ParkingLot p1 = new ParkingLot("Lot 9");
		ParkingLot p2 = new ParkingLot("Lot 10");
		ParkingLot p3 = new ParkingLot("Lot 11");
		plm.addLot(p);
		plm.addLot(p1);
		plm.addLot(p2);
		plm.addLot(p3);
		plm.printLots();

		return "\nPrinted Lots";

	}

	/**
	 * This method adds a user to the appropriate parking lot as specified by
	 * the data sent to this method from the website. Based on the "lot" field,
	 * this method finds that appropriate parking lot within the
	 * ParkingLotManager and inserts that user into the field
	 * 
	 */
	@RequestMapping(value = "/add/{name}", method = RequestMethod.POST)
	String addUserToLot(@PathVariable("name") String name,
			@RequestParam("building") String building,
			@RequestParam("lot") String lot, @RequestParam("time") String time,
			@RequestParam("email") String email) {

		User user = new User(name, building, time, email);
		System.out.println(name + " " + building + " " + time + " " + lot + " "
				+ email);
		PL.addUser(user, lot);
		return "/menu";
	}

	/**
	 * This method returns a string that is the concatenation of all the users
	 * in a specific lot's data. This is the information that gets returned to
	 * the website to be displayed when a parking lot is pressed.
	 * 
	 */
	@RequestMapping(value = "/get/{lot}", method = RequestMethod.GET)
	String getFirstUser(@PathVariable("lot") String lot) {
		List<User> list = PL.getUserList(lot);
		String userTest = "";
		for (int i = 0; i < list.size(); i++) {
			User test = list.get(i);
			userTest += (test.getName() + " is in " + test.getBuilding()
					+ " and leaves at " + test.getLeave() + " !" + test
					.getEmail());
			userTest += " ? ";
		}
		System.out.print(userTest);

		return userTest;
	}

	/**
	 * The following set of url based commands were part of our original, and if
	 * further implemented, future design. For time issues related to the
	 * project, we had to change how we were going to store and work with user
	 * data, but these methods represent our attempts at getting these to work.
	 */
	// @RequestMapping(value = "/cs480/parkedUser/list", method =
	// RequestMethod.GET)
	// List<ParkedUser> listAllParkedSpace() {
	// return parkSpaceManager.listAllUsers();
	// }
	//
	// @RequestMapping(value = "/cs480/parkedUser/add", method =
	// RequestMethod.POST)
	// ParkedUser addParkedSpace(
	// // @RequestParam("userId") String userId,
	// @RequestParam("Time Hour:") int hour,
	// @RequestParam("minute") int minute,
	// @RequestParam("pickOrNot") boolean pickOrNot,
	// @RequestParam(value = "location", required = false) int buildingNum,
	// @RequestParam("Contact Information") String contactInfo) {
	// ParkedUser user = new ParkedUser();
	// // user.setUserName(currentUser.getId());
	// user.setPickup(pickOrNot);
	// user.setContactInfo(contactInfo);
	// user.setHour(hour);
	// user.setMinute(minute);
	// user.setPickUpBuildingNum(buildingNum);
	// parkSpaceManager.updateParkedSpace(user);
	// return user;
	// }

	// @RequestMapping(value = "/cs480/parkedUser/delete/{postId}", method =
	// RequestMethod.DELETE)
	// void deleteParkedSpace(@PathVariable("postId") String postId) {
	// parkSpaceManager.deleteParkedSpace(postId);
	// }
	//
	//
	// @RequestMapping(value = "/cs480/userNeedSpace/list", method =
	// RequestMethod.GET)
	// List<UserNeedSpace> listAllUserNeedSpace() {
	// return userNeedSpaceManager.listAllUsers();
	// }
	//
	// @RequestMapping(value = "/cs480/userNeedSpace/add", method =
	// RequestMethod.POST)
	// UserNeedSpace addUserNeedSpace(
	// // @RequestParam("userId") String userId,
	// @RequestParam("Time Hour:") int hour,
	// @RequestParam("minute") int minute,
	// @RequestParam("Contact Information") String contactInfo) {
	// UserNeedSpace user = new UserNeedSpace();
	// // user.setUserName(currentUser.getId());
	// user.setContactInfo(contactInfo);
	// user.setHour(hour);
	// user.setMinute(minute);
	// userNeedSpaceManager.updateUser(user);
	// return user;
	// }
	//
	// @RequestMapping(value = "/cs480/userNeedSpace/delete/{postId}", method =
	// RequestMethod.DELETE)
	// void deleteUserNeedSpace(@PathVariable("postId") String postId) {
	// messageManager.deleteMessage(postId);
	// }
}