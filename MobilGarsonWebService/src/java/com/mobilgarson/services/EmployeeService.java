package com.mobilgarson.services;

import com.garson.model.DAO.EmployeeDAO;
import com.garson.model.entity.Employee;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("employeeservice")
public class EmployeeService extends EmployeeDAO
{

    @Context
    private UriInfo context;

    public EmployeeService()
    {
    }

    @GET
    @Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Employee getEmployee(@PathParam("id") long id)
    {
        if (id < 1)
            return null;

        return getByID(id);
    }

    @GET
    @Path("employee/restaurant/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Employee> getRestaurantsEmployeeList(@PathParam("id") long id)
    {
        if (id < 1)
            return null;
        return getRestaurantEmployees(id);
    }

    
    /**
     * Score maximum [10] minimum [1] olabilir
     *
     * @param score
     * @param empid
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void scoreEmployee(
            @FormParam("scoremaxten") int score,
            @FormParam("employeeid") long empid)
    {
        updateScore(empid, score);
    }

}
