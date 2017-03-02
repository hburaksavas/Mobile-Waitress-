package com.mobilgarson.managedbeans;

import com.garson.model.DAO.DAO;
import com.garson.model.DAO.ZreportDAO;
import com.garson.model.entity.Bill;
import com.garson.model.entity.Order;
import com.garson.model.entity.Zreport;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "zreportBean")
@SessionScoped
public class ZreportBean implements Serializable
{

    private List<Zreport> zreportList;
    private List<Bill> billList;
    private List<Order> ordersList;

    private Zreport selectedZReport;

    private int orderCount;
    private int billCount;

    public Zreport getSelectedZReport()
    {
        return selectedZReport;
    }

    public void setSelectedZReport(Zreport selectedZReport)
    {
        this.selectedZReport = selectedZReport;
    }

    private ZreportDAO reportDAO;
    private long restaurantid;

    public ZreportBean()
    {
    }

    private void checkDAO()
    {
        if (reportDAO == null)
        {
            reportDAO = new ZreportDAO();
        }
        if (restaurantid == 0)
        {
            HttpSession session = SessionUtils.getSession();
            restaurantid = (long) session.getAttribute("restaurantid");
        }
    }

    public void initReportList(AjaxBehaviorEvent e)
    {
        checkDAO();
        zreportList = reportDAO.getRestaurantZReports(restaurantid);
    }

    public void zreportDetails(Zreport report)
    {
        selectedZReport = report;

        try
        {
            orderCount = 0;
            ordersList = new ArrayList<>();
            billList = DAO.getListFromJSON(Bill[].class, report.getBills());
            List<Order> orderList;

            if (billList != null)
            {
                billCount = billList.size();

                for (Bill bill : billList)
                {
                    orderList = DAO.getListFromJSON(Order[].class, bill.getOrders());

                    for (Order order : orderList)
                    {
                        ordersList.add(order);
                    }

                    orderCount += orderList.size();
                }
            }

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN",
                            "Gösterilecek detay yok"));
            Order order = new Order().setId(0l)
                    .setClock("00:00")
                    .setDate("00-00-0000")
                    .setPiece(0).setMasaid(0).setRestaurantid(restaurantid).setStatu("OK")
                    .setUrunid(0l);
            ordersList.add(order);
            ordersList.add(order);
            orderCount = 0;
            billCount = 0;
        }

    }

    public void zreportCreate(AjaxBehaviorEvent e)
    {
        checkDAO();
        selectedZReport = reportDAO.reportDay(restaurantid);
        if (selectedZReport == null)
        {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("hideform('zreportForm')");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN",
                            "Bir gün içerisinde sadece bir kere gün sonu yapılabilir"));

        }
        else
        {
            zreportDetails(selectedZReport);
        }
    }

    public int getBillCount()
    {
        return billCount;
    }

    public void setBillCount(int billCount)
    {
        this.billCount = billCount;
    }

    public List<Order> getOrdersList()
    {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList)
    {
        this.ordersList = ordersList;
    }

    public int getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(int orderCount)
    {
        this.orderCount = orderCount;
    }

    public List<Bill> getBillList()
    {
        return billList;
    }

    public void setBillList(List<Bill> billList)
    {
        this.billList = billList;
    }

    public List<Zreport> getZreportList()
    {
        return zreportList;
    }

    public void setZreportList(List<Zreport> zreportList)
    {
        this.zreportList = zreportList;
    }

}
