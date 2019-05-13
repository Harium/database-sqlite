# database-sqlite
SQLite module to Harium Database

### Maven
```
<dependency>
  <groupId>com.harium.database</groupId>
  <artifactId>sqlite</artifactId>
  <version>1.0.6</version>
</dependency>
```

## Example

```java
import com.harium.database.dao.OrmLiteBaseDAOImpl;
import com.harium.database.sqlite.module.SQLiteDatabaseModule;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class Main {

    public static void main(String[] args) {
        OrmLiteBaseDAOImpl<Pojo> dao;
        SQLiteDatabaseModule module;

        dao = new OrmLiteBaseDAOImpl<Pojo>(Pojo.class);

        module = new SQLiteDatabaseModule("database.sqlite");
        module.register(dao);
        boolean clearDatabase = true;
        module.init(clearDatabase);

        Pojo pojo = new Pojo();
        pojo.setText("1");

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
