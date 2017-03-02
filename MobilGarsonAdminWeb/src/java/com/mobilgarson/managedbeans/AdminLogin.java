/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.mobilgarson.managedbeans.helper.SessionUtils;
import com.garson.model.DAO.RestaurantAdminDAO;
import com.garson.model.DAO.RestaurantDAO;
import com.garson.model.entity.Restaurant;
import com.garson.model.entity.RestaurantAdmin;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BurakS
 */
@ManagedBean(name = "adminLogin", eager = true)
@SessionScoped
public class AdminLogin implements Serializable
{

    private String password;
    private String mail;
    private String restaurantName;
    private List<Restaurant> restaurants;

    private long restaurantid;
    private List<SelectItem> restItems = new ArrayList<>();

    private RestaurantAdminDAO adminDao;
    private RestaurantDAO restaurantDAO;
    private HttpSession session;

    public AdminLogin()
    {

    }

    @PostConstruct
    public void init()
    {
        restaurantDAO = new RestaurantDAO();
        restaurants = restaurantDAO.getRestaurants();

        restaurants.forEach((res) ->
        {
            restItems.add(new SelectItem(res.getId(), res.getName()));
        });

        restaurants = null;

        adminDao = new RestaurantAdminDAO();
        session = SessionUtils.getSession();
    }

    public void update()
    {
        adminDao = new RestaurantAdminDAO();
        session = SessionUtils.getSession();
        int adminid = (int) session.getAttribute("adminid");
        restaurantid = (long) session.getAttribute("restaurantid");
        RestaurantAdmin admin = new RestaurantAdmin().setId(adminid).setMail(mail).setPassword(password).setRestaurantid(restaurantid);
        adminDao.updateAdmin(admin);
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Bilgiler başarıyla günncellendi"));
    }

    public void setRestItems(List<SelectItem> restItems)
    {
        this.restItems = restItems;
    }

    public List<SelectItem> getRestItems()
    {
        return restItems;
    }

    public void setRestaurantid(long restaurantid)
    {
        this.restaurantid = restaurantid;
    }

    public long getRestaurantid()
    {
        return restaurantid;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public String validateLogin()
    {

        if (adminDao == null)
            adminDao = new RestaurantAdminDAO();

        RestaurantAdmin login = adminDao.login(restaurantid, mail, password);

        if (login != null && login.getId() > 0)
        {
            if (restaurantDAO == null)
                restaurantDAO = new RestaurantDAO();
            Restaurant restaurant = restaurantDAO.getByID(login.getRestaurantid());
            session.setAttribute("mail", login.getMail());
            session.setAttribute("restaurantname", restaurant.getName());
            session.setAttribute("adminid", login.getId());
            session.setAttribute("restaurantid", restaurantid);
            return "admin?faces-redirect=true";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Hatalı Giriş!",
                            "Lütfen doğru mail ve şifre giriniz"));
            return "";
        }

    }

    public String logout()
    {
        try
        {
            HttpSession sessions = SessionUtils.getSession();
            sessions.invalidate();

        } catch (Exception e)
        {
        }

        return "login?faces-redirect=true";
    }

    public static boolean isPostback()
    {
        return FacesContext.getCurrentInstance().isPostback();
    }
}
