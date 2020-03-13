package examples;

import com.harium.database.dao.OrmLiteBaseDAOIntegerImpl;
import com.harium.database.sqlite.module.SQLiteDatabaseModule;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

public class SimpleExample {

  public static void main(String[] args) {
    SQLiteDatabaseModule<Integer> module = new SQLiteDatabaseModule("database.sqlite");

    OrmLiteBaseDAOIntegerImpl<Pojo> dao = new OrmLiteBaseDAOIntegerImpl<Pojo>(Pojo.class);
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
