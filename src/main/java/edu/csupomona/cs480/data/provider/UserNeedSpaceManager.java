package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.csupomona.cs480.data.UserNeedSpace;
import edu.csupomona.cs480.data.UserNeedSpaceMap;
import edu.csupomona.cs480.util.ResourceResolver;

public class UserNeedSpaceManager {
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
	private UserNeedSpaceMap getUserNeedSpaceMap() {
		UserNeedSpaceMap userNeedSpaceMap = null;
		File userNeedSpaceFile = ResourceResolver.getUserNeedSpaceFile();
        if (userNeedSpaceFile.exists()) {
        	// read the file and convert the JSON content
        	// to the UserMap object
            try {
            	userNeedSpaceMap = JSON.readValue(userNeedSpaceFile, UserNeedSpaceMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	userNeedSpaceMap = new UserNeedSpaceMap();
        }
        return userNeedSpaceMap;
	}

	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistUserMap(UserNeedSpaceMap userNeedSpaceMap) {
		try {
			// convert the userspace object to JSON format
            JSON.writeValue(ResourceResolver.getUserNeedSpaceFile(), userNeedSpaceMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	
	public UserNeedSpace getUser(String name) {
		UserNeedSpaceMap userNeedSpaceMap = getUserNeedSpaceMap();
        return userNeedSpaceMap.get(name);
	}

	
	public void updateUser(UserNeedSpace userNeedSpace ) {
		UserNeedSpaceMap userNeedSpaceMap = getUserNeedSpaceMap();
		userNeedSpaceMap.put(userNeedSpace.getUserName(), userNeedSpace);
		persistUserMap(userNeedSpaceMap);
	}

	
	public void deleteUser(String userId) {
		UserNeedSpaceMap userNeedSpaceMap = getUserNeedSpaceMap();
		userNeedSpaceMap.remove(userId);
		persistUserMap(userNeedSpaceMap);
	}

	
	public List<UserNeedSpace> listAllUsers() {
		UserNeedSpaceMap userNeedSpaceMap = getUserNeedSpaceMap();
		return new ArrayList<UserNeedSpace>(userNeedSpaceMap.values());
	}

}
