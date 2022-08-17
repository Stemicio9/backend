package com.mvpbe.mvpbe.dto;



import com.mvpbe.mvpbe.entities.MPVUser;

import java.util.List;
import java.util.Set;

public class SignUpRequest {

    private String username;
    private String role;
    private String password;
    private String email;
    private String controllo;



    public SignUpRequest(){}

    public SignUpRequest(MPVUser utente){
        this.username = utente.getUsername();
        this.role = utente.getRole().getName().name();
        this.email = utente.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }



    public String getEmail() {
        return email;
    }

    public String getControllo() {
        return controllo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setControllo(String controllo) {
        this.controllo = controllo;
    }
}
