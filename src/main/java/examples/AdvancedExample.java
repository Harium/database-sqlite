package examples;

import com.harium.database.Table;
import com.harium.database.dao.OrmLiteBaseDAOImpl;

import com.harium.database.sqlite.module.SQLiteDatabaseModule;
import java.util.List;

public class AdvancedExample {

    public static void main(String[] args) {

        SQLiteDatabaseModule<Long> module = new SQLiteDatabaseModule("database.sqlite");

        Table<Pojo> table = new Table(Pojo.class);
        table.createColumn("id").id().typeLong();
        table.createColumn("text");
        table.register(module);

        module.init(true);

        OrmLiteBaseDAOImpl<Pojo, Long> dao = (OrmLiteBaseDAOImpl<Pojo, Long>) table.getDAO(Pojo.class);

        Pojo pojo = new Pojo();
        pojo.setText("My Text");
        dao.create(pojo);

        List<Pojo> pojos = dao.queryAll();
        System.out.println(pojos.get(0).text);
    }

    static class Pojo {
        long id;
        String text;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
