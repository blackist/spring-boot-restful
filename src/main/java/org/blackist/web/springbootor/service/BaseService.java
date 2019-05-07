package org.blackist.web.springbootor.service;

import org.blackist.web.springbootor.model.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T t);

    T findById(Long id);

    void deleteById(Long id);

    T update(T t);

    List<T> query(T t);

    List<T> findAll();

    List<T> batch(List<T> list);
}
