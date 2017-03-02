package com.mobilgarson.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/garsonhome")
public class GarsonHomeWebSocket
{

    private static Set<Restaurant> clients = Collections.synchronizedSet(new HashSet<Restaurant>());

    @OnMessage
    public String onMessage(String message, Session session)
    {
        analyzeMessage(message, session);
        return null;
    }

    @OnError
    public void onError(Throwable error)
    {
    }

    @OnClose
    public void onClose(Session session)
    {
        clients.removeIf(c -> c.getSession().equals(session));
    }

    public void addRestaurantClient(long id, Session session)
    {
        clients.add(new Restaurant(id, session));
    }

    public void analyzeMessage(String message, Session session)
    {

        long restaurantNo;

        if (message.contains("add:restaurant:"))
        {

            String substring = message.substring(15);
            restaurantNo = Long.parseLong(substring);
            addRestaurantClient(restaurantNo, session);

        }
        else if (message.contains("request"))
        {

            restaurantNo = 0;

            if (message.contains("request:bill"))
            {
                restaurantNo = getRestaurantNoFromBillRequest(message);
            }
            else if (message.contains("request:waitress"))
            {
                restaurantNo = getRestaurantNoFromWaitressRequest(message);
            }

            if (restaurantNo > 0)
            {
                broadcastMessage(message, restaurantNo);
            }

        }
        else if (message.contains("table"))
        {
            broadcastTableOperations(message);
        }
        else if (message.contains("new:order"))
        {
            String arr[] = message.split(":");
            restaurantNo = Long.parseLong(arr[2]);
            broadcastMessage(message, restaurantNo);
        }
        else
        {
            try
            {

                session.close(new CloseReason(CloseReason.CloseCodes.PROTOCOL_ERROR, message));

            } catch (IOException ex)
            {
                Logger.getLogger(GarsonHomeWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void broadcastTableOperations(String message)
    {

        if (message.contains("open:table") || message.contains("close:table"))
        {
            String arr[] = message.split(":");
            long restaurantNo = Long.parseLong(arr[3]);
            broadcastMessage(message, restaurantNo);
        }

    }

    private long getRestaurantNoFromBillRequest(String message)
    {
        try
        {
            if (message != null)
            {
                String arr[] = message.split(":");
                long restaurantNo = Long.parseLong(arr[3]);
                return restaurantNo;
            }

        } catch (NumberFormatException ex)
        {
        }

        return 0;
    }

    private long getRestaurantNoFromWaitressRequest(String message)
    {
        try
        {
            if (message != null)
            {
                String arr[] = message.split(":");
                long restaurantNo = Long.parseLong(arr[4]);
                return restaurantNo;
            }

        } catch (NumberFormatException ex)
        {
        }

        return 0;
    }

    public void broadcastMessage(String message, long restaurantNo)
    {

        clients.stream()
                .filter(c -> c.getRestaurant_no() == restaurantNo)
                .forEach(c -> sendMessage(message, c.getSession()));

    }

    public void sendMessage(String message, Session session)
    {
        try
        {
            session.getBasicRemote().sendText(message);

        } catch (IOException ex)
        {
            Logger.getLogger(GarsonHomeWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
