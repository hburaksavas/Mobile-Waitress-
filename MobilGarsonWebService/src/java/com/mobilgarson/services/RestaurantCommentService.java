/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.services;

import com.garson.model.DAO.DAO;
import com.garson.model.DAO.RestaurantCommentsDAO;
import com.garson.model.entity.RestaurantComments;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("restaurantcomment")
public class RestaurantCommentService extends RestaurantCommentsDAO
{

    @Context
    private UriInfo context;

    public RestaurantCommentService()
    {
    }

    @PUT
    @Path("comments")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public RestaurantComments createComment(@FormParam("userid") long userid,
            @FormParam("restaurantid") long restid,
            @FormParam("comment") String comment)
    {
        RestaurantComments restComment = new RestaurantComments()
                .setClock(DAO.getCurrentClock())
                .setComment(comment)
                .setDate(DAO.getCurrentDate())
                .setRestaurantid(restid)
                .setUserid(userid);
        return addComment(restComment);
    }

    @GET
    @Path("comments/restaurant/{restaurantid}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public java.util.List<RestaurantComments> getRestaurantCommentList(@PathParam("restaurantid") long id)
    {
        return getRestaurantComments(id);
    }

    @DELETE
    @Path("comments")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String deleteComments(@FormParam("userid") long userid, @FormParam("commentid") long id)
    {
        RestaurantComments comment = getComment(id);
        if (comment.getId().equals(userid))
        {
            deleteComment(id);
            return "Yorum silindi";
        }
        else
            return "Başkasına ait yorum silinemez";
    }
}
