package com.mobilgarson.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class GarsonWebClient
{

    Session session;

    public GarsonWebClient()
    {
        try
        {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(this,
                    new URI("ws://localhost:8080/mobilgarsonweb/garsonhome"));

        } catch (IOException | URISyntaxException | DeploymentException ex)
        {
            Logger.getLogger(GarsonWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session session)
    {
    }

    @OnMessage
    public String onMessage(String message, Session session)
    {
        return null;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason)
    {

    }

    public void sendMessage(String message)
    {
        try
        {

            session.getBasicRemote().sendText(message);

        } catch (IOException ex)
        {
            Logger.getLogger(GarsonWebClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                session.close();

            } catch (IOException ex)
            {
                Logger.getLogger(GarsonWebClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
