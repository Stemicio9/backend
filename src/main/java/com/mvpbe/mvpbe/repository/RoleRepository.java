package com.mvpbe.mvpbe.repository;

import com.mvpbe.mvpbe.entities.MPVRole;
import com.mvpbe.mvpbe.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(MPVRole rolename);

}
