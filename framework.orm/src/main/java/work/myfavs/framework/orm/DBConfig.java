package work.myfavs.framework.orm;

import java.sql.Connection;
import work.myfavs.framework.orm.meta.DbType;
import work.myfavs.framework.orm.meta.dialect.DialectFactory;
import work.myfavs.framework.orm.meta.dialect.IDialect;

public class DBConfig {

  //数据库方言
  protected IDialect dialect;
  //数据库类型
  protected String   dbType           = DbType.MYSQL;
  //一次批量插入数据的数量
  protected int      batchSize        = 200;
  //查询每次抓取数据的数量
  protected int      fetchSize        = 1000;
  //查询超时时间，单位：秒
  protected int      queryTimeout     = 60;
  //是否显示SQL
  protected boolean  showSql          = false;
  //是否显示查询结果
  protected boolean  showResult       = false;
  //每页最大记录数
  protected int      maxPageSize      = -1;
  //默认事务级别
  protected int      defaultIsolation = Connection.TRANSACTION_READ_COMMITTED;
  //终端ID
  protected long     workerId         = 1L;
  //数据中心ID
  protected long     dataCenterId     = 1L;

  /**
   * 获取数据库方言
   *
   * @return 数据库方言
   */
  public IDialect getDialect() {

    if (this.dialect == null) {
      this.dialect = DialectFactory.getInstance(this.dbType);
    }
    return this.dialect;
  }


  /**
   * 获取数据库类型
   *
   * @return 数据库类型
   */
  public String getDbType() {

    return dbType;
  }

  /**
   * 设置数据库类型
   *
   * @param dbType 数据库类型
   *
   * @return Configuration
   */
  public DBConfig setDbType(String dbType) {

    this.dbType = dbType;
    return this;
  }

  /**
   * 获取批处理大小
   *
   * @return 批处理大小
   */
  public int getBatchSize() {

    return batchSize;
  }

  /**
   * 设置批处理大小
   *
   * @param batchSize 批处理大小
   *
   * @return Configuration
   */
  public DBConfig setBatchSize(int batchSize) {

    this.batchSize = batchSize;
    return this;
  }

  /**
   * 获取抓取数据大小
   *
   * @return 抓取数据大小
   */
  public int getFetchSize() {

    return fetchSize;
  }

  /**
   * 设置抓取数据大小
   *
   * @param fetchSize 抓取数据大小
   *
   * @return Configuration
   */
  public DBConfig setFetchSize(int fetchSize) {

    this.fetchSize = fetchSize;
    return this;
  }

  /**
   * 获取查询超时时间
   *
   * @return 查询超时时间
   */
  public int getQueryTimeout() {

    return queryTimeout;
  }

  /**
   * 设置查询超时时间
   *
   * @param queryTimeout 查询超时时间
   *
   * @return Configuration
   */
  public DBConfig setQueryTimeout(int queryTimeout) {

    this.queryTimeout = queryTimeout;
    return this;
  }

  /**
   * 获取是否显示SQL
   *
   * @return 是否显示SQL
   */
  public boolean getShowSql() {

    return showSql;
  }

  /**
   * 设置是否显示SQL（日志级别INFO）
   *
   * @param showSql 是否显示SQL
   *
   * @return Configuration
   */
  public DBConfig setShowSql(boolean showSql) {

    this.showSql = showSql;
    return this;
  }

  /**
   * 获取是否显示查询结果
   *
   * @return 是否显示查询结果
   */
  public boolean getShowResult() {

    return showResult;
  }

  /**
   * 设置是否显示查询结果（日志级别INFO）
   *
   * @param showResult 是否显示查询结果
   *
   * @return Configuration
   */
  public DBConfig setShowResult(boolean showResult) {

    this.showResult = showResult;
    return this;
  }

  /**
   * 获取分页时每页最大记录数
   *
   * @return 分页时每页最大记录数
   */
  public long getMaxPageSize() {

    return maxPageSize;
  }

  /**
   * 设置分页时每页最大记录数(小于 0 为不限制)
   *
   * @param maxPageSize 分页时每页最大记录数
   *
   * @return Configuration
   */
  public DBConfig setMaxPageSize(int maxPageSize) {

    this.maxPageSize = maxPageSize;
    return this;
  }

  /**
   * 获取默认事务隔离级别
   *
   * @return int
   */
  public int getDefaultIsolation() {

    return this.defaultIsolation;
  }

  /**
   * 设置默认事务隔离级别
   *
   * @param defaultIsolation 事务隔离级别
   *
   * @return Configuration
   */
  public DBConfig setDefaultIsolation(int defaultIsolation) {

    this.defaultIsolation = defaultIsolation;
    return this;
  }

  /**
   * 获取终端ID
   *
   * @return 终端ID
   */
  public long getWorkerId() {

    return workerId;
  }

  /**
   * 设置终端ID
   *
   * @param workerId 终端ID
   *
   * @return Configuration
   */
  public DBConfig setWorkerId(long workerId) {

    this.workerId = workerId;
    return this;
  }

  /**
   * 获取数据中心ID
   *
   * @return 数据中心ID
   */
  public long getDataCenterId() {

    return dataCenterId;
  }

  /**
   * 设置数据中心ID
   *
   * @param dataCenterId 数据中心ID
   *
   * @return Configuration
   */
  public DBConfig setDataCenterId(long dataCenterId) {

    this.dataCenterId = dataCenterId;
    return this;
  }

}