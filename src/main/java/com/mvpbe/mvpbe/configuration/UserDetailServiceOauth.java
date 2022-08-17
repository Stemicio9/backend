package com.mvpbe.mvpbe.configuration;


import com.mvpbe.mvpbe.dto.ResultUpload;
import com.mvpbe.mvpbe.dto.UserDto;
import com.mvpbe.mvpbe.entities.MPVRole;
import com.mvpbe.mvpbe.entities.MPVUser;
import com.mvpbe.mvpbe.entities.Role;
import com.mvpbe.mvpbe.repository.MPVUserRepository;
import com.mvpbe.mvpbe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailServiceOauth implements UserDetailsService {

    @Autowired
    private MPVUserRepository venturaUserRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MPVUser usersOptional = venturaUserRepository.findByUsername(username);

        if (usersOptional == null) {
            throw new UsernameNotFoundException("Username non trovato!");
        }

        return usersOptional;

    }


    public boolean createUser(MPVUser venturaUser) {
        venturaUser.setPassword(passwordEncoder().encode(venturaUser.getPassword()));
        try {
            venturaUserRepository.save(venturaUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateUser(MPVUser update) {
        MPVUser user = venturaUserRepository.findByUsername(update.getUsername());

        try {
            venturaUserRepository.saveAndFlush(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (update.getPassword() != null && !update.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder().encode(update.getPassword()));
        }
        if (update.getRole() != null) {
            user.setRole(update.getRole());
        }
        user.setEmail(update.getEmail());

        try {
            venturaUserRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean updateLastLogin(MPVUser user, Date lastlogin) {
        user.setLastlogin(lastlogin);
        try {
            venturaUserRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPassword(MPVUser received, String newPassword) {
        MPVUser user = venturaUserRepository.findByUsername(received.getUsername());
        user.setPassword(passwordEncoder().encode(newPassword));
        try {
            venturaUserRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserEmail(MPVUser received, String email) {
        MPVUser user = venturaUserRepository.findByUsername(received.getUsername());
        user.setEmail(email);
        try {
            venturaUserRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public MPVUser getUserByUsername(String username) {
        return venturaUserRepository.findByUsername(username);
    }

    public List<UserDto> listUsers() {
        List<MPVUser> listaUtenti = venturaUserRepository.findAll();
        List<UserDto> result = new LinkedList<>();
        for (MPVUser current : listaUtenti) {
            result.add(new UserDto(current));
        }
        return result;
    }


    public boolean deleteUser(String username) {
        try {
            MPVUser user = getUserByUsername(username);
            venturaUserRepository.delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Role addRole(String rolename) {
        if (rolename.equalsIgnoreCase(MPVRole.ADMIN.name()) && rolename.equalsIgnoreCase(MPVRole.CLIENTE.name())) {
            return null;
        }
        MPVRole venturaRole = MPVRole.valueOf(rolename);
        Optional<Role> userRole = roleRepository.findByName(venturaRole);
        if (userRole.isPresent()) {
            return userRole.get();
        } else {
            Role result = roleRepository.save(new Role(venturaRole));
            return result;
        }
    }

    public ResultUpload existsEmail(String email) {
        Optional<MPVUser> user = venturaUserRepository.findByEmail(email);
        if (user.isPresent()) return new ResultUpload(true);
        return new ResultUpload(false);
    }

}
