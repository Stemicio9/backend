package com.mvpbe.mvpbe.dto;


import com.mvpbe.mvpbe.entities.MPVUser;

import java.util.Date;
import java.util.List;
import java.util.Set;




public class UserDto {

    private String username;
    private String role;

    private String email;
    private Date lastlogin;



    public UserDto(){}

    public UserDto(MPVUser utente){
        this.username = utente.getUsername();
        this.role = utente.getRole().getName().name();

        this.email = utente.getEmail();
        this.lastlogin = utente.getLastlogin();
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }



    public String getEmail() {
        return email;
    }


    public Date getLastlogin() {
        return lastlogin;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }
}
