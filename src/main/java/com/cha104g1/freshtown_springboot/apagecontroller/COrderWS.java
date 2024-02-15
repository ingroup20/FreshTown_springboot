package com.cha104g1.freshtown_springboot.apagecontroller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.cha104g1.freshtown_springboot.amodel.OrderState;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.google.gson.Gson;

@ServerEndpoint("/COrderWS/{userName}")
public class COrderWS {
	private static Map<String ,Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	
	@Autowired
	OrdersService ordersSvc;
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName,Session userSession) throws IOException{
		sessionsMap.put(userName,userSession);
		Set<String> userNames = sessionsMap.keySet();
		OrderState stateOrderMsg = new OrderState("open",userName,userNames); 
		String orderIdJson =gson.toJson(stateOrderMsg);
		Collection<Session> sessions =sessionsMap.values();
		for(Session session : sessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(orderIdJson);
			}
		}
		String text =String.format("Sesson ID = %s,connected; userName =%s%nusers: %s", userSession.getId(),userName, userNames);
		System.out.println(text);
		
	}

		
	@OnMessage
	public void onMessage(Session userSession,String orderId) {
		String orderIdMsg=orderId;
		String delayDesc = ordersSvc.getOneOrders(Integer.valueOf(orderId)).getDelayDesc();
		String recevier="店家的話:";
		
		if(userSession != null && userSession.isOpen()) {
			userSession.getAsyncRemote().sendText(orderIdMsg);
			System.out.println(orderIdMsg);
			return;
		}
		
		Session receiverSession=sessionsMap.get(recevier);
		if(receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(recevier);
			userSession.getAsyncRemote().sendText(delayDesc);
		}
		System.out.println("說:"+recevier+delayDesc);
	}
	
	@OnError
	public void onError(Session userSession,Throwable e) {
		System.err.println("Errer:"+e.toString());
	}

	@OnClose
	public void onClose(Session userSession,CloseReason reason) {
		String userNameClose=null;
		Set<String> userNames=sessionsMap.keySet();
		for(String userName :userNames) {
			if(sessionsMap.get(userName).equals(userSession)) {
				userNameClose=userName;
				sessionsMap.remove(userName);
				break;
			}
		}
		
		
		if(userNameClose != null) {
			OrderState stateMessage=new OrderState("close",userNameClose,userNames);
			String stateMessageJson=gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for(Session session :sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
		
		String text = String.format("Sesson ID = %s,disconnected; close code = %s%nusers: %s", userSession.getId(),reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}


}
