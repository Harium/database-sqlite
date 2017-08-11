package com.harium.database.sqlite.dao;

import com.harium.database.DatabaseError;
import com.harium.database.model.BaseDAO;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAOSQLiteImpl<T> implements BaseDAO<T, ConnectionSource> {

    Class<T> klass;

    protected Dao<T, Integer> dao;

    public BaseDAOSQLiteImpl(Class<T> klass) {
        this.klass = klass;
    }

    public void init(ConnectionSource connectionSource) {
        try {
            dao = DaoManager.createDao(connectionSource, klass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public T queryForId(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public List<T> queryAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int create(T item) {
        try {
            return dao.create(item);
        } catch (SQLException e) {
            e.printStackTrace();
            return DatabaseError.ON_CREATE;
        }
    }

    public int update(T item) {
        try {
            return dao.update(item);
        } catch (SQLException e) {
            e.printStackTrace();
            return DatabaseError.ON_UPDATE;
        }
    }

    @Override
    public int delete(T model) {
        try {
            return dao.delete(model);
        } catch (SQLException e) {
            e.printStackTrace();
            return DatabaseError.ON_DELETE;
        }
    }

    public long count() {
        try {
            return dao.countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Class<T> getKlass() {
        return klass;
    }
}
