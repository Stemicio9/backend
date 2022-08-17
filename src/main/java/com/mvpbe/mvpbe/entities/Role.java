package com.mvpbe.mvpbe.entities;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MPVRole name;
    public Role() {
    }
    public Role(MPVRole name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public MPVRole getName() {
        return name;
    }
    public void setName(MPVRole name) {
        this.name = name;
    }
}
