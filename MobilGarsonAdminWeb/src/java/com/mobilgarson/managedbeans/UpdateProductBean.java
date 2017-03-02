package com.mobilgarson.managedbeans;

import com.garson.model.DAO.CategoryDAO;
import com.garson.model.DAO.ProductDAO;
import com.garson.model.DAO.ProductImageDAO;
import com.garson.model.entity.Category;
import com.garson.model.entity.Product;
import com.garson.model.entity.ProductImages;
import com.mobilgarson.managedbeans.helper.ImageOperation;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "updateProductBean")
@SessionScoped
public class UpdateProductBean implements Serializable
{

    private long restaurantid;

    private String image1;
    private long imageid1;
    private String image2;
    private long imageid2;
    private String image3;
    private long imageid3;

    private long categoryid;
    private long productid;
    private String name;
    private double price;
    private String definition;
    private long stock;

    private double score;
    private int voteCounte;

    List<Category> categoryList;
    List<Product> productList;

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private ProductImageDAO productImageDAO;

    private UploadedFile uploadedFile1;
    private UploadedFile uploadedFile2;
    private UploadedFile uploadedFile3;

    public void uploadFile1(FileUploadEvent event)
    {
        uploadedFile1 = event.getFile();
    }

    public void uploadFile2(FileUploadEvent event)
    {
        uploadedFile2 = event.getFile();
    }

    public void uploadFile3(FileUploadEvent event)
    {
        uploadedFile3 = event.getFile();
    }

    public UpdateProductBean()
    {
    }

    public void initCategoryList(AjaxBehaviorEvent e)
    {

        HttpSession session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");
        categoryDAO = new CategoryDAO();
        categoryList = categoryDAO.getAllCategories(restaurantid);

    }

    public void initProductList(AjaxBehaviorEvent e)
    {

        if (productList != null)
        {
            productList = null;
        }

        HttpSession session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");
        productDAO = new ProductDAO();
        productList = productDAO.getRestaurantProductsByCategory(restaurantid, categoryid);

    }

    public void initProductDetails(AjaxBehaviorEvent e)
    {
        productDAO = new ProductDAO();

        if (image1 != null)
            image1 = null;
        if (image2 != null)
            image2 = null;
        if (image3 != null)
            image3 = null;

        if (productid > 0)
        {
            Product product = productDAO.getByID(productid);

            this.definition = product.getDefiniton();
            this.name = product.getName();
            this.price = product.getPrice();
            this.stock = product.getStock();
            this.productid = product.getId();
            this.voteCounte = product.getVoteCount() != null ? product.getVoteCount() : 0;
            this.score = product.getScore() != null ? product.getScore() : 0;

            productImageDAO = new ProductImageDAO();
            List<ProductImages> productImagesById = productImageDAO.getProductsImagesListById(productid);
            if (productImagesById != null)
            {
                int imagesCount = productImagesById.size();

                try
                {
                    if (imagesCount > 0)
                    {
                        image1 = productImagesById.get(0).getImagestring();
                        imageid1 = (long) productImagesById.get(0).getId();
                    }

                    if (imagesCount > 1)
                    {
                        image2 = productImagesById.get(1).getImagestring();
                        imageid2 = (long) productImagesById.get(1).getId();
                    }
                    if (imagesCount > 2)
                    {
                        image3 = productImagesById.get(2).getImagestring();
                        imageid3 = (long) productImagesById.get(2).getId();

                    }

                } catch (Exception ex)
                {
                }
            }
        }

    }

    public void updateProduct(AjaxBehaviorEvent e)
    {

        try
        {
            Product product = new Product();

            product.setCategoryid(categoryid)
                    .setDefiniton(definition)
                    .setId(productid)
                    .setName(name)
                    .setPrice(price)
                    .setRestaurantid(restaurantid)
                    .setStock(stock)
                    .setScore(score)
                    .setVoteCount(voteCounte);

            productDAO = new ProductDAO();
            productDAO.updateProduct(product);

            updateImages();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ürün bilgileri güncellendi"));
        } catch (Exception ex)
        {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ürün bilgileri güncellenirken beklenmedik bir hata oluştu"));

        }

    }

    private void updateImages()
    {
        String imageStringBase64;
        productImageDAO = new ProductImageDAO();
        if (uploadedFile1 != null)
        {

            imageStringBase64 = ImageOperation.getImageStringBase64(uploadedFile1);
            ProductImages image = createImage(imageid1, imageStringBase64);

            if (imageid1 > 0)
            {
                productImageDAO.updateProductImages(image);
            }
            else
            {
                productImageDAO.addProductImage(image);
            }
            image1 = imageStringBase64;
        }

        if (uploadedFile2 != null)
        {
            imageStringBase64 = ImageOperation.getImageStringBase64(uploadedFile2);
            ProductImages image = createImage(imageid2, imageStringBase64);

            if (imageid2 > 0)
            {
                productImageDAO.updateProductImages(image);
            }
            else
            {
                productImageDAO.addProductImage(image);
            }
            image2 = imageStringBase64;
        }
        if (uploadedFile3 != null)
        {
            imageStringBase64 = ImageOperation.getImageStringBase64(uploadedFile3);
            ProductImages image = createImage(imageid3, imageStringBase64);

            if (imageid3 > 0)
            {
                productImageDAO.updateProductImages(image);
            }
            else
            {
                productImageDAO.addProductImage(image);
            }

            image3 = imageStringBase64;
        }

    }

    private ProductImages createImage(long id, String imageStr)
    {
        if (id > 0)
        {
            return new ProductImages().setId(id).setImagestring(imageStr).setProductid(productid);
        }
        else
        {
            return new ProductImages().setImagestring(imageStr).setProductid(productid);
        }
    }

    public String getImage1()
    {
        return image1;
    }

    public void setImage1(String image1)
    {
        this.image1 = image1;
    }

    public String getImage2()
    {
        return image2;
    }

    public void setImage2(String image2)
    {
        this.image2 = image2;
    }

    public String getImage3()
    {
        return image3;
    }

    public void setImage3(String image3)
    {
        this.image3 = image3;
    }

    public long getCategoryid()
    {
        return categoryid;
    }

    public void setCategoryid(long categoryid)
    {
        this.categoryid = categoryid;
    }

    public long getProductid()
    {
        return productid;
    }

    public void setProductid(long productid)
    {
        this.productid = productid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public long getStock()
    {
        return stock;
    }

    public void setStock(long stock)
    {
        this.stock = stock;
    }

    public List<Category> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    public List<Product> getProductList()
    {
        return productList;
    }

    public void setProductList(List<Product> productList)
    {
        this.productList = productList;
    }

    public UploadedFile getUploadedFile1()
    {
        return uploadedFile1;
    }

    public void setUploadedFile1(UploadedFile uploadedFile1)
    {
        this.uploadedFile1 = uploadedFile1;
    }

    public UploadedFile getUploadedFile2()
    {
        return uploadedFile2;
    }

    public void setUploadedFile2(UploadedFile uploadedFile2)
    {
        this.uploadedFile2 = uploadedFile2;
    }

    public UploadedFile getUploadedFile3()
    {
        return uploadedFile3;
    }

    public void setUploadedFile3(UploadedFile uploadedFile3)
    {
        this.uploadedFile3 = uploadedFile3;
    }

}
