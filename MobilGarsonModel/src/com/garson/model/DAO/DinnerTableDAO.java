package com.garson.model.DAO;

import com.garson.model.entity.DinnerTable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

public class DinnerTableDAO extends DAO<DinnerTable>
{

    public DinnerTableDAO()
    {
        super();
    }

    public DinnerTable addDinnerTable(DinnerTable table)
    {
        DinnerTable table2 = getTableByNameAndRestaurantID(table.getRestaurantid(), table.getName());

        if (table2 == null)
        {
            return add(table);
        }
        else
        {
            return null;
        }
    }

    public DinnerTable updateDinnerTable(DinnerTable table)
    {

        return update(table);

    }

    public boolean deleteDinnerTable(long tableid)
    {
        return delete(DinnerTable.class, tableid);
    }

    public List<DinnerTable> getRestaurantTables(long restaurantID)
    {
        return getListByCriteria(DinnerTable.class, Restrictions.eq("restaurantid", restaurantID));
    }

    /**
     * statu; 0 = BOŞ,1 = DOLU
     *
     * @param restaurantID
     * @param statu
     * @return
     */
    public List<DinnerTable> getRastauranTablesByStatu(long restaurantID, int statu)
    {

        Criterion cr1 = Restrictions.eq("statu", statu);
        Criterion cr2 = Restrictions.eq("restaurantid", restaurantID);
        LogicalExpression andExp = Restrictions.and(cr1, cr2);

        return getListWithLogicalExp(DinnerTable.class, andExp);

    }

    public DinnerTable getByID(long id)
    {
        return findByID(DinnerTable.class, id);
    }

    public long getRestaurantIdByDinnerTableId(long id)
    {
        DinnerTable table = getByID(id);
        return table.getRestaurantid();
    }

    private DinnerTable getTableByNameAndRestaurantID(long restaurantid, String name)
    {

        Criterion c1 = Restrictions.eq("restaurantid", restaurantid);
        Criterion c2 = Restrictions.eq("name", name);

        LogicalExpression exp = Restrictions.and(c1, c2);

        List<DinnerTable> list = getListWithLogicalExp(DinnerTable.class, exp);

        return list != null
                ? (list.size() > 0 ? list.get(0) : null) : null;

    }

}
