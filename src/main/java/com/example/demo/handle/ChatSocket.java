package com.example.demo.handle;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/qqserver/{qqNumber}")
public class ChatSocket {
    static{
        System.out.println("----------------------------------");
        System.out.println("------   WebSocket服务启动   -------");
        System.out.println("---------                ----------");
        System.out.println("----------------------------------");
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * QQ号
     */
    private String qqNumber;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    //  注：底下WebSocket是当前类名
    private static CopyOnWriteArraySet<ChatSocket> webSockets =new CopyOnWriteArraySet<>();

    // 用来存在线连接用户信息( 线程安全 )
    // key: QQ号  value: 每个客户端的session对象
    private static ConcurrentHashMap<String,Session> sessionPool = new ConcurrentHashMap<String,Session>();

    // 1. 当客户端连接时做什么事情
    @OnOpen
    public void onOpen(Session session,@PathParam(value="qqNumber") String qqNumber){
        System.out.println("--客户端连接[qq："+qqNumber+"]--");
        this.session = session;
        this.qqNumber = qqNumber;
        sessionPool.put(qqNumber,session);
        webSockets.add(this);
    }

    // 2.当客户端发送消息给服务器时做什么事情
    // 参数message就是客户端给服务器发送的消息内容
    @OnMessage
    public void onMsg(String message){
        // 实现群发功能

        try {
            sendAllMsg(message);
        } catch (IOException e) {
            System.err.println("发送消息异常");
            e.printStackTrace();
        }
    }

    // 3.当客户端断开连接时要做什么事情
    @OnClose
    public void onClose(){
        System.out.println("客户端断开连接~！~！！");
    }

    // 4.当客户端发送消息、连接或断开连接时错误做什么事情
    @OnError
    public void onErr(Throwable error){
        error.printStackTrace();
        System.out.println("服务器或客户端异常！！！！");
    }

    /**
     * 群发功能
     * @param msg 群发的消息
     */
    public void sendAllMsg(String msg) throws IOException {
        System.out.println("--群发消息："+msg);
        // 遍历所有已经连接到服务器的客户端对象
        for(ChatSocket socket:webSockets){

            if(socket.session.isOpen()){// 判断客户端是否在线
                socket.session.getAsyncRemote().sendText(msg);
            }
        }
    }
}
