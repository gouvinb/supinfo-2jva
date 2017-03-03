package com.supinfo.javaparadise.dao;

import com.supinfo.javaparadise.dao.jdbc.JdbcPlaceDao;
import com.supinfo.javaparadise.dao.jdbc.JdbcTripDao;
import com.supinfo.javaparadise.util.ConnectionManager;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class DaoFactory {
  private DaoFactory() {

  }

  public static PlaceDao getPlaceDao() {
    return new JdbcPlaceDao(ConnectionManager.getConnection());
  }

  public static TripDao getTripDao() {
    return new JdbcTripDao(ConnectionManager.getConnection());
  }
}