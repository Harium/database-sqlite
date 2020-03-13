package com.harium.database.sqlite.module;

import com.harium.database.module.OrmLiteDatabaseModule;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class SQLiteDatabaseModule<ID> extends OrmLiteDatabaseModule<ID> {

    private static final String SQLITE_PREFIX = "jdbc:sqlite:";
    private final String DATABASE_NAME;

    public SQLiteDatabaseModule(String databaseName) {
        this.DATABASE_NAME = SQLITE_PREFIX + databaseName;
    }

    @Override
    protected ConnectionSource initConnection() throws SQLException {
        return new JdbcConnectionSource(DATABASE_NAME);
    }
}
