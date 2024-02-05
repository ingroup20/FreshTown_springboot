package com.cha104g1.freshtown_springboot.service.model.controller;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.cha104g1.freshtown_springboot.service.model.model.State;
import com.google.gson.Gson;

import idv.david.websocketchat.jedis.JedisHandleMessage;
import com.cha104g1.freshtown_springboot.service.model.model.ChatMessage;


@ServerEndpoint("/sFunction/{userName}")
public class ServiceWSController {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	//改成會員編號當作key。同步安全處理
	Gson gson = new Gson();
    //為方便使用json格式，使用gson API
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		
	    String timestamp = getTimestamp();
		/* Sends all the connected users to the new user */
		//取得線上所有使用者的名字
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames, timestamp);
		//open是識別字
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {//後端收到資料
		
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		String timestamp = chatMessage.getTimestamp();
		
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, timestamp, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}
		
		//聊天
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {

	    String timestamp = getTimestamp();
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames, timestamp);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
	
	   private String getTimestamp() {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    return dateFormat.format(new Date());
	}
	
	}