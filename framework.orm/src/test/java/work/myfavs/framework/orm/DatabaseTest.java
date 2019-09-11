package work.myfavs.framework.orm;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import work.myfavs.framework.orm.entity.Snowfake;
import work.myfavs.framework.orm.entity.enums.TypeEnum;

@Slf4j
public class DatabaseTest {

  String url      = "jdbc:mysql://127.0.0.1:3306/myfavs_test?useUnicode=true&useServerPrepStmts=false&rewriteBatchedStatements=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
  String user     = "root";
  String password = "root";

  private DBTemplate DBTemplate;

  @Before
  public void setUp()
      throws Exception {

    HikariConfig configuration = new HikariConfig();
    configuration.setDriverClassName("com.mysql.jdbc.Driver");
    configuration.setJdbcUrl(url);
    configuration.setUsername(user);
    configuration.setPassword(password);
    configuration.setAutoCommit(false);
    DataSource dataSource = new HikariDataSource(configuration);

    this.DBTemplate = DBTemplate.build(dataSource);
  }

  @Test
  public void find() {

    try (Database database = this.DBTemplate.open()) {
      List<Snowfake> snowfakes = database.find(Snowfake.class, "SELECT * FROM tb_snowfake", null);
      Assert.assertNotNull(snowfakes);
      Assert.assertTrue(snowfakes.size() > 0);
    }
  }

  @Test
  public void testTransaction() {

    try (Database db1 = this.DBTemplate.beginTransaction()) {
      List<Snowfake> s1 = db1.find(Snowfake.class, "SELECT * FROM tb_snowfake", null);
      try (Database db2 = this.DBTemplate.beginTransaction(Connection.TRANSACTION_READ_UNCOMMITTED)) {
        List<Snowfake> s2 = db2.find(Snowfake.class, "SELECT * FROM tb_snowfake", null);
        try (Database db3 = this.DBTemplate.beginTransaction(Connection.TRANSACTION_SERIALIZABLE)) {
          Snowfake s3 = new Snowfake();
          s3.setCreated(new Date());
          s3.setName("s3");
          s3.setDisable(false);
          s3.setPrice(new BigDecimal(100));
          s3.setType(TypeEnum.DRINK);
          db3.create(Snowfake.class, s3);
        }
      }
      List<Snowfake> s4 = db1.find(Snowfake.class, "SELECT * FROM tb_snowfake", null);
    }
  }

  @Test
  public void commit() {

  }

  @Test
  public void rollback() {

  }

  @Test
  public void close() {

  }

  @Test
  public void findTop() {

  }

  @Test
  public void testFindTop() {

  }

  @Test
  public void testFindTop1() {

  }

  @Test
  public void testFindTop2() {

  }

  @Test
  public void get() {

  }

  @Test
  public void testGet() {

  }

  @Test
  public void testGet1() {

  }

  @Test
  public void testGet2() {

  }

  @Test
  public void getById() {

  }

  @Test
  public void getByField() {

  }

  @Test
  public void findByIds() {

  }

  @Test
  public void findByField() {

  }

  @Test
  public void testFindByField() {

  }

  @Test
  public void count() {

  }

  @Test
  public void testCount() {

  }

  @Test
  public void findPageLite() {

  }

  @Test
  public void testFindPageLite() {

  }

  @Test
  public void testFindPageLite1() {

  }

  @Test
  public void testFindPageLite2() {

  }

  @Test
  public void findPage() {

  }

  @Test
  public void testFindPage() {

  }

  @Test
  public void testFindPage1() {

  }

  @Test
  public void testFindPage2() {

  }

  @Test
  public void execute() {

  }

  @Test
  public void testExecute() {

  }

  @Test
  public void testExecute1() {

  }

  @Test
  public void create() {

    Snowfake snowfake = new Snowfake();
    snowfake.setCreated(new Date());
    snowfake.setName("create test");
    snowfake.setDisable(false);
    snowfake.setPrice(new BigDecimal(199));
    snowfake.setType(TypeEnum.DRINK);
    snowfake.setConfig("");

    try (Database db = DBTemplate.open()) {
      db.create(Snowfake.class, snowfake);
    }

  }

  @Test
  public void testCreate() {

  }

  @Test
  public void update() {

  }

  @Test
  public void testUpdate() {

  }

  @Test
  public void testUpdate1() {

  }

  @Test
  public void testUpdate2() {

  }

  @Test
  public void delete() {

  }

  @Test
  public void testDelete() {

  }

  @Test
  public void deleteByIds() {

  }

  @Test
  public void deleteById() {

  }

}