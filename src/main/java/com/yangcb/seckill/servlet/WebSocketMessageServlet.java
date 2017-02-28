package com.yangcb.seckill.servlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;

@ServerEndpoint("/websocket/test")
public class WebSocketMessageServlet {
	
	//线程安全Map结合
	private static Map<String, Session> sessions=Collections.synchronizedMap(new HashMap<String,Session>());
	
	
	public WebSocketMessageServlet(){
		System.out.println("------------------------------");
	}
	
	
	 /** 
     * 向客户端群发信息 
     * @param category 
     * @param data 
     */  
    public synchronized static void sendAll(String category, Object data){  
        SocketReply re = new SocketReply(category, data);  
        String replyStr = JSON.toJSONString(re);  
        System.out.println("开始群发信息！");  
        Set<String> keys = sessions.keySet();  
        for(String k:keys){  
            Session s = sessions.get(k);  
            if(s.isOpen()){  
                try{  
                    s.getBasicRemote().sendText(replyStr);  
  
                    System.out.println("发送成功， id="+k);  
                }catch(Exception e){  
                    System.err.println("发送出错："+e.getMessage());  
                }  
            }  
        }  
    }  
  
    @OnMessage  
    public void onMessage(Session session, String msg){  
    	
    	//接收客户端推送过来的消息
    	
        System.out.println("收到信息");  
        try {  
            session.getBasicRemote().sendText("get");  
        }catch (Exception e){  
            e.printStackTrace();  
        }  
    }  
  
    
    //建立长连接信息
    @OnOpen  
    public void onOpen(Session session, EndpointConfig config){  
        try {  
            sessions.put(session.getId(), session);  
  
            SocketReply re = new SocketReply("a", "aa");  
            String replyStr = JSON.toJSONString(re);  
  
            session.getBasicRemote().sendText(replyStr);  
        }catch (Exception e){  
            e.printStackTrace();  
        }  
    }  
  
    @OnError  
    public void onError(Session session, Throwable throwable){  
  
    }  
  
    @OnClose  
    public void onClose(Session session, CloseReason reason){  
    	
    	//链接断开移除session 长链接
    	
        try {  
            System.out.println("断开连接, id="+session.getId());  
            synchronized (sessions){  
                sessions.remove(session.getId());  
            }  
        }catch (Exception e){  
            e.printStackTrace();  
        }  
    }  
	
	

}

class  SocketReply{
	private String category;
	private Object data;
	public SocketReply(String category, Object data) {
		super();
		this.category = category;
		this.data = data;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "SocketReply [category=" + category + ", data=" + data + "]";
	}
	
	
	
	
}

