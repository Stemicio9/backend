package com.mvpbe.mvpbe.repository;

import com.mvpbe.mvpbe.entities.MPVUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MPVUserRepository extends JpaRepository<MPVUser,String> {

    MPVUser findByUsername(String username);



    Optional<MPVUser> findByEmail(String email);



}
