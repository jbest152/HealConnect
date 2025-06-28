package com.healconnect.service;


import com.healconnect.model.BaseEntity;
import com.healconnect.repository.GenericRepository;

public class GenericService<T extends BaseEntity, ID> {

    protected final GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public T findById(ID id) {
        return repository.findById(id).get();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
