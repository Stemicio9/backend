package com.mvpbe.mvpbe.controllers;


import com.mvpbe.mvpbe.configuration.JwtUtils;
import com.mvpbe.mvpbe.configuration.UserDetailServiceOauth;
import com.mvpbe.mvpbe.dto.DateDto;
import com.mvpbe.mvpbe.dto.DoubleDto;
import com.mvpbe.mvpbe.dto.SignInRequest;
import com.mvpbe.mvpbe.dto.UserDto;
import com.mvpbe.mvpbe.entities.MPVUser;
import com.mvpbe.mvpbe.entities.Role;
import com.mvpbe.mvpbe.services.ScontrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.xml.ws.Response;
import java.util.Date;


@CrossOrigin(originPatterns = "http://*.*.*:[*]", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailServiceOauth userDetailServiceOauth;

    @Autowired
    JwtUtils jwtUtils;




    @Autowired
    private ScontrinoService scontrinoService;


    @PostMapping("listbydate")
    public ResponseEntity listByDate(@RequestBody DateDto data){

        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        return ResponseEntity.ok(scontrinoService.getScontriniByDate(data.getDate()));
    }

    @PostMapping("listfornegozio")
    public ResponseEntity listByDateAndNegozio(@RequestBody DateDto data){
        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        System.out.println("DATA DATA DATA DATA DATA DATA DATA");
        System.out.println(data.getDate());
        return ResponseEntity.ok(scontrinoService.getSingleNegozio(data.getDate(), data.getNegozio()));
    }



    @PostMapping("scontrinomediodelgiorno")
    public ResponseEntity scontrinoMedioDelGiorno(@RequestBody DateDto data){
        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        DoubleDto result = new DoubleDto();
        System.out.println("DATA DATA DATA DATA DATA DATA DATA");
        System.out.println(data.getDate());
        result.setValue(scontrinoService.scontrinoMedioDelGiorno(data.getDate()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("numerovenditedelgiorno")
    public ResponseEntity numeroVenditeDelGiorno(@RequestBody DateDto data){
        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        System.out.println("DATA DATA DATA DATA DATA DATA DATA");
        System.out.println(data.getDate());
        DoubleDto result = new DoubleDto();
        result.setValue(scontrinoService.numeroVenditeDelGiorno(data.getDate()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("incassodelgiorno")
    public ResponseEntity incassoVenditeDelGiorno(@RequestBody DateDto data){
        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        DoubleDto result = new DoubleDto();
        result.setValue(scontrinoService.incassoDelGiorno(data.getDate()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("incassodellasettimana")
    public ResponseEntity incassoDellaSettimana(@RequestBody DateDto data){
        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        DoubleDto result = new DoubleDto();
        result.setValue(scontrinoService.incassoDellaSettimana(data.getDate()));
        return ResponseEntity.ok(result);
    }

    @GetMapping("fakeadmin")
    public ResponseEntity fakeAdmin(){
        MPVUser user = new MPVUser();
        Role role = userDetailServiceOauth.addRole("ADMIN");
        user.setUsername("user");
        user.setPassword("password");
        user.setRole(role);
        boolean result = userDetailServiceOauth.createUser(user);
        if(result){
            return ResponseEntity.ok("UTENTE SALVATO!");
        }else{
            return ResponseEntity.badRequest().body("ERRORE NEL SALVATAGGIO!");
        }
    }


    @PostMapping("signin")
    public ResponseEntity<?> signInWithoutCode(@RequestBody SignInRequest request){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MPVUser userDetails = (MPVUser) authentication.getPrincipal();
        MPVUser utenteComplete = userDetailServiceOauth.getUserByUsername(userDetails.getUsername());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        UserDto result = new UserDto(utenteComplete);


        userDetailServiceOauth.updateLastLogin(utenteComplete, new Date());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(result);
    }


    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logout eseguito!");
    }



}
