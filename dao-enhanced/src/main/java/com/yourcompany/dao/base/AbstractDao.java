package com.yourcompany.dao.base;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {

    protected JdbcTemplate jdbcTemplate;

    public AbstractDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<T> findAll(String sql) {
        return jdbcTemplate.query(sql, getRowMapper());
    }

    public Optional<T> findById(String sql, Object... params) {
        try {
            T result = jdbcTemplate.queryForObject(sql, params, getRowMapper());
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int executeUpdate(String sql, Object... params) {
        return jdbcTemplate.update(sql, params);
    }

    public int[] executeBatchUpdate(String sql, List<Object[]> batchParams) {
        return jdbcTemplate.batchUpdate(sql, batchParams);
    }

    public <E> E queryForObject(String sql, Class<E> requiredType, Object... params) {
        return jdbcTemplate.queryForObject(sql, params, requiredType);
    }

    public <E> List<E> queryForList(String sql, Class<E> elementType, Object... params) {
        return jdbcTemplate.queryForList(sql, params, elementType);
    }

    public List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        return jdbcTemplate.query(sql, params, rowMapper);
    }

    protected abstract RowMapper<T> getRowMapper();
}