/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.services;

import com.garson.model.DAO.ReservationDAO;
import com.garson.model.entity.Reservation;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author BurakS
 */
@Path("reservationservice")
public class ReservationService extends ReservationDAO {

    @Context
    private UriInfo context;

    public ReservationService() {
    }

    @GET
    @Path("reservation/user/{userid}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Reservation> getUserReservations(@PathParam("userid") long id) {
        return getReservationByUser(id);
    }

    @GET
    @Path("reservation/restaurant/{restaurantid}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Reservation> getRestaurantsReservations(@PathParam("restaurantid") long id) {
        return getRestaurantReservations(id);
    }

    @PUT
    @Path("reservation/restaurant")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Reservation createReservation(@FormParam("restaurantid") long id,
            @FormParam("date") String date,
            @FormParam("clock") String clock,
            @FormParam("userid") long userid,
            @FormParam("name") String name,
            @FormParam("surname") String surname,
            @FormParam("tableid") long tableid) {
        
        Reservation rest = null;
        
        try {
            rest = getReservationByDateClock(date, clock, id, tableid);
            if (rest != null) {
                return new Reservation().setId(-1l);
            }

        } catch (Exception e) {
            return new Reservation().setId(-1l);
        }

        Reservation res = new Reservation()
                .setClock(clock)
                .setDate(date)
                .setClock(clock)
                .setDinnertableid(tableid)
                .setRestaurantid(id)
                .setUsername(surname)
                .setUsersurname(surname);

        return addReservation(res);
    }

    @POST
    @Path("reservation/restaurant")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Reservation updateeReservation(@FormParam("restaurantid") long id,
            @FormParam("date") String date,
            @FormParam("clock") String clock,
            @FormParam("userid") long userid,
            @FormParam("name") String name,
            @FormParam("surname") String surname,
            @FormParam("tableid") long tableid) {
        Reservation rest = getReservationByDateClock(date, clock, id, tableid);
        if (rest != null) {
            return new Reservation().setId(-1l);
        }

        Reservation res = new Reservation()
                .setClock(clock)
                .setDate(date)
                .setClock(clock)
                .setDinnertableid(tableid)
                .setRestaurantid(id)
                .setUsername(surname)
                .setUsersurname(surname);

        return updateReservation(res);
    }

    @DELETE
    @Path("reservation/{reservid}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String cancelReservation(@PathParam("reservid") long id) {

        deleteReservation(id);
        return "Rezervation is cancelled";

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String putJson() {
        return "Reservation Service";
    }
}
