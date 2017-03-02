package com.mobilgarson.managedbeans;

import com.garson.model.DAO.RestaurantCommentsDAO;
import com.garson.model.DAO.RestaurantDAO;
import com.garson.model.DAO.RestaurantImageDAO;
import com.garson.model.entity.Restaurant;
import com.garson.model.entity.RestaurantComments;
import com.garson.model.entity.RestaurantImage;
import com.mobilgarson.managedbeans.helper.ImageOperation;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "restaurantBean")
@SessionScoped
public class RestaurantBean implements Serializable
{

    private long restaurantid;
    private String restaurantName;
    private String restaurantDefinition;
    private double restaurantServiceScore;

    private long imageid;
    private String restaurantImage64;

    private List<RestaurantComments> commentList;

    private RestaurantDAO restaurantDAO;
    private RestaurantImageDAO imageDAO;
    private RestaurantCommentsDAO commentDAO;
    private UploadedFile uploadedImage;

    public RestaurantBean()
    {
    }

    @PostConstruct
    public void init()
    {
        HttpSession session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");
    }

    public void upload(FileUploadEvent event)
    {
        uploadedImage = event.getFile();
    }

    public void updateRestaurantInfos(AjaxBehaviorEvent event)
    {
        checkDAOs();

        Restaurant restaurant = restaurantDAO.getByID(restaurantid);
        restaurant.setDefinition(restaurantDefinition);
        restaurant.setName(restaurantName);
        restaurantDAO.updateRestaurant(restaurant);

    }

    public void updateRestaurantImage(AjaxBehaviorEvent event)
    {
        checkDAOs();

        updateRestaurantImage();
    }

    private void updateRestaurantImage()
    {
        if (uploadedImage != null)
        {
            List<RestaurantImage> listRestaurantImages
                    = imageDAO.getListRestaurantImages(restaurantid);

            int imageCount = listRestaurantImages != null ? listRestaurantImages.size() : 0;

            this.restaurantImage64 = ImageOperation.getImageStringBase64(uploadedImage);

            if (restaurantImage64 != null && !restaurantImage64.equals(""))
            {
                RestaurantImage rImage = new RestaurantImage()
                        .setImagestring(restaurantImage64)
                        .setRestaurantid(restaurantid);

                if (imageCount == 0)
                {
                    imageDAO.addRestaurantImages(rImage);
                }
                else
                {
                    rImage.setId(imageid);
                    imageDAO.updaRestaurantImages(rImage);
                }

            }
        }
    }

    public void initRestaurantComments(AjaxBehaviorEvent event)
    {
        if (commentDAO == null)
        {
            commentDAO = new RestaurantCommentsDAO();
        }

        this.commentList = commentDAO.getRestaurantComments(restaurantid);
        initRestaurantInfos(event);
    }

    public void initRestaurantInfos(AjaxBehaviorEvent event)
    {
        checkDAOs();

        uploadRestaurantInfosFromDB();

        uploadRestaurantImageFromDB();

    }

    public void clearFields(AjaxBehaviorEvent event)
    {
        this.restaurantName = "";
        this.restaurantDefinition = "";
    }

    private void uploadRestaurantInfosFromDB()
    {
        Restaurant restaurant = restaurantDAO.getByID(restaurantid);
        if (restaurant != null)
        {
            this.restaurantName = restaurant.getName();
            this.restaurantDefinition = restaurant.getDefinition();
            this.restaurantServiceScore = restaurant.getServiceScore() != null ? restaurant.getServiceScore() : 0;
        }
    }

    private void uploadRestaurantImageFromDB()
    {
        List<RestaurantImage> listRestaurantImages = imageDAO.getListRestaurantImages(restaurantid);
        if (listRestaurantImages != null)
        {
            if (listRestaurantImages.size() > 0)
            {
                this.restaurantImage64 = listRestaurantImages.get(0).getImagestring();
                this.imageid = listRestaurantImages.get(0).getId();
            }
        }
    }

    private void checkDAOs()
    {
        if (restaurantDAO == null)
        {
            restaurantDAO = new RestaurantDAO();
        }
        if (imageDAO == null)
        {
            imageDAO = new RestaurantImageDAO();
        }
    }

    public long getRestaurantid()
    {
        return restaurantid;
    }

    public void setRestaurantid(long restaurantid)
    {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDefinition()
    {
        return restaurantDefinition;
    }

    public void setRestaurantDefinition(String restaurantDefinition)
    {
        this.restaurantDefinition = restaurantDefinition;
    }

    public double getRestaurantServiceScore()
    {
        return restaurantServiceScore;
    }

    public void setRestaurantServiceScore(double restaurantServiceScore)
    {
        this.restaurantServiceScore = restaurantServiceScore;
    }

    public String getRestaurantImage64()
    {
        return restaurantImage64;
    }

    public void setRestaurantImage64(String restaurantImage64)
    {
        this.restaurantImage64 = restaurantImage64;
    }

    public List<RestaurantComments> getCommentList()
    {
        return commentList;
    }

    public void setCommentList(List<RestaurantComments> commentList)
    {
        this.commentList = commentList;
    }

    public UploadedFile getUploadedImage()
    {
        return uploadedImage;
    }

    public void setUploadedImage(UploadedFile uploadedImage)
    {
        this.uploadedImage = uploadedImage;
    }

}
