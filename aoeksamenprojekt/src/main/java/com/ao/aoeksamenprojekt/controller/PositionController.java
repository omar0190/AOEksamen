package com.ao.aoeksamenprojekt.controller;

import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.service.position.PositionServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Controller
public class PositionController {
    PositionServiceJPA positionServiceJPA;

    public PositionController(PositionServiceJPA positionServiceJPA) {
        this.positionServiceJPA = positionServiceJPA;
    }
    @GetMapping("/stillinger")
    public String stillinger(Model model){
        ArrayList<Position> list = positionServiceJPA.findAll();
        Position position1 = list.get(0);
        System.out.println(position1.getTitle());
        model.addAttribute("stillinger",list);
        return "stillinger";
    }
    @GetMapping("/details{id}")
    public String showPositions(@PathVariable("id") int id, Model model){
        Optional<Position> position1 = positionServiceJPA.findByID(id); // Optional, gør at man skal hente objektet selv, og hente den med get
    if (position1.isPresent()){
        model.addAttribute("title", position1.get().getTitle());
        model.addAttribute("description",position1.get().getDescription());
    }

return "stillingsbeskrivelse";
    }

}
