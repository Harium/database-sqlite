package com.harium.database.sqlite.dao;

import com.harium.database.model.BaseDAO;
import com.j256.ormlite.support.ConnectionSource;

public interface BaseSQLiteDAO<T> extends BaseDAO<T, ConnectionSource> {

}
