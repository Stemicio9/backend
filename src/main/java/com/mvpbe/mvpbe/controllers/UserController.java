package com.mvpbe.mvpbe.controllers;

import com.mvpbe.mvpbe.configuration.UserDetailServiceOauth;
import com.mvpbe.mvpbe.dto.*;
import com.mvpbe.mvpbe.entities.MPVUser;
import com.mvpbe.mvpbe.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.mvpbe.mvpbe.utilities.AuthUtilities.generateRandomString;

@RestController
@CrossOrigin(originPatterns = "http://*.*.*:[*]", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserDetailServiceOauth userDetailServiceOauth;






    @GetMapping("list")
    private List<UserDto> listusers(){
        return userDetailServiceOauth.listUsers();
    }


    @PostMapping("updatepassword")
    public ResultUpload updatePassword(@RequestBody MPVUser user){
        return new ResultUpload(userDetailServiceOauth.updateUserPassword(user, user.getPassword()));
    }

    @PostMapping("updateemail")
    public ResultUpload updateEmail(@RequestBody MPVUser user){
        return new ResultUpload(userDetailServiceOauth.updateUserEmail(user, user.getEmail()));
    }



    @PostMapping("updateuser")
    public ResponseEntity<?> updateUser(@RequestBody SignUpRequest venturaUser){
        Role role = userDetailServiceOauth.addRole(venturaUser.getRole());
        MPVUser user = new MPVUser();
        user.setUsername(venturaUser.getUsername());
        user.setPassword(venturaUser.getPassword());
        user.setRole(role);
        user.setEmail(venturaUser.getEmail());
        MPVUser optUser = userDetailServiceOauth.getUserByUsername(venturaUser.getUsername());

        boolean result = userDetailServiceOauth.updateUser(user);
        return ResponseEntity.ok().body(result);
    }


    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest venturaUser){




        if(!venturaUser.getControllo().equals("CONTROLLO")){
            return ResponseEntity.ok("NON ACCETTATA LA VARIABILE DI CONTROLLO");
        }

        if(venturaUser.getPassword() == null || venturaUser.getPassword().isEmpty() || venturaUser.getPassword().length() < 4){
            return ResponseEntity.badRequest().body("Password errata");
        }

        Role role = userDetailServiceOauth.addRole(venturaUser.getRole());
        MPVUser user = new MPVUser();
        user.setUsername(venturaUser.getUsername());
        user.setPassword(venturaUser.getPassword());
        user.setRole(role);
        user.setEmail(venturaUser.getEmail());





      /*  if(venturaUser.isUsinf2fa()){
            user.setUsing2FA(true);
            user.setSecret(secretGenerator.generate());
        } */

        MPVUser optUser = userDetailServiceOauth.getUserByUsername(venturaUser.getUsername());
        boolean result;
        if(optUser == null){
            result = userDetailServiceOauth.createUser(user);
        }else{
            result = userDetailServiceOauth.updateUser(user);
        }


        if(result){
            return ResponseEntity.ok("Utente registrato!");
        }else{
            return ResponseEntity.badRequest().body("Errore nella registrazione dell'utente!");
        }
    }

    @PostMapping("removeuser")
    public List<UserDto> removeUser(@RequestBody String user){
        userDetailServiceOauth.deleteUser(user);
        return userDetailServiceOauth.listUsers();
    }

    @GetMapping("checkemail")
    public ResultUpload existsEmail(@RequestParam String email){
        if(email.isEmpty()) return new ResultUpload(false);
        return userDetailServiceOauth.existsEmail(email);
    }

    @GetMapping("existsuser")
    public ResponseEntity<?> existsUser(@RequestParam String username){
        MPVUser optUser = userDetailServiceOauth.getUserByUsername(username);
        if(optUser != null){
            return ResponseEntity.ok(new ExistsUserDto(true));
        }else{
            return ResponseEntity.ok(new ExistsUserDto(false));
        }
    }

    @GetMapping("loggedin")
    public UserDto isLoggedIn(Authentication authentication){
        MPVUser user;

        try{
            user = (MPVUser) authentication.getPrincipal();
            System.out.println("CERCO UTENTE " + user.getUsername());
            MPVUser optionalUser = userDetailServiceOauth.getUserByUsername(user.getUsername());

            if(optionalUser == null){
                return null;
            }
            UserDto userDto = new UserDto(optionalUser);
            return userDto;
        }catch(Exception e){
            return null;
        }
    }


    /*

    @PostMapping("changemailrequest")
    public ResponseEntity<?> changeMailRequest(@RequestBody ChangeMailRequest request){
        String code = generateRandomString(6);
        MailVerification verification = new MailVerification();
        verification.setEmail(request.getNewmail());
        verification.setAuthCode(code);
        verification.setAuthCodeGenerated(new Date());
        try{
            mailVerificationRepository.save(verification);
            inviaEmail(request.getNewmail(), code);
            return ResponseEntity.ok(new CodeVerificationResponse(request.getNewmail()));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Errore nella richiesta");
        }

    }

*/



}
