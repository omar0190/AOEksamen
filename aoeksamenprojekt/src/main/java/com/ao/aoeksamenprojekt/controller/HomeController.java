package com.ao.aoeksamenprojekt.controller;


import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.service.position.PositionServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping ("/")
    public String index() {
       return "index";
    }

    @GetMapping ("/admin")
    public String index1() {
        return "indexlogin";
    }









}
