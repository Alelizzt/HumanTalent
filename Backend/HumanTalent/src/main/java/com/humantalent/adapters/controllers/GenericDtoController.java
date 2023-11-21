package com.humantalent.adapters.controllers;

import com.humantalent.application.service.GenericService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@SuppressWarnings("unchecked")
public class GenericDtoController <E, S extends GenericService<E>>{

    protected final S service;
    protected final String name_entity;

    public List<E> getAll() {
        return (List<E>) service.findAll();
    }

    public E getById(Integer id) {
        return  (E) service.findById(id);
    }

    public E addEntity(E entity) {
        return service.save(entity);
    }
}
