/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.garson.model.DAO.CategoryDAO;
import com.garson.model.entity.Category;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "categoryBean")
@RequestScoped
public class CategoryBean
{

    private String categoryNewName;

    //kategori çekme,ekleme,silme işlemleri
    private CategoryDAO categoryDAO;

    //kategori listesinde seçilecek itemin değerini tutar, silme işlemi için kullanılır
    private long categoryid;

    //session'da set edilen restaurant id
    private long restaurantid;

    private static Categoryy[] category;

    public CategoryBean()
    {
    }

    @PostConstruct
    public void init()
    {
        System.out.println("init construct of CategoryBean");
        HttpSession session = SessionUtils.getSession();
        this.restaurantid = (long) session.getAttribute("restaurantid");
    }

    public void setCategoryNewName(String categoryNewName)
    {
        this.categoryNewName = categoryNewName;
    }

    public String getCategoryNewName()
    {
        return categoryNewName;
    }

    public long getCategoryid()
    {
        return categoryid;
    }

    public void setCategoryid(long categoryid)
    {
        this.categoryid = categoryid;

    }

    /**
     * Veritabanından kategorileri çekip Categoryy nesnesine dönüştürme işlemi
     *
     * @return
     */
    public void action(AjaxBehaviorEvent e)
    {
        initCategories();
    }

    /**
     * Kategori ekleme işlemi
     *
     * @return
     */
    public void newCategory(AjaxBehaviorEvent e)
    {

        if (categoryNewName != null)
        {
            HttpSession session = SessionUtils.getSession();
            this.restaurantid = (long) session.getAttribute("restaurantid");

            if (categoryDAO == null)
            {
                categoryDAO = new CategoryDAO();
            }

            Category categor = categoryDAO.addCategory(getCategoryNewName(), restaurantid);

            if (categor != null)
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "INFO",
                                "Kategori ekleme başarılı.\nEklenilen kategori numarası:" + categor.getId()));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "ERROR",
                                "Kategori eklenirken bir hata oluştu"));
            }
        }

        categoryNewName = "";

    }
    public void deleteCategory(AjaxBehaviorEvent e)
    {
        try
        {
            categoryDAO = new CategoryDAO();
            boolean deleteCategory = categoryDAO.deleteCategory(categoryid);
            if (deleteCategory)
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "INFO",
                                String.valueOf(categoryid) + " numaralı kategori başarıyla silindi"));
                categoryid = 0;
            }
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "ERROR",
                            categoryid + " numaralı kategori silinirken bir hata oluştu.\nBu numaraya sahip bir kategori olmayabilir"));
        }

    }

    public void updateCategory(AjaxBehaviorEvent e)
    {
        try
        {
            categoryDAO = new CategoryDAO();
            Category upCategory = new Category().setId(categoryid).setName(categoryNewName).setRestaurantid(restaurantid);
            categoryDAO.updateCategory(upCategory);

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "INFO",
                            categoryid + " numaralı kategori başarıyla güncellendi"));
            categoryid = 0;
            initCategories();

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "ERROR",
                            categoryid + "numaralı kategori güncellenirken bir hata oluştu \n Bu numaraya sahip bir kategori olmayabilir"));
        }
        finally
        {
            categoryNewName = "";
        }
    }

    private void initCategories()
    {
        categoryDAO = new CategoryDAO();
        HttpSession session = SessionUtils.getSession();
        this.restaurantid = (long) session.getAttribute("restaurantid");

        List<Category> categorys = categoryDAO.getAllCategories(restaurantid);

        category = new Categoryy[categorys.size()];

        for (int i = 0; i < categorys.size(); i++)
        {
            category[i] = new Categoryy(categorys.get(i).getId().toString(), categorys.get(i).getName());
        }
    }

    public Categoryy[] getCategory()
    {
        return category;
    }

    public static class Categoryy
    {

        String id;
        String value;

        public Categoryy()
        {
        }

        public Categoryy(String id, String value)
        {
            this.id = id;
            this.value = value;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

    }
}
