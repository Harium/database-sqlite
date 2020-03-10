# database-sqlite
SQLite module to Harium Database

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harium.database/sqlite/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harium.database/sqlite/)

## Example

```java
import com.harium.database.dao.OrmLiteBaseDAOImpl;
import com.harium.database.sqlite.module.SQLiteDatabaseModule;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class Main {

    public static void main(String[] args) {
        SQLiteDatabaseModule module = new SQLiteDatabaseModule("database.sqlite");
        
        OrmLiteBaseDAOImpl<Pojo> dao = new OrmLiteBaseDAOImpl<Pojo>(Pojo.class);
        module.register(dao);
        boolean clearDatabase = true;
        module.init(clearDatabase);
        
        Pojo pojo = new Pojo();
        pojo.setText("Text 1");
        dao.create(pojo);
        
        Pojo created = dao.queryForId(1);
        System.out.println(created.getText());
    }

    @DatabaseTable(tableName = "pojo")
    public static class Pojo {

        @DatabaseField(generatedId = true)
        int id;

        @DatabaseField(columnName = "text")
        String text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
```
