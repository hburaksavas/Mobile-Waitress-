package com.mobilgarson.homebeans;

import com.garson.model.DAO.EmployeeDAO;
import com.garson.model.DAO.RestaurantDAO;
import com.garson.model.entity.Employee;
import com.garson.model.entity.Restaurant;
import com.mobilgarson.homebeans.service.SessionUtils;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "homeLogin")
@SessionScoped
public class HomeLogin implements Serializable
{

    private String mail;
    private String password;

    private List<Restaurant> restaurants;

    private long restaurantid;

    public HomeLogin()
    {
    }

    @PostConstruct
    public void init()
    {
        try
        {
            RestaurantDAO restaurantDAO = new RestaurantDAO();
            restaurants = restaurantDAO.getRestaurants();

        } catch (Exception ex)
        {

        }

    }

    public String login()
    {

        EmployeeDAO dao = new EmployeeDAO();
        Employee employee = dao.login(restaurantid, mail, password);

        if (employee != null)
        {
            if (employee.getId() > 0)
            {

                HttpSession session = SessionUtils.getSession();
                session.setAttribute("restoran-no", restaurantid);
                return "home?faces-redirect=true";
            }

        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Hatalı Giriş", ""));

        return "";
    }

    public String logout()
    {
        try
        {
            HttpSession sessions = SessionUtils.getSession();
            sessions.invalidate();

        } catch (Exception ex)
        {

        }

        return "login?faces-redirect=true";
    }

    public List<Restaurant> getRestaurants()
    {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }

    public long getRestaurantid()
    {
        return restaurantid;
    }

    public void setRestaurantid(long restaurantid)
    {
        this.restaurantid = restaurantid;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
