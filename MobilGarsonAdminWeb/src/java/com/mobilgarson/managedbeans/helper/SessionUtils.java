package com.mobilgarson.managedbeans.helper;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils
{

    public static HttpSession getSession()
    {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest()
    {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserMail()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("mail").toString();
    }

    public static long getRestaurantId()
    {
        HttpSession session = getSession();
        if (session != null)
            return (long) session.getAttribute("restaurantid");
        else
            return -1;
    }
}
