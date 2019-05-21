package org.blackist.web.springbootor.service;

import org.blackist.web.springbootor.repository.BaseMongoRepository;

import java.util.List;

public abstract class BaseServiceMongoImpl<T, R extends BaseMongoRepository<T, String>> implements BaseServiceMongo<T> {

    public abstract R getRepository();

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public T findById(String id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        getRepository().deleteById(id);
    }

    @Override
    public T update(T t) {
        return getRepository().save(t);
    }

    @Override
    public List<T> query(T t) {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> batch(List<T> list) {
        return getRepository().saveAll(list);
    }
}
