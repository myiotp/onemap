package com.onemap.websocket;

public enum WebClientEnum {  
  
    CLIENT;  
      
    private static MsgWebSocketClient socketClient = null;  
      
    public static void initClient(MsgWebSocketClient client) throws InterruptedException {  
        socketClient = client;  
        if(socketClient != null) {  
            socketClient.connect();  
            Thread.sleep(10000);
            socketClient.send("Test websocket");  
        }  
        boolean flag = true;  
        int i=1000;  
        while(flag) {  
            socketClient.send("Test websocket"+(i--));  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            if(i == 0) {  
                flag = false;  
            }  
        }  
    }  
      
}  