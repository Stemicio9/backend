package com.mvpbe.mvpbe.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
public class MPVUser implements UserDetails {

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @Id
    private String username;

    private String password;


    @Column(unique = true)
    private String email;

    private Date lastlogin;



    @ManyToOne
    private Role role;

    public MPVUser(final MPVUser users) {
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.role = users.getRole();
    }

    public MPVUser() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role ruolo = getRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(ruolo.getName().name());
        List<GrantedAuthority> listaautorita = new LinkedList<>();
        listaautorita.add(simpleGrantedAuthority);
        return listaautorita;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }



    public String getEmail() {
        return email;
    }

    public static long getOtpValidDuration() {
        return OTP_VALID_DURATION;
    }

    public Date getLastlogin() {
        return lastlogin;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}