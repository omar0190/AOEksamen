package com.ao.aoeksamenprojekt.controller;


import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.service.position.PositionServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

PositionServiceJPA positionServiceJPA;

    public HomeController(PositionServiceJPA positionServiceJPA) {
        this.positionServiceJPA = positionServiceJPA;
    }

    @GetMapping ("/")
    public String index(){
        Position p1 = new Position();
        p1.setTitle("EtEllerAndet");
        p1.setDescription("IkkeEtEllerAndetIgen");
        positionServiceJPA.save(p1);
        return "index";
    }



}
