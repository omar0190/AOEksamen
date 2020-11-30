package com.ao.aoeksamenprojekt.service;


import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public interface CRUDService<T, ID> {
    public ArrayList<T> findAll();

    public T save(T obj);

    public void delete(T obj);

    public void deleteByID(ID id);

    public Optional<T> findByID(ID id);



}
