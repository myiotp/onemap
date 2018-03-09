package com.onemap.websocket;

import java.net.URISyntaxException;

public class SocketClientEngine{  
	  
	  
    public static void main(String[] args) {  
        try {  
            WebClientEnum.CLIENT.initClient(new MsgWebSocketClient("wss://tongdagufen.cn/wsapp/"));  
        } catch (URISyntaxException | InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  