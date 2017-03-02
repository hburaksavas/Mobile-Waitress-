/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.garson.model.DAO.AddressDAO;
import com.garson.model.DAO.EmployeeDAO;
import com.garson.model.entity.Address;
import com.garson.model.entity.Employee;
import com.mobilgarson.managedbeans.helper.SessionUtils;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable
{

    private List<Employee> employeeList;
    private List<Employee> filteredEmployeeList;
    private long addressid;
    private Long empid;

    private String selectedEmployeeTcNo;
    private String city;
    private String district;
    private String street;
    private String addresstext;
    private String name;
    private String surname;
    private String tcNo;
    private String phone;
    private String mail;
    private String password;

    private EmployeeDAO employeeDAO;

    public EmployeeBean()
    {
    }

    public void updateEmployee(AjaxBehaviorEvent event)
    {
        checkDAO();
        try
        {
            HttpSession session = SessionUtils.getSession();
            long restaurantid = (long) session.getAttribute("restaurantid");
            Address adres;
            if (addressid > 0)
            {
                adres = new Address().setAdresstext(addresstext)
                        .setCity(city)
                        .setDistrict(district)
                        .setStreet(street)
                        .setId(addressid);

                adres = new AddressDAO().updateAddres(adres);
            }
            else
            {
                adres = new Address().setAdresstext(addresstext)
                        .setCity(city)
                        .setDistrict(district)
                        .setStreet(street);

                adres = new AddressDAO().addAddress(adres);
            }

            Employee employee = new Employee()
                    .setMail(getMail())
                    .setName(getName())
                    .setSurname(getSurname())
                    .setPassword(getPassword())
                    .setPhone(getPhone())
                    .setRestaurantid(restaurantid)
                    .setTcNo(getTcNo())
                    .setAdressid(adres.getId())
                    .setId(empid);

            Employee updateEmployee = new EmployeeDAO().updateEmployee(employee);

            if (updateEmployee != null && updateEmployee.getId() > 0)
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Info",
                                "Çalışan başarıyla güncellendi, çalışan numarası:" + updateEmployee.getId().toString()
                        ));
                initList();
                clearFields();
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Warn",
                                "Çalışan ekleme başarısız, birşeyler ters gitti"
                        ));

            }
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Beklenmedik bir hata oluştu," + ex.getMessage()
                    ));
        }
    }

    public void deleteEmployee(AjaxBehaviorEvent event)
    {
        checkDAO();

        try
        {
            Employee employe = employeeDAO.getByID(empid);

            if (employe != null)
            {
                if (employe.getAdressid() != null)
                {
                    if (employe.getAdressid() > 0)
                    {
                        AddressDAO dao = new AddressDAO();
                        dao.deleteAddress(employe.getAdressid());
                    }

                }

            }

            employeeDAO.deleteEmployee(empid);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info",
                            "Çalışan başarıyla silindi"
                    ));

            empid = 0l;
            initList();
            clearFields();

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info",
                            "Bir hata oluştu:" + ex.getMessage()
                    ));

            initList();
            clearFields();
        }

    }

    public void createEmployee(AjaxBehaviorEvent event)
    {
        checkDAO();
        try
        {
            HttpSession session = SessionUtils.getSession();
            long restaurantid = (long) session.getAttribute("restaurantid");
            Address adres = new Address().setAdresstext(addresstext)
                    .setCity(city)
                    .setDistrict(district)
                    .setStreet(street);
            new AddressDAO().addAddress(adres);

            Employee employee = new Employee()
                    .setMail(getMail())
                    .setName(getName())
                    .setSurname(getSurname())
                    .setPassword(getPassword())
                    .setPhone(getPhone())
                    .setRestaurantid(restaurantid)
                    .setTcNo(getTcNo())
                    .setAdressid(adres.getId());
            Employee addEmployee = new EmployeeDAO().addEmployee(employee);

            if (addEmployee != null && addEmployee.getId() > 0)
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Info",
                                "Çalışan başarıyla eklendi, çalışan numarası:" + addEmployee.getId().toString()
                        ));
                initList();
                clearFields();
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Warn",
                                "Çalışan ekleme başarısız, birşeyler ters gitti"
                        ));

            }
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Beklenmedik bir hata oluştu," + ex.getMessage()
                    ));
        }

    }

    public void initEmployeeList(AjaxBehaviorEvent event)
    {
        initList();
    }

    public void fillFields(AjaxBehaviorEvent e)
    {

        checkDAO();
        clearFields();
        Employee employee = null;

        try
        {
            employee = employeeDAO.getByID(empid);
            this.mail = employee.getMail();
            this.name = employee.getName();
            this.password = employee.getPassword();
            this.phone = employee.getPhone();
            this.surname = employee.getSurname();
            this.tcNo = employee.getTcNo();
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Warn",
                            ex.getMessage()));
        }

        if (employee != null)
        {
            try
            {

                AddressDAO dao = new AddressDAO();
                Address adres = dao.getByID(employee.getAdressid());
                if (adres != null)
                {
                    this.addresstext = adres.getAdresstext();
                    this.city = adres.getCity();
                    this.district = adres.getDistrict();
                    this.street = adres.getStreet();
                    this.addressid = adres.getId();
                }

            } catch (Exception ex)
            {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Warn",
                                ex.getMessage()));
            }
        }

    }

    private void clearFields()
    {
        this.addresstext = "";
        this.city = "";
        this.district = "";
        this.mail = "";
        this.name = "";
        this.password = "";
        this.phone = "";
        this.selectedEmployeeTcNo = "";
        this.street = "";
        this.surname = "";
        this.tcNo = "";
        this.addressid = 0;
    }

    private void initList()
    {
        checkDAO();
        HttpSession session = SessionUtils.getSession();
        long restaurantid = (long) session.getAttribute("restaurantid");
        employeeList = employeeDAO.getRestaurantEmployees(restaurantid);
    }

    private void checkDAO()
    {
        if (employeeDAO == null)
        {
            employeeDAO = new EmployeeDAO();
        }
    }

    public Long getEmpid()
    {
        return empid;
    }

    public void setEmpid(Long empid)
    {
        this.empid = empid;
    }

    public List<Employee> getEmployeeList()
    {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList)
    {
        this.employeeList = employeeList;
    }

    public List<Employee> getFilteredEmployeeList()
    {
        return filteredEmployeeList;
    }

    public void setFilteredEmployeeList(List<Employee> filteredEmployeeList)
    {
        this.filteredEmployeeList = filteredEmployeeList;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getTcNo()
    {
        return tcNo;
    }

    public void setTcNo(String tcNo)
    {
        this.tcNo = tcNo;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSelectedEmployeeTcNo()
    {
        return selectedEmployeeTcNo;
    }

    public void setSelectedEmployeeTcNo(String selectedEmployeeTcNo)
    {
        this.selectedEmployeeTcNo = selectedEmployeeTcNo;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAddresstext()
    {
        return addresstext;
    }

    public void setAddresstext(String addresstext)
    {
        this.addresstext = addresstext;
    }

}
