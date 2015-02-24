package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Message;
import edu.csupomona.cs480.data.MessageMap;
import edu.csupomona.cs480.data.ParkedUser;
import edu.csupomona.cs480.data.ParkedUserMap;
import edu.csupomona.cs480.util.ResourceResolver;

public class MessageManager {
	private static final ObjectMapper JSON = new ObjectMapper();
	
	private MessageMap getMessageMap() {
		MessageMap messageMap = null;
		File messagerFile = ResourceResolver.getMessageFile();
        if (messagerFile.exists()) {
        	// read the file and convert the JSON content
        	// to the UserMap object
            try {
            	messageMap = JSON.readValue(messagerFile, MessageMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	messageMap = new MessageMap();
        }
        return messageMap;
	}
	
	/**
	 * Save and persist the message map in the local file.
	 *
	 * @param messageMap
	 */
	private void persistMessageMap(MessageMap messageMap) {
		try {
			// convert the parkeduser object to JSON format
            JSON.writeValue(ResourceResolver.getMessageFile(), messageMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Message getMessage(String name) {
		MessageMap messageMap = getMessageMap();
        return messageMap.get(name);
	}

	public void updateMessageUser(Message message ) {
		MessageMap messageMap = getMessageMap();
		messageMap.put(message.getMessageID(), message);
		persistMessageMap(messageMap);
	}
	public void deleteMessage(String messageId) {
		MessageMap messageMap = getMessageMap();
		messageMap.remove(messageId);
		persistMessageMap(messageMap);
	}
	
	public List<Message> listMessage(String userId) {
		MessageMap messageMap = getMessageMap();
		return new ArrayList<Message>(messageMap.values());
	}
}
