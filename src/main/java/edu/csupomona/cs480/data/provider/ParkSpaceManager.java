package edu.csupomona.cs480.data.provider;import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.ParkedUser;
import edu.csupomona.cs480.data.ParkedUserMap;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link UserManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class ParkSpaceManager {
	
	/**
	 * We persist all the user related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();

	/**
	 * Load the user map from the local file.
	 *
	 * @return
	 */
	private ParkedUserMap getParkedUserMap() {
		ParkedUserMap parkedUserMap = null;
		File parkedUserFile = ResourceResolver.getParkedUserFile();
        if (parkedUserFile.exists()) {
        	// read the file and convert the JSON content
        	// to the UserMap object
            try {
				parkedUserMap = JSON.readValue(parkedUserFile, ParkedUserMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	parkedUserMap = new ParkedUserMap();
        }
        return parkedUserMap;
	}

	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistParkedSpace(ParkedUserMap parkedUserMap) {
		try {
			// convert the parkeduser object to JSON format
            JSON.writeValue(ResourceResolver.getParkedUserFile(), parkedUserMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	
	public ParkedUser getParkedSpace(String name) {
		ParkedUserMap parkedUserMap = getParkedUserMap();
        return parkedUserMap.get(name);
	}

	
	public void updateParkedSpace(ParkedUser parkedUser ) {
		ParkedUserMap parkedUserMap = getParkedUserMap();
		parkedUserMap.put(parkedUser.getUserName(), parkedUser);
		persistParkedSpace(parkedUserMap);
	}

	
	public void deleteParkedSpace(String userId) {
		ParkedUserMap parkedUserMap = getParkedUserMap();
		parkedUserMap.remove(userId);
		persistParkedSpace(parkedUserMap);
	}

	
	public List<ParkedUser> listAllUsers() {
		ParkedUserMap parkedUserMap = getParkedUserMap();
		return new ArrayList<ParkedUser>(parkedUserMap.values());
	}

}




