package com.garson.model.entity;
// Generated Dec 7, 2016 1:03:25 AM by Hibernate Tools 4.3.1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable
{

    private Long id;
    private Long addresid;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private String phone;
    private Integer gender;

    public User()
    {
    }

    public User(Long addresid, String name, String surname, String mail, String password, String phone, Integer gender)
    {
        this.addresid = addresid;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }

    public Long getId()
    {
        return this.id;
    }

    public User setId(Long id)
    {
        this.id = id;
        return this;

    }

    public Long getAddresid()
    {
        return this.addresid;
    }

    public User setAddresid(Long addresid)
    {
        this.addresid = addresid;
        return this;

    }

    public String getName()
    {
        return this.name;
    }

    public User setName(String name)
    {
        this.name = name;
        return this;

    }

    public String getSurname()
    {
        return this.surname;
    }

    public User setSurname(String surname)
    {
        this.surname = surname;
        return this;

    }

    public String getMail()
    {
        return this.mail;
    }

    public User setMail(String mail)
    {
        this.mail = mail;
        return this;

    }

    public String getPassword()
    {
        return this.password;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;

    }

    public String getPhone()
    {
        return this.phone;
    }

    public User setPhone(String phone)
    {
        this.phone = phone;
        return this;

    }

    public Integer getGender()
    {
        return this.gender;
    }

    public User setGender(Integer gender)
    {
        this.gender = gender;
        return this;
    }

}