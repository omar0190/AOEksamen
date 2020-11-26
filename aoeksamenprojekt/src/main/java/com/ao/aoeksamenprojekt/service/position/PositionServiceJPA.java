package com.ao.aoeksamenprojekt.service.position;

import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service // Dette gør, at en instans fra denne klasse automatisk bliver oprettet
public class PositionServiceJPA implements PositionService {
    private PositionRepository positionRepository;

    public PositionServiceJPA(PositionRepository positionRepository) { // Spring laver automatisk et objekt af positionRepostiory
        this.positionRepository = positionRepository;
    }

    @Override
    public Set<Position> findAll() {
        Set<Position> list = new HashSet<>();
        positionRepository.findAll().forEach(list::add); // Henter findall, som er i Set, den tager den set og kører loop igennem dem, så hver gang den kører loop, sætter den ind i den nye set vi har lavet.
        return list;
    }

    @Override
    public Position save(Position obj) {
        positionRepository.save(obj);
        return obj;
    }

    @Override
    public void delete(Position obj) {
        positionRepository.delete(obj);
    }

    @Override
    public void deleteByID(Integer id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Optional<Position> findByID(Integer id) { // Hvis ID = 0, Så gør den ikke noget.
       return positionRepository.findById(id);
    }
}
