package com.supinfo.javaparadise.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by gouvinb on 01/03/2017.
 */
abstract class JdbcDao {

  private final Connection connection;

  JdbcDao(Connection connection) {
    this.connection = connection;
  }

  Connection getConnection() {
    return connection;
  }

  void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException("Unable to close the connection", e);
    }
  }
}