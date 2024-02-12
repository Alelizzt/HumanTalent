package com.humantalent.application.service.contracts;

import java.util.Optional;

public interface GenericService <E>{
    Optional<E> findById(Integer id);
    E save(E entity);
    Iterable<E> findAll();
    void deleteById(Integer id);
}
