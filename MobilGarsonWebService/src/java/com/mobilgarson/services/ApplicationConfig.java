package com.mobilgarson.services;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application
{

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources)
    {
        resources.add(com.mobilgarson.services.BillService.class);
        resources.add(com.mobilgarson.services.CategoryService.class);
        resources.add(com.mobilgarson.services.ComplaintService.class);
        resources.add(com.mobilgarson.services.DinnerTableService.class);
        resources.add(com.mobilgarson.services.EmployeeService.class);
        resources.add(com.mobilgarson.services.ImageService.class);
        resources.add(com.mobilgarson.services.OrderService.class);
        resources.add(com.mobilgarson.services.ProductService.class);
        resources.add(com.mobilgarson.services.RequestService.class);
        resources.add(com.mobilgarson.services.ReservationService.class);
        resources.add(com.mobilgarson.services.RestaurantCommentService.class);
        resources.add(com.mobilgarson.services.RestaurantService.class);
        resources.add(com.mobilgarson.services.UserService.class);
    }

}
