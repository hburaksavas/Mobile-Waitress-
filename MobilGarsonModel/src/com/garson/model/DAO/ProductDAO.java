package com.garson.model.DAO;

import com.garson.model.entity.Product;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

public class ProductDAO extends DAO<Product> {

    public ProductDAO() {
        super();
    }

    public Product addProduct(Product product) {
        return add(product);
    }

    public Product updateProduct(Product product) {
        return update(product);
    }

    public boolean deleteProduct(long id) {
        return delete(Product.class, id);
    }

    public List<Product> getRestaurantProducts(long restaurantid) {
        return getListByCriteria(Product.class, Restrictions.eq("restaurantid", restaurantid));
    }

    public List<Product> getRestaurantProductsByCategory(long restaurantid, long categoryid) {

        Criterion cr1 = Restrictions.eq("categoryid", categoryid);
        Criterion cr2 = Restrictions.eq("restaurantid", restaurantid);
        LogicalExpression andExp = Restrictions.and(cr1, cr2);

        return getListWithLogicalExp(Product.class, andExp);

    }

    public boolean updateStock(long restaurantid, long productid, long stock) {
        String hql = "UPDATE Product p SET p.stock=:stock WHERE p.id=:productid AND p.restaurantid=:resid";
        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("stock", stock);
        query.setParameter("productid", productid);
        query.setParameter("resid", restaurantid);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public Product updateScore(int score, long id) {

        Product p = getByID(id);
        
        int votes;
        double newScore;

        if (p.getVoteCount() != null) {
            votes = p.getVoteCount();
            newScore = ((p.getScore() * votes) + score) / (++votes);
        } else {
            votes = 1;
            newScore = score;
        }

        p = p.setScore(newScore).setVoteCount(votes);

        return updateProduct(p);
    }

    public Product getByID(long id) {
        return findByID(Product.class, id);
    }
}
