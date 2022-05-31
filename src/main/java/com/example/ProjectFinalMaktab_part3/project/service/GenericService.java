package com.example.ProjectFinalMaktab_part3.project.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {
    T save(T entity);

    List<T> findAll();

    Optional<T> findById(ID entityId);

    T update(T entity);

    T updateById(T entity, ID entityId);

    void delete(T entity);

    void deleteById(ID entityId);

    boolean existsById(ID entityId);
}
