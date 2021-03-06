package by.nc.school.dev.dao;

import by.nc.school.dev.dao.entities.BaseDaoEntity;

import java.util.ArrayList;
import java.util.List;

abstract class BaseDao<T extends BaseDaoEntity> implements IDao<T> {

    protected List<T> entities;

    public BaseDao() {
        entities = new ArrayList<>();
    }

    @Override
    public T get(int id) {
        for (T entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }
}