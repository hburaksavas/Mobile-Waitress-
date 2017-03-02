/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilgarson.managedbeans;

import com.garson.model.DAO.DinnerTableDAO;
import com.garson.model.DAO.ReservationDAO;
import com.garson.model.entity.DinnerTable;
import com.garson.model.entity.Reservation;
import com.mobilgarson.managedbeans.helper.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "reservationBean")
@SessionScoped
public class ReservationBean implements Serializable
{

    private ReservationDAO reservationDAO;
    private List<Reservation> reservationList;
    private List<Reservation> filteredReservationList;
    private List<DinnerTable> tableList;

    private long restaurantid = 0l;

    private long selectedTableid;
    private long reservNo;
    private long userno;
    private String name;
    private String surname;
    private String clock;
    private String date;

    public ReservationBean()
    {
    }

    private void checkDAO()
    {
        if (reservationDAO == null)
        {
            reservationDAO = new ReservationDAO();
        }
        if (restaurantid == 0l)
        {
            HttpSession session = SessionUtils.getSession();
            restaurantid = (long) session.getAttribute("restaurantid");
        }
    }

    public void cancelReservation(AjaxBehaviorEvent e)
    {
        if (reservNo < 1)
        {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Info",
                                    "Lütfen önce bir rezervasyon numarası giriniz"));

        }
        else
        {
            try
            {
                checkDAO();

                reservationDAO.deleteReservation(reservNo);

                FacesContext.getCurrentInstance()
                        .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Info",
                                        "Rezervasyon iptali başarıyla tamamlandı."));
                clearFields();

            } catch (Exception ex)
            {
                FacesContext.getCurrentInstance()
                        .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Warn",
                                        "Beklenmedik bir hata oluştu!" + ex.getMessage()));
            }

        }
    }

    public void updateReservation(AjaxBehaviorEvent e)
    {

        if (reservNo < 1)
        {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Info",
                                    "Lütfen önce bir rezervasyon numarası giriniz"));
        }
        else
        {
            checkDAO();

            Reservation reservation = new Reservation();

            reservation.setId(reservNo);
            reservation.setClock(clock);
            reservation.setDate(date);
            reservation.setDinnertableid(selectedTableid);
            reservation.setRestaurantid(restaurantid);
            reservation.setUserid(userno);
            reservation.setUsername(name);
            reservation.setUsersurname(surname);

            reservationDAO.updateReservation(reservation);

            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Info",
                                    "Rezervasyon bilgileri güncellendi"));

            clearFields();
        }

    }

    public void getReservationById(AjaxBehaviorEvent e)
    {
        if (reservNo > 0)
        {
            checkDAO();
            Reservation reservation = reservationDAO.getById(reservNo);

            if (reservation != null)
            {

                clock = reservation.getClock();
                date = reservation.getDate();

                selectedTableid = reservation.getDinnertableid();

                if (reservation.getUserid() != null)
                {
                    userno = reservation.getUserid();
                }

                name = reservation.getUsername();
                surname = reservation.getUsersurname();

                initTableList(e);
            }
            else
            {
                FacesContext.getCurrentInstance()
                        .addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Info",
                                        "Böyle bir rezervasyon kaydı bulunmamaktadır."));

            }

        }
    }

    public void initReservationList(AjaxBehaviorEvent e)
    {
        initReservationList();
    }

    public void initTableList(AjaxBehaviorEvent e)
    {
        checkDAO();
        DinnerTableDAO dao = new DinnerTableDAO();
        tableList = dao.getRestaurantTables(restaurantid);

    }

    public void createReservation(AjaxBehaviorEvent e)
    {
        checkDAO();

        Reservation res = new Reservation()
                .setClock(clock)
                .setDate(date)
                .setDinnertableid(selectedTableid)
                .setRestaurantid(restaurantid)
                .setUsername(surname)
                .setUsersurname(surname)
                .setUserid(userno);

        reservationDAO.addReservation(res);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                        "Rezervasyon Başarıyla Kayıt Edildi, Reservasyon No:" + res.getId()));

        clearFields();
        reservNo = res.getId();
    }

    private void initReservationList()
    {

        checkDAO();
        reservationList = reservationDAO.getRestaurantReservations(restaurantid);

    }

    private void clearFields()
    {
        this.clock = "";
        this.date = "";
        this.name = "";
        this.reservNo = 0;
        this.selectedTableid = 0;
        this.surname = "";
        this.userno = 0l;
    }

    public List<Reservation> getReservationList()
    {
        return reservationList;
    }

    public void setReservationList(final List<Reservation> reservationList)
    {
        this.reservationList = reservationList;
    }

    public List<Reservation> getFilteredReservationList()
    {
        return filteredReservationList;
    }

    public void setFilteredReservationList(List<Reservation> filteredReservationList)
    {
        this.filteredReservationList = filteredReservationList;
    }

    public List<DinnerTable> getTableList()
    {
        return tableList;
    }

    public void setTableList(List<DinnerTable> tableList)
    {
        this.tableList = tableList;
    }

    public void setSelectedTableid(long selectedTableid)
    {
        this.selectedTableid = selectedTableid;
    }

    public long getSelectedTableid()
    {
        return selectedTableid;
    }

    public long getUserno()
    {
        return userno;
    }

    public void setUserno(long userno)
    {
        this.userno = userno;
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

    public String getClock()
    {
        return clock;
    }

    public void setClock(String clock)
    {
        this.clock = clock;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setReservNo(long reservNo)
    {
        this.reservNo = reservNo;
    }

    public long getReservNo()
    {
        return reservNo;
    }

}
