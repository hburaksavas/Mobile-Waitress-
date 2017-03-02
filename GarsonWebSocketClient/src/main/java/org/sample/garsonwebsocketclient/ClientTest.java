package org.sample.garsonwebsocketclient;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class ClientTest
{
    Session session;
    public ClientTest()
    {
        try
        {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(this,
                    new URI("ws://localhost:8080/garsonweb/garsonhome"));
            session.getBasicRemote().sendText("request:bill:7:1");

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println("OnOpen" + session.getAsyncRemote());
        session.getAsyncRemote().sendText("request:bill:7:1");
    }

    @OnMessage
    public String onMessage(String message, Session session)
    {
        System.out.println(message);
        return null;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason)
    {
        System.out.println("On Close" + session.getAsyncRemote());

    }

    public static void main(String[] args)
    {
        ClientTest clientTest = new ClientTest();
    }
}
