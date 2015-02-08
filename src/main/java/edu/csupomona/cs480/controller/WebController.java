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

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.ParkedUser;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserNeedSpace;
import edu.csupomona.cs480.data.provider.ParkSpaceManager;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.data.provider.UserNeedSpaceManager;

///////////////////////////////////
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
///////////////////////////////////

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
    @Autowired
    private UserManager userManager;
    private User currentUser;
    private ParkSpaceManager parkSpaceManager = new ParkSpaceManager();
    private ParkedUser currentParkedUser;
    private UserNeedSpaceManager userNeedSpaceManager = new UserNeedSpaceManager();
    private UserNeedSpace userNeedSpace;

    /**
     * This is a simple example of how the HTTP API works.
     * It returns a String "OK" in the HTTP response.
     * To try it, run the web application locally,
     * in your web browser, type the link:
     * 	http://localhost:8080/cs480/ping
     */
    @RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
    String healthCheck() {
    	// You can replace this with other string,
    	// and run the application locally to check your changes
    	// with the URL: http://localhost:8080/
        return "OK";
    }
    
    //Trying to create the framework for logging in. Could be completely wrong way to do this, but starting somewhere.
    @RequestMapping(value = "/login/{userId}+{userPass}", method = RequestMethod.GET)
    String login(
    		@PathVariable("userId") String userId,
    		@PathVariable("userPass") String password) {
   
    	User user = userManager.getUser(userId);
    	if(password.compareTo(user.getPassword()) == 0){
    		currentUser = user;
    		return "login successful";
    		
    	}else{
    		currentUser = null;
    		return "login failed, actual password is " + user.getPassword() + " and you entered " + password + " !";
    		
    	}
    
    }
    
    //The system now keeps a record of who is logged in in the global variable currentUser. 
    //The idea I have is that after a login, the page loads a main page like "modelandView" method
    //down below, and passes in current user, so it knows to pull up the appropriate lists and etc.
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    String Checklogin() {
    	User temp = currentUser;
    	if(temp == null){
    		return "No one is logged in";
    	}else{
    		return temp.getName() + " is logged in";
    	}
    
    }

    /**
     * This is a simple example of how to use a data manager
     * to retrieve the data and return it as an HTTP response.
     * <p>
     * Note, when it returns from the Spring, it will be
     * automatically converted to JSON format.
     * <p>
     * Try it in your web browser:
     * 	http://localhost:8080/cs480/user/user101
     */
    @RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
    User getUser(@PathVariable("userId") String userId) {
    	User user = userManager.getUser(userId);
        return user;
    }

    /**
     * This is an example of sending an HTTP POST request to
     * update a user's information (or create the user if not
     * exists before).
     *
     * You can test this with a HTTP client by sending
     *  http://localhost:8080/cs480/user/user101
     *  	name=John major=CS
     *
     * Note, the URL will not work directly in browser, because
     * it is not a GET request. You need to use a tool such as
     * curl.
     *
     * @param id
     * @param name
     * @param major
     * @return
     */
    @RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
    User updateUser(
    		@PathVariable("userId") String id,
    		@RequestParam("name") String name,
    		@RequestParam("password") String password,
    		@RequestParam(value = "major", required = false) String major) {
    	User user = new User();
    	user.setId(id);
    	user.setPassword(password);
    	user.setMajor(major);
    	user.setName(name);
    	userManager.updateUser(user);
    	return user;
    }

    /**
     * This API deletes the user. It uses HTTP DELETE method.
     *
     * @param userId
     */
    @RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
    void deleteUser(
    		@PathVariable("userId") String userId) {
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
    //-------------------------------parked space---------------------------------
    @RequestMapping(value = "/cs480/parkedUser/list", method = RequestMethod.GET)
    List<ParkedUser> listAllParkedSpace() {
    	return parkSpaceManager.listAllUsers();
    }
    
    @RequestMapping(value = "/cs480/parkedUser/add", method = RequestMethod.POST)
    ParkedUser addParkedSpace(
    		//@RequestParam("userId") String userId,
    		@RequestParam("Time Hour:") int hour,
    		@RequestParam("minute") int minute,
    		@RequestParam("pickOrNot") boolean pickOrNot,
    		@RequestParam(value="location", required = false) int buildingNum,
    		@RequestParam("Contact Information") String contactInfo){
    	ParkedUser user = new ParkedUser();
    	user.setUserName(currentUser.getId());
    	user.setPickup(pickOrNot);
    	user.setContactInfo(contactInfo);
    	user.setHour(hour);
    	user.setMinute(minute);
    	user.setPickUpBuildingNum(buildingNum);
    	parkSpaceManager.updateUser(user);
    	return user;
    }

//-------------------------user that need a parking space--------------------------------
    @RequestMapping(value = "/cs480/userNeedSpace/list", method = RequestMethod.GET)
    List<UserNeedSpace> listAllUserNeedSpace() {
    	return userNeedSpaceManager.listAllUsers();
    }
    
    @RequestMapping(value = "/cs480/userNeedSpace/add", method = RequestMethod.POST)
    UserNeedSpace addUserNeedSpace(
    		//@RequestParam("userId") String userId,
    		@RequestParam("Time Hour:") int hour,
    		@RequestParam("minute") int minute,
    		@RequestParam("Contact Information") String contactInfo){
    	UserNeedSpace user = new UserNeedSpace();
    	user.setUserName(currentUser.getId());
    	user.setContactInfo(contactInfo);
    	user.setHour(hour);
    	user.setMinute(minute);
    	userNeedSpaceManager.updateUser(user);
    	return user;
    }

///////////////////////////////////////////////////////////////////////////////AS6
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
    
}