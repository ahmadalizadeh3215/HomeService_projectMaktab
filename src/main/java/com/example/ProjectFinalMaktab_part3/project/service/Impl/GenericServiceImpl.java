package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.service.GenericService;


import java.util.List;
import java.util.Optional;


public class GenericServiceImpl<T, ID> implements GenericService<T, ID> {
    private final GenericRepository<T, ID> genericRepository;

    public GenericServiceImpl(GenericRepository<T, ID> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID entityId) {
        return genericRepository.findById(entityId);
    }

    @Override
    public T update(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public T updateById(T entity, ID entityId) {
        Optional<T> optional = genericRepository.findById(entityId);
        if (optional.isPresent()) {
            return (T) genericRepository.save(entity);
        } else {
            return null;
        }
    }

    @Override
    public void delete(T entity) {
        genericRepository.delete(entity);

    }

    @Override
    public void deleteById(ID entityId) {
        genericRepository.deleteById(entityId);
    }

    @Override
    public boolean existsById(ID entityId) {
        return genericRepository.existsById(entityId);
    }
}
