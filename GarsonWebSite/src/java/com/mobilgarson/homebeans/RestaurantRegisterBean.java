/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.homebeans;

import com.garson.model.DAO.RestaurantAdminDAO;
import com.garson.model.DAO.RestaurantDAO;
import com.garson.model.DAO.RestaurantImageDAO;
import com.garson.model.entity.Restaurant;
import com.garson.model.entity.RestaurantAdmin;
import com.garson.model.entity.RestaurantImage;
import com.mobilgarson.homebeans.service.ImageOperation;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "restaurantRegisterBean")
@ViewScoped
public class RestaurantRegisterBean
{

    private String restaurantName;
    private String mail;
    private String password;
    private String definiton;
    private UploadedFile file;

    private RestaurantDAO restaurantDAO;
    private RestaurantAdminDAO adminDAO;
    private RestaurantImageDAO imageDAO;

    public RestaurantRegisterBean()
    {
    }

    public void register()
    {
        Restaurant restaurant = new Restaurant()
                .setName(restaurantName)
                .setDefinition(definiton);
        restaurantDAO = new RestaurantDAO();

        restaurant = restaurantDAO.addRestaurant(restaurant);
        imageUpload(restaurant.getId());
        adminRegister(restaurant.getId());

        if (restaurant.getId() > 0)
        {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarıyla kayıt oldunuz", ""));
            restaurantName = "";
            definiton = "";
            mail = "";
            password = "";
        }
    }

    private void imageUpload(long restaurantid)
    {
        RestaurantImage image = new RestaurantImage().setRestaurantid(restaurantid);
        if (file != null)
        {
            image.setImagestring(ImageOperation.getImageStringBase64(file));
        }
        imageDAO = new RestaurantImageDAO();
        imageDAO.addRestaurantImages(image);
    }

    private void adminRegister(long restaurantid)
    {
        RestaurantAdmin admin = new RestaurantAdmin()
                .setMail(mail)
                .setPassword(password)
                .setRestaurantid(restaurantid);

        adminDAO = new RestaurantAdminDAO();
        adminDAO.addAdmin(admin);
    }

    public void upload(FileUploadEvent event)
    {
        file = event.getFile();
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public UploadedFile getFile()
    {
        return file;
    }

    public void setFile(UploadedFile file)
    {
        this.file = file;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDefiniton()
    {
        return definiton;
    }

    public void setDefiniton(String definiton)
    {
        this.definiton = definiton;
    }

}
