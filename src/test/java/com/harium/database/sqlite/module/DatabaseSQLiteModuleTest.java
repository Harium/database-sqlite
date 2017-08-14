package com.harium.database.sqlite.module;

import com.harium.database.Pojo;
import com.harium.database.dao.OrmLiteBaseDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseSQLiteModuleTest {

    private OrmLiteBaseDAOImpl<Pojo> dao;
    private DatabaseSQLiteModule module;

    @Before
    public void setUp() {
        dao = new OrmLiteBaseDAOImpl<Pojo>(Pojo.class);

        module = new DatabaseSQLiteModule("database.sqlite");
        module.register(dao);
        module.init(true);
    }

    @Test
    public void testGetDAO() {
        Assert.assertNotNull(module.getDAO(Pojo.class));
    }

    @Test
    public void testDAOMethods() {
        Pojo pojo = new Pojo();
        pojo.setText("1");

        Assert.assertEquals(0, dao.count());
        dao.create(pojo);
        Assert.assertEquals(1, dao.count());
        Assert.assertEquals("1", dao.queryForId(1).getText());
    }
}
