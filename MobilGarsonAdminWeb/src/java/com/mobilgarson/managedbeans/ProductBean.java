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
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean implements Serializable
{

    private static final long serialVersionUID = 20111021L;

    private long restaurantid;
    private HttpSession session;
    private ProductDAO dao;

    private List<Category> categoryList;
    private long selectedCategoryId;
    private String selectedCategoryName;

    private List<Product> productList;
    private List<Product> filteredProductList;
    private String selectedProductName;
    private long selectedProductId;

    public ProductBean()
    {

    }

    @PostConstruct
    public void init()
    {

    }

    public void pullProductsAjaxListener(AjaxBehaviorEvent e)
    {
        initProductList();
    }

    public void pullCategoryAjaxListener(AjaxBehaviorEvent e)
    {
        session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");

        CategoryDAO cdao = new CategoryDAO();
        categoryList = cdao.getAllCategories(restaurantid);
        if (filteredProductList != null)
        {
            filteredProductList.clear();
        }
    }

    private void initProductList()
    {
        session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");

        if (restaurantid > 0 && selectedCategoryId > 0)
        {

            dao = new ProductDAO();
            productList = dao.getRestaurantProductsByCategory(restaurantid, selectedCategoryId);

        }

    }

    public void deleteProduct(AjaxBehaviorEvent e)
    {
        dao = new ProductDAO();
        dao.deleteProduct(selectedProductId);
        ProductImageDAO piDAO = new ProductImageDAO();
        try
        {

            piDAO.deleteProductImages(selectedProductId);

        } catch (Exception ex)
        {

        }

        initProductList();
    }

    public List<Category> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    public List<Product> getFilteredProductList()
    {
        return filteredProductList;
    }

    public void setFilteredProductList(List<Product> filteredProductList)
    {
        this.filteredProductList = filteredProductList;
    }

    public List<Product> getProductList()
    {
        return productList;
    }

    public void setProductList(final List<Product> productList)
    {
        this.productList = productList;
    }

    public String getSelectedProductName()
    {
        return selectedProductName;
    }

    public void setSelectedProductName(String selectedProductName)
    {
        this.selectedProductName = selectedProductName;
    }

    public long getSelectedProductId()
    {
        return selectedProductId;
    }

    public void setSelectedProductId(long selectedProductId)
    {
        this.selectedProductId = selectedProductId;
    }

    public long getSelectedCategoryId()
    {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(long selectedCategoryId)
    {
        this.selectedCategoryId = selectedCategoryId;
    }

    public String getSelectedCategoryName()
    {
        return selectedCategoryName;
    }

    public void setSelectedCategoryName(String selectedCategoryName)
    {
        this.selectedCategoryName = selectedCategoryName;
    }

}
