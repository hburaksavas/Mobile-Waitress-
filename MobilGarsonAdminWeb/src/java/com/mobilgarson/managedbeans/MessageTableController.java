/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.garson.model.DAO.CategoryDAO;
import com.garson.model.entity.Category;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class MessageTableController implements Serializable
{

    private static final long serialVersionUID = 20111020L;

    private List<Category> messages;
    private List<Category> filteredMessages;
    private String newID;
    private String newName;

    public MessageTableController()
    {

    }

    public void initTables(AjaxBehaviorEvent e)
    {
        categoryListAdapter();
    }

    public void categoryListAdapter()
    {
        HttpSession session = SessionUtils.getSession();
        long id = (long) session.getAttribute("restaurantid");
        CategoryDAO dao = new CategoryDAO();
        messages = dao.getAllCategories(id);
    }

    public List<Category> getMessages()
    {
        return messages;
    }

    public void setMessages(final List<Category> messages)
    {
        this.messages = messages;
    }

    public List<Category> getFilteredMessages()
    {
        return filteredMessages;
    }

    public void setFilteredMessages(List<Category> filteredMessages)
    {
        this.filteredMessages = filteredMessages;
    }

    public void setNewID(String newID)
    {
        this.newID = newID;
    }

    public String getNewID()
    {
        return newID;
    }

    public void setNewName(String newName)
    {
        this.newName = newName;
    }

    public String getNewName()
    {
        return newName;
    }

}
