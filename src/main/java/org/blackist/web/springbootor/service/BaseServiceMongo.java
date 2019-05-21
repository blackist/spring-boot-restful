package org.blackist.web.springbootor.service;

import org.blackist.web.springbootor.model.entity.BaseEntity;

import java.util.List;

public interface BaseServiceMongo<T> {

    T save(T t);

    T findById(String id);

    void deleteById(String id);

    T update(T t);

    List<T> query(T t);

    List<T> findAll();

    List<T> batch(List<T> list);
}
