package com.ao.aoeksamenprojekt.controller;

import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.service.position.PositionServiceJPA;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostionRestController {

    private PositionServiceJPA positionServiceJPA;

    public PostionRestController(PositionServiceJPA positionServiceJPA) {
        this.positionServiceJPA = positionServiceJPA;
    }

    @PostMapping("/sletstilling")
    public ResponseEntity<PositionServiceJPA> delete(@RequestBody Position position){

        System.out.println("Rest virker: " + position.getTitle());


        return ResponseEntity.ok(positionServiceJPA);
    }

}
