package com.mobilgarson.homebeans;

import com.garson.model.DAO.BillDAO;
import com.garson.model.DAO.DinnerTableDAO;
import com.garson.model.DAO.ProductDAO;
import com.garson.model.DAO.RequestDAO;
import com.garson.model.DAO.RestaurantDAO;
import com.garson.model.entity.Bill;
import com.garson.model.entity.DinnerTable;
import com.garson.model.entity.Order;
import com.garson.model.entity.Request;
import com.garson.model.entity.Restaurant;
import com.mobilgarson.homebeans.service.OrderItem;
import com.mobilgarson.homebeans.service.OrderService;
import com.mobilgarson.homebeans.service.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "homeBean")
@SessionScoped
public final class HomeBean implements Serializable
{

    private long restaurantid;
    private String restaurantName;

    private List<OrderItem> orderItemList;
    private List<DinnerTable> dinnerTableData;
    private List<OrderItem> tablesOrderItemList;

    private DinnerTableDAO dinnerTableDAO;
    private ProductDAO productDAO;

    private long tableNo;
    private Bill bill;

    public HomeBean()
    {

    }

    @PostConstruct
    public void init()
    {
        try
        {

            HttpSession session = SessionUtils.getSession();
            restaurantid = (long) session.getAttribute("restoran-no");

            initDinnerTableData();
            initOrderItems();
            connectGarsonHomeServer(restaurantid);
            inittRestaurantName(restaurantid);
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(ex.getMessage()));

        }
    }

    private void inittRestaurantName(long id)
    {
        RestaurantDAO dao = new RestaurantDAO();
        Restaurant byID = dao.getByID(id);
        restaurantName = byID.getName();
    }

    public void connectGarsonHomeServer(long restaurantid)
    {
        try
        {
            String jsMethod = "connectGarsonServer('" + restaurantid + "')";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute(jsMethod);

        } catch (Exception ex)
        {

        }

    }

    public void initOrderItems()
    {

        try
        {
            OrderService orderService = new OrderService();

            List<Order> orderList = orderService.getOrders_isNotReady(restaurantid);

            orderItemList = new ArrayList<>();

            for (Order order : orderList)
            {

                OrderItem item = new OrderItem();
                item.setClock(order.getClock());
                item.setOrderCount(order.getPiece());
                item.setOrderNo(order.getId());
                item.setStatu(order.getStatu());

                buildProductDAO();
                String productName = productDAO.getByID(order.getUrunid()).getName();
                item.setProductName(productName);

                buildDinnerTableDAO();
                String tableName = dinnerTableDAO.getByID(order.getMasaid()).getName();
                item.setTableName(tableName);
                item.setTableNo(order.getMasaid());

                orderItemList.add(item);

            }

            Collections.sort(orderItemList, (p1, p2) -> Long.compare(p1.getOrderNo(), p2.getOrderNo()));

        } catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getLocalizedMessage()));
        }

    }

    public void initDinnerTableData()
    {
        buildDinnerTableDAO();
        dinnerTableData = dinnerTableDAO.getRestaurantTables(restaurantid);
    }

    public void initListOrderByTableId(long tableid)
    {
        if (tableid > 0)
        {
            bill = new Bill().setAmount(0d).setRestaurantid(restaurantid);

            OrderService service = new OrderService();
            List<Order> orders = service.getOrders_ByTableId(restaurantid, tableid);

            tablesOrderItemList = new ArrayList<>();

            for (Order order : orders)
            {
                OrderItem item = new OrderItem();
                item.setClock(order.getClock());
                item.setOrderCount(order.getPiece());
                item.setOrderNo(order.getId());
                item.setStatu(order.getStatu());

                buildProductDAO();
                String productName = productDAO.getByID(order.getUrunid()).getName();
                item.setProductName(productName);

                buildDinnerTableDAO();
                String tableName = dinnerTableDAO.getByID(order.getMasaid()).getName();
                item.setTableName(tableName);
                item.setTableNo(order.getMasaid());

                tablesOrderItemList.add(item);
            }
            this.tableNo = tableid; //hesanı kapat için kullanılacak
            String jsMethod = "removeAnimateClass('formTables:bill-" + tableid + "')";
            RequestContext.getCurrentInstance().execute(jsMethod);
        }

    }

    public void remove_OrderItem_If_Its_Ready(long id)
    {

        OrderService service = new OrderService();
        service.updateOrderStatuForReady(id);
        orderItemList.removeIf(p1 -> p1.getOrderNo() == id);

    }

    public void changeTableStatu(long id)
    {
        dinnerTableDAO = new DinnerTableDAO();

        DinnerTable table = dinnerTableDAO.getByID(id);

        if (table.getStatu() == 0)
        {
            openTable(table);
        }
        else
        {
            closeTable(table);
        }
    }

    public void openTable(DinnerTable table)
    {

        try
        {
            table.setStatu(1);
            dinnerTableDAO = new DinnerTableDAO();
            dinnerTableDAO.updateDinnerTable(table);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", table.getName() + " açıldı"));

            String elementId = "formTables:T-" + table.getId();
            String command = "openTable('" + elementId + "')";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute(command);

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN", table.getName() + " açılırken bir sorun oluştu"));

        }

    }

    public void closeTable(DinnerTable table)
    {

        try
        {
            table.setStatu(0);
            dinnerTableDAO = new DinnerTableDAO();
            dinnerTableDAO.updateDinnerTable(table);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", table.getName() + " kapatıldı"));

            String elementId = "formTables:T-" + table.getId();
            String command = "closeTable('" + elementId + "')";
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute(command);

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", table.getName()
                            + " kapatılırken bir sorun oluştu"));
        }

    }

    public void payOneByOne(long orderid, long tableid, int paidCount)
    {
        OrderService service = new OrderService();
        if (paidCount > 0)
        {
            service.orderUpdatePaid(orderid, paidCount);
            initListOrderByTableId(tableid);
        }

    }

    public void closeBill()
    {
        if (tableNo > 0)
        {
            tablesOrderItemList
                    .stream()
                    .filter(
                            (item) -> (!item.getStatu().equals("Ödendi")))
                    .forEachOrdered(
                            (item) ->
                    {
                        OrderService service = new OrderService();
                        service.updateOrderStatuForPaid(item.getOrderNo());
                    });

            BillDAO billDAO = new BillDAO();
            initListOrderByTableId(tableNo);
            bill = billDAO.addBill(tableNo, restaurantid);
        }
    }

    public void deleteRequest(long tableid)
    {
        RequestDAO dao = new RequestDAO();
        List<Request> restaurantRequestList = dao.getRestaurantRequestList(restaurantid);

        restaurantRequestList.stream().filter((request) -> (request.getDinnertableid() == (tableid))).forEachOrdered((request) ->
        {
            dao.deleteRequest(request.getId());
        });

        String jsMethod = "removeAnimateClass('formTables:waitress-" + tableid + "')";
        RequestContext.getCurrentInstance().execute(jsMethod);
    }

    private void buildDinnerTableDAO()
    {

        dinnerTableDAO = new DinnerTableDAO();

    }

    private void buildProductDAO()
    {

        productDAO = new ProductDAO();

    }

    /**
     * Getters And Setters
     */
    public List<OrderItem> getOrderItemList()
    {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList)
    {
        this.orderItemList = orderItemList;
    }

    public List<DinnerTable> getDinnerTableData()
    {
        return dinnerTableData;
    }

    public void setDinnerTableData(List<DinnerTable> dinnerTableData)
    {
        this.dinnerTableData = dinnerTableData;
    }

    public List<OrderItem> getTablesOrderItemList()
    {
        return tablesOrderItemList;
    }

    public void setTablesOrderItemList(List<OrderItem> tablesOrderItemList)
    {
        this.tablesOrderItemList = tablesOrderItemList;
    }

    public Bill getBill()
    {
        return bill;
    }

    public void setBill(Bill bill)
    {
        this.bill = bill;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

}
