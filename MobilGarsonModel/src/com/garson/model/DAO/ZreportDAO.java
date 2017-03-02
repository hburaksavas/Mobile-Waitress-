package com.garson.model.DAO;

import com.garson.model.entity.Bill;
import com.garson.model.entity.Zreport;
import com.google.gson.Gson;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

public class ZreportDAO extends DAO<Zreport>
{

    public ZreportDAO()
    {
        super();
    }

    public Zreport reportDay(long restaurantid)
    {

        List<Zreport> zreports = getZreportByDate(restaurantid);

        if (zreports != null && zreports.size() > 0)
        {
            return null;
        }

        BillDAO dao = new BillDAO();
        double total = 0d;
        Zreport zreport = null;
        try
        {
            List<Bill> listBill = dao.getRestaurantBillsByDate(restaurantid, getCurrentDate());

            total = listBill.stream().map((bill) -> bill.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);

            Gson gson = new Gson();
            String jsonBills = gson.toJson(listBill);

            zreport = new Zreport()
                    .setIncome(total)
                    .setRestaurantid(restaurantid)
                    .setBills(jsonBills)
                    .setDate(getCurrentDate())
                    .setClock(getCurrentClock());

            return add(zreport);

        } catch (Exception ex)
        {

            return zreport;
        }

    }

    public Zreport updateZreport(Zreport zreport)
    {
        return update(zreport);
    }

    public boolean deleteZreport(long zreportid)
    {
        return delete(Zreport.class, zreportid);
    }

    public List<Zreport> getRestaurantZReports(long restaurantid)
    {
        return getListByCriteria(Zreport.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public Zreport getZreportById(long id)
    {
        return findByID(Zreport.class, id);
    }

    public List<Zreport> getZreportByDate(long restaurantid)
    {
        String date = getCurrentDate();
        Criterion c1 = Restrictions.eq("restaurantid", restaurantid);
        Criterion c2 = Restrictions.eq("date", date);
        LogicalExpression and = Restrictions.and(c1, c2);
        return getListWithLogicalExp(Zreport.class, and);
    }
}
