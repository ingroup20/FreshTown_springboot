package com.cha104g1.freshtown_springboot.service.model.controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.cha104g1.freshtown_springboot.service.model.model.State;
import com.cha104g1.freshtown_springboot.service.model.service.SvcService;
import com.google.gson.Gson;

import idv.david.websocketchat.jedis.JedisHandleMessage;
import com.cha104g1.freshtown_springboot.service.model.model.ChatMessage;

@Controller
@Validated
@ServerEndpoint("/sFunction/service/${userAccount}")
public class ServiceWSController {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userAccount")String userAccount, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userAccount, userSession);
		/* Sends all the connected users to the new user 推播*/
		Set<String> userAccounts = sessionsMap.keySet();
		State stateMessage = new State("open", userAccount, userAccounts);
		//open是識別字
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
			String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
					userAccount, userAccounts);
			System.out.println(text);
	     }
    }
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();//拿到發送者物件
		String receiver = chatMessage.getReceiver();//拿到接收者物件
		
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("history = " + gson.toJson(cmHistory));
				return;//拿到歷史紀錄，結束。
			}
		}
		
		
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
		System.out.println("Message received: " + message);
	}
	
	
	
	
	
	
	}