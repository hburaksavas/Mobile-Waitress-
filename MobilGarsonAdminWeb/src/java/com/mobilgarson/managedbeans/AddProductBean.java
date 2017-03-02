/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.garson.model.DAO.CategoryDAO;
import com.garson.model.DAO.ProductDAO;
import com.garson.model.DAO.ProductImageDAO;
import com.garson.model.entity.Category;
import com.garson.model.entity.Product;
import com.garson.model.entity.ProductImages;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "addProductBean")
@SessionScoped
public class AddProductBean implements Serializable
{

    private long id;
    private long categoryid;
    private String name;
    private String definition;
    private double price;
    private long stock;

    private long restaurantid;
    private List<Category> list;
    private List<UploadedFile> uploadedFiles;

    public void upload(FileUploadEvent event)
    {
        uploadedFiles.add(event.getFile());
    }

    public AddProductBean()
    {
    }

    @PostConstruct
    public void init()
    {
        uploadedFiles = new ArrayList<>();
        list = new ArrayList<>();

    }

    public void initList(AjaxBehaviorEvent e)
    {
        try
        {
            HttpSession session = SessionUtils.getSession();
            restaurantid = (long) session.getAttribute("restaurantid");

            CategoryDAO dao = new CategoryDAO();
            list = dao.getAllCategories(restaurantid);

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Hata",
                            ex.getLocalizedMessage()));
        }

    }

    public void save(AjaxBehaviorEvent e)
    {

        ProductDAO dao = new ProductDAO();
        Product product = new Product()
                .setCategoryid(categoryid)
                .setDefiniton(definition)
                .setName(name)
                .setPrice(price)
                .setStock(stock)
                .setRestaurantid(restaurantid);

        dao.addProduct(product);
        id = product.getId();

        if (id > 0)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Info",
                            "Ürün ekleme başarılı, Eklenen ürün no: " + id));

            ProductImageDAO piDAO = new ProductImageDAO();
            for (UploadedFile file : uploadedFiles)
            {
                try
                {
                    String contentType = file.getContentType();
                    InputStream inputstream = file.getInputstream();
                    BufferedImage bufferedImage = ImageIO.read(inputstream);
                    if (bufferedImage != null)
                    {
                        java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
                        ImageIO.write(bufferedImage, contentType.replace("image/", ""), os);
                        byte[] data = os.toByteArray();
                        String imageStr = Base64.getEncoder().encodeToString(data);
                        ProductImages image = new ProductImages().setImagestring(imageStr).setProductid(id);
                        piDAO.addProductImage(image);

                    }
                } catch (IOException ex)
                {
                    Logger.getLogger(AddProductBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            price = 0;
            definition = "";
            name = "";
            stock = 0;

        }
        else
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Error",
                            "Ürün eklenirken bir hata oluştu"));
    }

    public List<Category> getList()
    {
        return list;
    }

    public void setList(List<Category> list)
    {
        this.list = list;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getCategoryid()
    {
        return categoryid;
    }

    public void setCategoryid(long categoryid)
    {
        this.categoryid = categoryid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public long getStock()
    {
        return stock;
    }

    public void setStock(long stock)
    {
        this.stock = stock;
    }

}
