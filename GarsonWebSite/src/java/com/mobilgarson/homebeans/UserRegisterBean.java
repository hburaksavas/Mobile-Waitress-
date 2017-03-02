/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.homebeans;

import com.garson.model.DAO.UserDAO;
import com.garson.model.entity.User;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userRegisterBean")
@SessionScoped
public class UserRegisterBean implements Serializable
{

    private String name;
    private String surname;
    private String mail;
    private String password;

    private UserDAO userDAO;

    public UserRegisterBean()
    {
    }

    public void register()
    {
        User user = new User()
                .setMail(mail)
                .setName(name)
                .setSurname(surname)
                .setPassword(password);

        userDAO = new UserDAO();

        userDAO.addUser(user);

        if (user != null && user.getId() > 0)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                     new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarıyla kayıt oldunuz", ""));
            mail = "";
            name = "";
            surname = "";
            password = "";
        }

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
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
