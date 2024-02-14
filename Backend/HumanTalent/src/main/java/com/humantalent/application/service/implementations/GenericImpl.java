package com.humantalent.application.service.implementations;

import com.humantalent.application.service.contracts.GenericService;
import com.humantalent.domain.model.person.Person;
import jakarta.persistence.EntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericImpl <E, R extends CrudRepository<E,Integer>> implements GenericService<E> {

    protected final R repository;

    public GenericImpl(R repository){
        this.repository = repository;
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
