package com.mvpbe.mvpbe.controllers;

import com.mvpbe.mvpbe.dto.DateDto;
import com.mvpbe.mvpbe.services.ScontrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("dashboard")
@CrossOrigin(originPatterns = "http://*.*.*:[*]", maxAge = 3600, allowCredentials="true")
public class ScontrinoController {


    @Autowired
    private ScontrinoService scontrinoService;


    @PostMapping("listbydate")
    public ResponseEntity listByDate(@RequestBody DateDto data){
        return ResponseEntity.ok(scontrinoService.getScontriniByDate(data.getDate()));
    }

    @PostMapping("listfornegozio")
    public ResponseEntity listByDateAndNegozio(@RequestBody DateDto data){
        data.getDate().setHours(0);
        data.getDate().setMinutes(0);
        return ResponseEntity.ok(scontrinoService.getSingleNegozio(data.getDate(), data.getNegozio()));
    }


}
