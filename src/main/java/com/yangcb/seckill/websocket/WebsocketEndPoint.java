package com.yangcb.seckill.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEndPoint extends TextWebSocketHandler {

	private static List<WebSocketSession> users = Collections.synchronizedList(new ArrayList<WebSocketSession>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		users.add(session);
		System.out.println("建立连接成功。。。。。。。");
		session.sendMessage(new TextMessage("建立连接成功！"));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);

		if (session.isOpen()) {
			session.close();
		}

		users.remove(session);// 移除session

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);

		users.remove(session);// 移除session

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		//super.handleBinaryMessage(session, message);
		
		System.out.println(message);
		
		

	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		super.handleMessage(session, message);
	}

	/****
	 * 接收客户端发送的消息并处理转发
	 */
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if(session.getId().equals("1")){
			WebSocketSession user=users.get(0);
			user.sendMessage(message);
		}else if(session.getId().equals("0")){
			WebSocketSession user=users.get(1);
			user.sendMessage(message);
		}
		
	}

	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
		// TODO Auto-generated method stub
		
		super.handlePongMessage(session, message);
	}

	//给在线用户发送消息
	public void sendMessageToUsers(TextMessage textMessage) {

		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(textMessage);
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

	}

}
