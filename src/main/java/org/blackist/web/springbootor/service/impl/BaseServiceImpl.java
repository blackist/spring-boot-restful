package org.blackist.web.springbootor.service.impl;

import org.blackist.web.springbootor.model.entity.BaseEntity;
import org.blackist.web.springbootor.repository.BaseRepository;
import org.blackist.web.springbootor.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository<T, Long>> implements BaseService<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract R getRepository();

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public T findById(Long id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
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

    @Transactional
    public List<T> batch(List<T> list) {
        return getRepository().saveAll(list);
    }
}
