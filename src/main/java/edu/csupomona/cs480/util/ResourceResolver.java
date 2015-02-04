package edu.csupomona.cs480.util;

import java.io.File;

/**
 * This is an utility class to help file locating.
 */
public class ResourceResolver {

	/** The base folder to store all the data used by this project. */
    private static final String BASE_DIR = System.getProperty("user.home") + "/cs480";
    


    /**
     * Get the file used to store the user object JSON
     *
     * @param userId
     * @return
     */
    public static File getUserFile() {
        File file = new File(BASE_DIR + "/" + "user-map.json");
        System.out.println(file.getAbsolutePath());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
         return file;
    }
    
    public static File getParkedUserFile(){
    	File file2 = new File(BASE_DIR + "/" + "parkedUser-map.json");
    	System.out.println(file2.getAbsolutePath());
    	if(!file2.getParentFile().exists()){
    		file2.getParentFile().mkdirs();
    	}
    	return file2;
    }
    public static File getUserNeedSpaceFile(){
    	File file3 = new File(BASE_DIR + "/" + "userNeedSpace-map.json");
    	System.out.println(file3.getAbsolutePath());
    	if(!file3.getParentFile().exists()){
    		file3.getParentFile().mkdirs();
    	}
    	return file3;
    }
}
