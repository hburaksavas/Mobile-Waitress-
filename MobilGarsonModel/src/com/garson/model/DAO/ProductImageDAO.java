package com.garson.model.DAO;

import com.garson.model.entity.ProductImages;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class ProductImageDAO extends DAO<ProductImages>
{

    public ProductImageDAO()
    {
        super();
    }

    public ProductImages addProductImage(ProductImages image)
    {
        return add(image);
    }

    public ProductImages updateProductImages(ProductImages image)
    {
        return update(image);
    }

    public boolean deleteProductImages(long id)
    {
        return delete(ProductImages.class, id);
    }

    public boolean deleteProductImagesByProductId(long productid)
    {
        String hql = "DELETE FROM ProductImages WHERE productid = :restid";
        session = getOpenedSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("restid", productid);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public List<ProductImages> getProductsImagesListById(long productid)
    {
        return getListByCriteria(ProductImages.class, Restrictions.eq("productid", productid));
    }

    public ProductImages getProductImagesById(long id)
    {
        return findByID(ProductImages.class, id);
    }
}
