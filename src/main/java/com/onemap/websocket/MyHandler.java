package com.onemap.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.onemap.domain.MachineryXY;
import com.onemap.service.MachineryXYService;

public class MyHandler extends TextWebSocketHandler {
	@Autowired
	private MachineryXYService service;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		System.out.println("Message from the client: " + message.getPayload());

		try {
			MachineryXY t = new MachineryXY();
			JSONObject json = JSON.parseObject(message.getPayload(), JSONObject.class);
			t.setPositionX(json.getDouble("x"));
			t.setPositionY(json.getDouble("y"));
			t.setSpeed(json.getDouble("s"));
			t.setMachineryOperationId(json.getInteger("a"));
			t.setPositionSequence(json.getLong("t"));
			service.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}