package com.yourcompany.dao.base;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDao<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
    public abstract void save(T entity);

    public abstract T findById(Long id);

    public abstract void update(T entity);

    public abstract void delete(Long id);
}
