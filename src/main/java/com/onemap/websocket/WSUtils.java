package com.onemap.websocket;
import java.net.URI;
import java.net.URISyntaxException;

public class WSUtils {
	public static void main(String[] args) {
		 WebsocketClientEndpoint clientEndPoint = null;
		try {
            // open websocket
            clientEndPoint = new WebsocketClientEndpoint(new URI("wss://tongdagufen.cn/wsapp/"));

            
            // send message to websocket
            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);
            if(clientEndPoint != null) {
                // add listener
                clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                    public void handleMessage(String message) {
                        System.out.println(message);
                    }
                });
                
                Thread.sleep(100*1000*1000);
        		}

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
        
		
    }
}
