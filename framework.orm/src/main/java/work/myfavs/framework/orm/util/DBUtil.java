package work.myfavs.framework.orm.util;

import cn.hutool.core.collection.CollectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import work.myfavs.framework.orm.meta.handler.PropertyHandlerFactory;
import work.myfavs.framework.orm.util.exception.DBException;

/**
 * 数据库工具类
 */
public class DBUtil {


  public static PreparedStatement getPstForQuery(Connection conn,
      String sql) throws SQLException {
    return conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
        ResultSet.CONCUR_READ_ONLY);
  }

  public static PreparedStatement getPstForQuery(Connection conn,
      String sql,
      Collection params)
      throws SQLException {

    final PreparedStatement pst = getPstForQuery(conn, sql);
    if (CollectionUtil.isNotEmpty(params)) {
      setParams(pst, params);
    }
    return pst;
  }

  public static PreparedStatement getPstForUpdate(Connection conn,
      boolean autoGeneratedPK,
      String sql,
      Collection params)
      throws SQLException {

    PreparedStatement pst = getPstForUpdate(conn, autoGeneratedPK, sql);

    if (CollectionUtil.isNotEmpty(params)) {
      setParams(pst, params);
    }
    return pst;
  }

  public static PreparedStatement getPstForUpdate(Connection conn,
      boolean autoGeneratedPK,
      String sql) throws SQLException {
    if (autoGeneratedPK) {
      return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    } else {
      return conn.prepareStatement(sql);
    }
  }

  public static int executeBatch(PreparedStatement pst,
      Collection<Collection> params,
      int batchSize) throws SQLException {
    int result = 0,
        execIdx = 0;
    for (Iterator<Collection> iterator = params.iterator(); iterator.hasNext(); ) {
      Collection param = iterator.next();
      setParams(pst, params);
      pst.addBatch();

      if (++execIdx % batchSize == 0) {
        result += Arrays.stream(pst.executeBatch()).sum();
        pst.clearBatch();
      }
    }

    result += Arrays.stream(pst.executeBatch()).sum();
    pst.clearBatch();

    return result;
  }

  /**
   * 设置 PreparedStatement 参数
   *
   * @param preparedStatement PreparedStatement
   * @param params            参数数组
   * @return PreparedStatement
   * @throws SQLException SQLException
   */
  private static PreparedStatement setParams(PreparedStatement preparedStatement,
      Collection params)
      throws SQLException {

    if (params != null && params.size() > 0) {
      int index = 1;
      for (Iterator iterator = params.iterator(); iterator.hasNext(); ) {
        PropertyHandlerFactory.addParameter(preparedStatement, index++, iterator.next());
      }
    }
    return preparedStatement;
  }

  public static int executeUpdate(PreparedStatement preparedStatement)
      throws SQLException {

    int result;
    Connection connection;

    connection = preparedStatement.getConnection();
    result = preparedStatement.executeUpdate();

    return result;
  }

  /**
   * 关闭数据库连接
   *
   * @param connection Connection
   */
  public static void close(Connection connection) {

    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new DBException(e);
      }
    }
  }

  /**
   * 关闭Statment
   *
   * @param statement Statement
   */
  public static void close(Statement statement) {

    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        throw new DBException(e);
      }
    }
  }

  /**
   * 关闭ResultSet
   *
   * @param resultSet ResultSet
   */
  public static void close(ResultSet resultSet) {

    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        throw new DBException(e);
      }
    }
  }

  /**
   * 关闭Connection、Statement、ResultSet
   *
   * @param connection Connection
   * @param statement  Statement
   * @param resultSet  ResultSet
   */
  public static void close(Connection connection,
      Statement statement,
      ResultSet resultSet) {

    close(resultSet);
    close(connection, statement);
  }

  /**
   * 关闭Connection、Statement
   *
   * @param connection Connection
   * @param statement  Statement
   */
  public static void close(Connection connection,
      Statement statement) {

    close(statement);
    close(connection);
  }

  /**
   * Statement、ResultSet
   *
   * @param statement Statement
   * @param resultSet ResultSet
   */
  public static void close(Statement statement,
      ResultSet resultSet) {

    close(statement);
    close(resultSet);
  }

}
