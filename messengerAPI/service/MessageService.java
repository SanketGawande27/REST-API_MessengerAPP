package org.sanket.messengerAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.sanket.messengerAPI.model.Message;

public class MessageService {

	public List<Message> getAllMessages() {
		Message m1 = new Message(1L, "HEllo Sanket, how are you", "Sanket");
		Message m2 = new Message(2L, "HEllo Sanket, this is the second message", "Sanket");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		return list;

	}

}
