package com.onemap.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.onemap.domain.MachineryXY;
import com.onemap.service.MachineryXYService;

@ServerEndpoint("/websocketendpoint")
public class WsServer {
	@Autowired
	private MachineryXYService service;
	
	@OnOpen
	public void onOpen(){
		System.out.println("Open Connection ...");
	}
	
	@OnClose
	public void onClose(){
		System.out.println("Close Connection ...");
	}
	
	@OnMessage
	public String onMessage(String message){
		System.out.println("Message from the client: " + message);
		
		try {
			MachineryXY t = new MachineryXY();
			JSONObject json = JSON.parseObject(message, JSONObject.class);
			t.setPositionX(json.getDouble("x"));
			t.setPositionY(json.getDouble("y"));
			t.setSpeed(json.getDouble("s"));
			t.setMachineryOperationId(json.getInteger("a"));
			t.setPositionSequence(json.getLong("t"));
			service.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}

}