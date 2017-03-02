package com.mobilgarson.services;

import com.garson.model.DAO.AddressDAO;
import com.garson.model.DAO.UserDAO;
import com.garson.model.entity.Address;
import com.garson.model.entity.User;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("userservice")
public class UserService extends UserDAO
{

    @Context
    private UriInfo context;

    public UserService()
    {
    }

    @PUT
    @Path("user/create")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User createUser(@FormParam("mail") String mail,
            @FormParam("name") String name,
            @FormParam("surname") String surname,
            @FormParam("password") String password,
            @FormParam("phone") String phone,
            @FormParam("gender") int gender,
            @FormParam("sehir") String city,
            @FormParam("ilce") String district,
            @FormParam("mahalle") String street,
            @FormParam("sokaknodaire") String adrestext)
    {

        AddressDAO dao = new AddressDAO();
        Address adres = new Address()
                .setCity(city)
                .setDistrict(district)
                .setStreet(street)
                .setAdresstext(adrestext);
        adres = dao.addAddress(adres);

        User user = new User()
                .setAddresid(adres.getId())
                .setGender(gender)
                .setMail(mail)
                .setName(name)
                .setPassword(password)
                .setPhone(phone)
                .setSurname(surname);
        return addUser(user);

    }

    @POST
    @Path("user/update")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User updatesUser(@FormParam("userid") long userid, @FormParam("mail") String mail,
            @FormParam("name") String name,
            @FormParam("surname") String surname,
            @FormParam("password") String password,
            @FormParam("phone") String phone,
            @FormParam("gender") int gender)
    {
        User user = findByID(User.class, userid);
        user.setGender(gender)
                .setMail(mail)
                .setName(name)
                .setName(name)
                .setPassword(password)
                .setPhone(phone)
                .setSurname(surname);

        return updateUser(user);

    }

    @GET
    @Path("user/{userid}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public User getUserById(@PathParam("userid") long userid)
    {

        return findByID(User.class, userid);
    }

    @POST
    @Path("user/login")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User loginService(@FormParam("mail") String mail, @FormParam("password") String password)
    {
        return login(mail, password);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getJson()
    {
        return "User Service";
    }
    
    @GET
    @Path("user/address/{addressid}")
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public Address getAdressByID(@PathParam("addressid") long id){
        
        return new AddressDAO().getByID(id);
        
    }
}
