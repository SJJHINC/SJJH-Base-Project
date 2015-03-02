//Laptop Git Test

package edu.csupomona.cs480.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

///////////////////////////////////
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.lang3.time.StopWatch;
///////////////////////////////////
import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.random.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

//////////////////////////////////
/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

/**
 * @author Jeremiah
 *
 */
/**
 * @author Jeremiah
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
	private User currentUser;
	private ParkingLotManager PL;

	private ParkSpaceManager parkSpaceManager = new ParkSpaceManager();
	private ParkedUser currentParkedUser;
	private UserNeedSpaceManager userNeedSpaceManager = new UserNeedSpaceManager();
	private UserNeedSpace userNeedSpace;

	/**
	 * This is a simple example of how the HTTP API works. It returns a String
	 * "OK" in the HTTP response. To try it, run the web application locally, in
	 * your web browser, type the link: http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}

	// /For now, this method will create the parkingLotManager
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

		}
		return "\nAdded Lots";
	}

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

	// Adding A user into a parking lot
	@RequestMapping(value = "/add/{name}+{building}+{lot}+{time}", method = RequestMethod.POST)
	String addUserToLot(@PathVariable("name") String name,
			@PathVariable("building") String building,
			@PathVariable("lot") String lot, @PathVariable("time") String time) {

		User user = new User(name, building, time);
		PL.addUser(user, lot);
		return "User Added Successfully";
	}

	// The list of people in a certain parking lot
	@RequestMapping(value = "/get/{lot}", method = RequestMethod.POST)
	List<User> getUsers(@PathVariable("lot") String lot) {

		return PL.getUserList(lot);
	}

	// The system now keeps a record of who is logged in in the global variable
	// currentUser.
	// The idea I have is that after a login, the page loads a main page like
	// "modelandView" method
	// down below, and passes in current user, so it knows to pull up the
	// appropriate lists and etc.
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	String Checklogin() {
		User temp = currentUser;
		if (temp == null) {
			return "No one is logged in";
		} else {
			return temp.getName() + " is logged in";
		}

	}

	/**
	 * This is a simple example of how to use a data manager to retrieve the
	 * data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be automatically converted
	 * to JSON format.
	 * <p>
	 * Try it in your web browser: http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	@RequestMapping(value = "/firstUserTest/{lotNumber}", method = RequestMethod.GET)
	User getFirstUserFromLot(@PathVariable("lotNumber") String ln) {
		User user = PL.getFirstUser(ln);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

	// -------------------------------parked
	// space---------------------------------
	@RequestMapping(value = "/cs480/parkedUser/list", method = RequestMethod.GET)
	List<ParkedUser> listAllParkedSpace() {
		return parkSpaceManager.listAllUsers();
	}

	@RequestMapping(value = "/cs480/parkedUser/add", method = RequestMethod.POST)
	ParkedUser addParkedSpace(
			// @RequestParam("userId") String userId,
			@RequestParam("Time Hour:") int hour,
			@RequestParam("minute") int minute,
			@RequestParam("pickOrNot") boolean pickOrNot,
			@RequestParam(value = "location", required = false) int buildingNum,
			@RequestParam("Contact Information") String contactInfo) {
		ParkedUser user = new ParkedUser();
		// user.setUserName(currentUser.getId());
		user.setPickup(pickOrNot);
		user.setContactInfo(contactInfo);
		user.setHour(hour);
		user.setMinute(minute);
		user.setPickUpBuildingNum(buildingNum);
		parkSpaceManager.updateParkedSpace(user);
		return user;
	}

	@RequestMapping(value = "/cs480/parkedUser/delete/{postId}", method = RequestMethod.DELETE)
	void deleteParkedSpace(@PathVariable("postId") String postId) {
		parkSpaceManager.deleteParkedSpace(postId);
	}

	// -------------------------user that need a parking
	// space--------------------------------
	@RequestMapping(value = "/cs480/userNeedSpace/list", method = RequestMethod.GET)
	List<UserNeedSpace> listAllUserNeedSpace() {
		return userNeedSpaceManager.listAllUsers();
	}

	@RequestMapping(value = "/cs480/userNeedSpace/add", method = RequestMethod.POST)
	UserNeedSpace addUserNeedSpace(
			// @RequestParam("userId") String userId,
			@RequestParam("Time Hour:") int hour,
			@RequestParam("minute") int minute,
			@RequestParam("Contact Information") String contactInfo) {
		UserNeedSpace user = new UserNeedSpace();
		// user.setUserName(currentUser.getId());
		user.setContactInfo(contactInfo);
		user.setHour(hour);
		user.setMinute(minute);
		userNeedSpaceManager.updateUser(user);
		return user;
	}

	@RequestMapping(value = "/cs480/userNeedSpace/delete/{postId}", method = RequestMethod.DELETE)
	void deleteUserNeedSpace(@PathVariable("postId") String postId) {
		messageManager.deleteMessage(postId);
	}

	// /////////////////////////////////////////////////////////////////////////////AS6
	public static void parserWithJSOUP(String[] args) {

		Document doc;
		try {

			// need http protocol
			doc = Jsoup.connect("http://google.com").get();

			// get page title
			String title = doc.title();
			System.out.println("title : " + title);

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {

				// get the value from href attribute
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("text : " + link.text());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// /////////////////////////////////////// Testing Commons-Math, assignment
	// 5, Hesham.
	public static void testingCommonsMath(String[] args) {
		RandomGenerator randomGenerator = new JDKRandomGenerator();
		System.out.println(randomGenerator.nextInt());
		System.out.println(randomGenerator.nextDouble());

		// Descriptive Statistics like Mean, standart deviation, Max
		DescriptiveStatistics stats = new DescriptiveStatistics();
		stats.addValue(1);
		stats.addValue(2);
		stats.addValue(3);
		stats.addValue(4);
		stats.addValue(5);

		System.out.println("Mean is" + stats.getMean() + "\n");
		System.out.println("Standard Deviation is"
				+ stats.getStandardDeviation() + "\n");
		System.out.println("Max is" + stats.getMax() + "\n");

		Complex c1 = new Complex(1, 2);
		Complex c2 = new Complex(2, 3);
		System.out.println("Absolute of c1 is " + c1.abs() + "\n");
		System.out.println("Addition of c1 and c2 is " + (c1.add(c2)) + "\n");
	}
	// ///////////////////////////////////////// end of commonsMath testing.

}