package com.supinfo.javaparadise.dao.jdbc;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import com.supinfo.javaparadise.dao.PlaceDao;
import com.supinfo.javaparadise.model.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class JdbcPlaceDao extends JdbcDao implements PlaceDao {

  public JdbcPlaceDao(Connection connection) {
    super(connection);
  }

  @Override
  public Long createPlace(Place place) {
    try (PreparedStatement statement = getConnection().prepareStatement("INSERT INTO places(name) VALUES (?)",
        RETURN_GENERATED_KEYS)) {
      statement.setString(1, place.getName());
      statement.executeUpdate();
      ResultSet resultSet = statement.getGeneratedKeys();
      resultSet.next();
      Long id = resultSet.getLong(1);
      getConnection().commit();
      return id;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to insert this place: " + place, e);
    }
  }

  @Override
  public Place findPlaceById(Long id) {
    try (PreparedStatement statement = getConnection().prepareStatement("SELECT id, name FROM places WHERE id = ?")) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return toPlace(resultSet);
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to retrieve place with id: " + id, e);
    }
  }

  @Override
  public boolean updatePlace(Place place) {
    try (PreparedStatement statement = getConnection().prepareStatement("UPDATE places SET name = ? WHERE id = ?")) {
      statement.setString(1, place.getName());
      statement.setLong(2, place.getId());
      boolean result = statement.executeUpdate() > 0;
      getConnection().commit();
      return result;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to update this place: " + place, e);
    }
  }

  @Override
  public boolean removePlace(Place place) {
    try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM places WHERE id = ?")) {
      statement.setLong(1, place.getId());
      boolean result = statement.executeUpdate() > 0;
      getConnection().commit();
      return result;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to delete this place: " + place, e);
    }
  }

  @Override
  public List<Place> findAllPlaces() {
    try (PreparedStatement statement = getConnection().prepareStatement("SELECT id, name FROM places")) {
      List<Place> places = new ArrayList<>();
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        places.add(toPlace(resultSet));
      }
      return places;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to find all places");
    }
  }

  private Place toPlace(ResultSet resultSet) throws SQLException {
    Place place = new Place();
    place.setId(resultSet.getLong(1));
    place.setName(resultSet.getString(2));
    return place;
  }

  @Override
  protected void finalize() throws Throwable {
    super.close();
    super.finalize();
  }
}
