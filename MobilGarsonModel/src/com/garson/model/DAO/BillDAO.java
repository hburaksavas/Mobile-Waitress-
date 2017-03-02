package com.garson.model.DAO;

import com.garson.model.entity.Bill;
import com.garson.model.entity.Order;
import com.garson.model.entity.Product;
import com.google.gson.Gson;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

public class BillDAO extends DAO<Bill>
{

    public BillDAO()
    {
        super();
    }

    public Bill addBill(long dinnertableid, long restaurantid)
    {

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getRestaurantOrderByDinnerTableid(restaurantid, dinnertableid);

        if (orders.size() < 1)
            return null;

        ProductDAO proDAO = new ProductDAO();

        double total = 0d;

        //Toplam hesap tutarını hesaplama
        for (Order o : orders)
        {

            if (o.getStatu().equals("Ödendi"))
            {
                Product product = proDAO.getByID(o.getUrunid());

                total += product.getPrice() * o.getPiece();
            }
        }

        Gson gson = new Gson();
        String jsonOrder;
        jsonOrder = gson.toJson(orders);
        Bill bill = new Bill().setAmount(total)
                .setOrders(jsonOrder)
                .setRestaurantid(restaurantid)
                .setDate(getCurrentDate())
                .setClock(getCurrentClock());

        add(bill);

        //ekleme işlemi başarılı ise, siparişleri veritabanından silme işlemi
        if (bill != null)
        {

            orders.stream().filter((o) -> (o.getStatu().equals("Ödendi"))).forEachOrdered((o) ->
            {
                orderDAO.deleteOrder(o.getId());
            });

        }

        return bill;
    }

    public Bill updateBill(Bill bill)
    {

        return update(bill);
    }

    public boolean deleteBill(long id)
    {

        return delete(Bill.class, id);

    }

    public List<Bill> getRestaurantBills(long restaurantid)
    {
        return getListByCriteria(Bill.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public List<Bill> getRestaurantBillsByDate(long restaurantid, String date)
    {
        Criterion c1 = Restrictions.eq("restaurantid", restaurantid);
        Criterion c2 = Restrictions.eq("date", date);
        LogicalExpression and = Restrictions.and(c1, c2);
        return getListWithLogicalExp(Bill.class, and);

    }
}
