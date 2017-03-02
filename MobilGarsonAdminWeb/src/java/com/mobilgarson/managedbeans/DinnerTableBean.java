/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.garson.model.DAO.DinnerTableDAO;
import com.garson.model.entity.DinnerTable;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "tableBean")
@SessionScoped
public class DinnerTableBean implements Serializable
{

    private DinnerTableDAO tableDAO;
    private List<DinnerTable> dinnerTableList;
    private List<DinnerTable> filteredList;

    private long tableid;
    private long restaurantid;
    private String tablename;
    private int capacity;

    public DinnerTableBean()
    {
    }

    public void initTableList(AjaxBehaviorEvent e)
    {
        initTableList();
    }

    private void initTableList()
    {
        HttpSession session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");
        tableDAO = new DinnerTableDAO();
        dinnerTableList = tableDAO.getRestaurantTables(restaurantid);
    }

    public void addTable(AjaxBehaviorEvent e)
    {
        HttpSession session = SessionUtils.getSession();
        restaurantid = (long) session.getAttribute("restaurantid");

        tableDAO = new DinnerTableDAO();

        DinnerTable table = new DinnerTable();
        table.setCapacity(capacity)
                .setName(tablename)
                .setStatu(0)
                .setRestaurantid(restaurantid);

        table = tableDAO.addDinnerTable(table);

        if (table != null && table.getId() > 0)
        {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info",
                            "Masa başarıyla eklendi. Masa bilgileri:\nMasa No:" + table.getId()
                            + "\nMasa Adı:" + table.getName()
                            + "\nKapasite:" + table.getCapacity()
                    ));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Warn",
                            "Masa eklenirken bir hata oluştu. Aynı isme sahip başka bir masa olmadığından emin olunuz."
                    ));
        }
        tablename = "";
        initTableList();
    }

    public void updateTable(AjaxBehaviorEvent e)
    {
        if (tableDAO == null)
            tableDAO = new DinnerTableDAO();

        DinnerTable table;
        try
        {
            table = tableDAO.getByID(tableid);
            if (table != null)
            {
                table.setCapacity(capacity);
                table.setName(tablename);
                tableDAO.updateDinnerTable(table);

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Info",
                                "Masa bilgileri başarıyla güncellendi"));
                capacity = 0;
                tablename = "";
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Warn",
                                "Bu numaraya sahip bir masa yoktur "));
            }

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Warn",
                            "Bu numaraya sahip bir masa yoktur "));
        }
    }

    public void deleteTable(AjaxBehaviorEvent e)
    {

        if (tableDAO == null)
            tableDAO = new DinnerTableDAO();

        DinnerTable table;
        try
        {
            table = tableDAO.getByID(tableid);
            if (table != null)
            {
                if (tableDAO.deleteDinnerTable(tableid))
                {

                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Info",
                                    "Masa Silme Başarılı"));
                    tableid = 0;
                }
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Warn",
                                "Bu numaraya sahip bir masa yoktur "));
            }

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Warn",
                            "Bu numaraya sahip bir masa yoktur "));
        }

    }

    public void setFilteredList(List<DinnerTable> filteredList)
    {
        this.filteredList = filteredList;
    }

    public List<DinnerTable> getFilteredList()
    {
        return filteredList;
    }

    public String getTablename()
    {
        return tablename;
    }

    public void setTablename(String tablename)
    {
        this.tablename = tablename;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public List<DinnerTable> getDinnerTableList()
    {
        return dinnerTableList;
    }

    public void setDinnerTableList(final List<DinnerTable> dinnerTableList)
    {
        this.dinnerTableList = dinnerTableList;
    }

    public void setTableid(long tableid)
    {
        this.tableid = tableid;
    }

    public long getTableid()
    {
        return tableid;
    }

}
