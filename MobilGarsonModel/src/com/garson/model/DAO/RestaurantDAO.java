/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Restaurant;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author BurakS
 */
public class RestaurantDAO extends DAO<Restaurant> {

    public RestaurantDAO() {
        super();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return add(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        return update(restaurant);
    }

    public boolean deleteRestaurant(long restaurantid) {
        return delete(Restaurant.class, restaurantid);
    }

    public List<Restaurant> getRestaurants() {
        return getAllRecords(Restaurant.class);
    }

    public boolean updateScoreAndVoteCount(double score, int voteCount, long id) {

        String hql = "UPDATE Restaurant SET voteCount = :voteCount , serviceScore = :score WHERE id = :empId";

        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("score", score);
        query.setParameter("voteCount", voteCount);
        query.setParameter("empId", id);

        int up = query.executeUpdate();
        session.getTransaction().commit();
        return up > 0;
    }

    public Restaurant getByID(long id) {
        return findByID(Restaurant.class, id);
    }

    public Restaurant updateScore(long id, int score) {

        Restaurant rest = getByID(id);

        int voteCount;
        double newScore;

        if (rest.getVoteCount() != null) {
            voteCount = rest.getVoteCount();
            newScore = ((rest.getServiceScore() * voteCount) + score) / (++voteCount);
        } else {
            voteCount = 1;
            newScore = score;
        }

        String hql = "UPDATE Restaurant SET voteCount = :voteCount , serviceScore = :score WHERE id = :empId";

        session = getOpenedSession();
        session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("score", newScore);
        query.setParameter("voteCount", voteCount);
        query.setParameter("empId", id);

        int up = query.executeUpdate();
        session.getTransaction().commit();
       

        return rest
                .setServiceScore(newScore)
                .setVoteCount(voteCount);
    }

}
