package com.ao.aoeksamenprojekt.controller;

import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.service.position.PositionServiceJPA;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PositionController {
    PositionServiceJPA positionServiceJPA;

    public PositionController(PositionServiceJPA positionServiceJPA) {
        this.positionServiceJPA = positionServiceJPA;
    }

    @GetMapping("/stillinger")
    public String stillinger(Model model) {
        ArrayList<Position> list = positionServiceJPA.findAll();
        model.addAttribute("stillinger", list);
        return "Stillinger/stillinger";
    }

//    @GetMapping("/details{id}")
//    public String showPositions(@PathVariable("id") int id, Model model) {
//        Optional<Position> position1 = positionServiceJPA.findByID(id); // Optional, gør at man skal hente objektet selv, og hente den med get
//        if (position1.isPresent()) {
//            model.addAttribute("title", position1.get().getTitle());
//            model.addAttribute("description", position1.get().getDescription());
//        }
//
//        return "stillingsbeskrivelse";
//    }


    @GetMapping("/opretstilling")
    public String create(){

        return "Stillinger/opretStilling";
    }


    @PostMapping("stillingCreated")
    public String created (@ModelAttribute Position position){


        positionServiceJPA.save(position);

        System.out.println("oprettet stilling");

        return "redirect:/allestillinger";
    }


    @GetMapping("/allestillinger")
    public String get(Model model){

        ArrayList<Position> list = positionServiceJPA.findAll();

        if(list != null){
            System.out.println("liste hentet");
        }
        model.addAttribute("list", list);

        return ("Stillinger/AllStilling");
    }


    @GetMapping("/redigererstillinger{id}")
    public String edit(@PathVariable ("id") int id, Model model){

        Optional<Position> position = positionServiceJPA.findByID(id);

        if(position.isPresent()){
            model.addAttribute("id", position.get().getID());
            model.addAttribute("title", position.get().getTitle());
            model.addAttribute("des", position.get().getDescription());
        }

        return "Stillinger/edit";
    }


    @PostMapping("/edited")
    public String edit(@ModelAttribute Position position){

        if (position != null){
            positionServiceJPA.save(position);
            System.out.println("edited");
        }
        else {
        System.out.println("Not edited");
        }


        return "redirect:/allestillinger";
    }


    @GetMapping("/sletstillinger{id}")
    public String delete(@PathVariable("id") int id){

        positionServiceJPA.deleteByID(id);
        System.out.println("deleted");

        return "redirect:/allestillinger";
    }

    @GetMapping("/stillinginfo{id}")
    public String details(@PathVariable("id") int id, Model model){

        Optional<Position> position = positionServiceJPA.findByID(id);

        if(position.isPresent()){
            model.addAttribute("title", position.get().getTitle());
            model.addAttribute("description", position.get().getDescription());
        }


        return "Stillinger/details";
    }


    @GetMapping("/infostilling{id}")
    public String info(@PathVariable("id") int id, Model model){

        Optional<Position> position = positionServiceJPA.findByID(id);

        if(position.isPresent()){
            model.addAttribute("title", position.get().getTitle());
            model.addAttribute("description", position.get().getDescription());
        }


        return "Stillinger/detailsbruger";
    }

}
