package com.humantalent.adapters.controllers;

import com.humantalent.application.service.contracts.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GenericController<E, S extends GenericService<E>> {

    protected final S service;
    protected String name_entity;

    public GenericController(S service) {
        this.service = service;
    }


    public List<E> getAll() {
        return (List<E>) service.findAll();
    }


    @SuppressWarnings("unchecked")
    public E getById(Integer id) {
        return (E) service.findById(id);
    }


    public E addEntity(E entity) {
        return service.save(entity);
    }

    protected Map<String, Object> getValidations(BindingResult result) {
        Map<String, Object> validations = new HashMap<>();
        result.getFieldErrors()
                .forEach(fieldError -> validations.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return validations;
    }
}
