package com.harium.database.sqlite.module;

import com.harium.database.model.BaseDAO;
import com.harium.database.module.DatabaseModule;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseSQLiteModule implements DatabaseModule<ConnectionSource> {
    private final String DATABASE_NAME;
    private static final String SQLITE_PREFIX = "jdbc:sqlite:";

    private ConnectionSource connectionSource;

    private List<BaseDAO<?, ConnectionSource>> registers = new ArrayList<BaseDAO<?, ConnectionSource>>();
    private Map<Class<?>, BaseDAO<?, ConnectionSource>> daos = new HashMap<Class<?>, BaseDAO<?, ConnectionSource>>();

    public DatabaseSQLiteModule(String databaseName) {
        this.DATABASE_NAME = SQLITE_PREFIX + databaseName;
    }

    public void init(boolean clearDatabase) {
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_NAME);

            createDAOs();

            if (clearDatabase) {
                clear();
            }
            setupDatabase(connectionSource);

            initDAOs(connectionSource);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() throws Exception {
        for (BaseDAO<?, ConnectionSource> dao : registers) {
            TableUtils.dropTable(connectionSource, dao.getKlass(), true);
        }
    }

    private void setupDatabase(ConnectionSource connectionSource) throws SQLException {
        for (BaseDAO<?, ConnectionSource> dao : registers) {
            TableUtils.createTableIfNotExists(connectionSource, dao.getKlass());
        }
    }

    private void createDAOs() {
        for (BaseDAO<?, ConnectionSource> register : registers) {
            addDAO(register);
        }
    }

    private void initDAOs(ConnectionSource connectionSource) {
        for (BaseDAO<?, ConnectionSource> dao : registers) {
            dao.init(connectionSource);
        }
    }

    private BaseDAO addDAO(BaseDAO<?, ConnectionSource> baseDAO) {
        daos.put(baseDAO.getKlass(), baseDAO);
        return baseDAO;
    }

    public BaseDAO getDAO(Class klass) {
        return daos.get(klass);
    }

    public void register(BaseDAO<?, ConnectionSource> dao) {
        registers.add(dao);
    }
}
