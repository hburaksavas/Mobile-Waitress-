
package com.garson.model.DAO;

import com.garson.model.entity.Category;
import java.util.List;
import org.hibernate.criterion.Restrictions;

public class CategoryDAO extends DAO<Category> {

    public CategoryDAO() {
        super();
    }

    public Category addCategory(String name, long restauranID) {

        if (name == null || restauranID < 1) {
            return null;
        }

        Category cat = new Category();
        cat.setName(name);
        cat.setRestaurantid(restauranID);

        return add(cat);
    }

    public Category updateCategory(Category category) {

        if (category == null) {
            return category;
        }

        return update(category);
    }

    public boolean deleteCategory(long id) {
        return delete(Category.class, id);
    }

    public List<Category> getAllCategories(long restaurantID) {
        return getListByCriteria(Category.class, Restrictions.eq("restaurantid", restaurantID));
    }
    
    public Category getByID(long id){
        return findByID(Category.class, id);
    }
}
