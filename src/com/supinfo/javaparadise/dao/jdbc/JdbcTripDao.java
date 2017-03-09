package com.supinfo.javaparadise.dao.jdbc;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import com.supinfo.javaparadise.dao.TripDao;
import com.supinfo.javaparadise.model.Place;
import com.supinfo.javaparadise.model.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class JdbcTripDao extends JdbcDao implements TripDao {


  public JdbcTripDao(Connection connection) {
    super(connection);
  }

  @Override
  public Long createTrip(Trip trip) {
    try (PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
        "trips(departure, destination, price) VALUES (?, ?, ?)", RETURN_GENERATED_KEYS)) {
      statement.setLong(1, trip.getDeparture().getId());
      statement.setLong(2, trip.getDestination().getId());
      statement.setBigDecimal(3, trip.getPrice());
      statement.executeUpdate();
      ResultSet resultSet = statement.getGeneratedKeys();
      resultSet.next();
      Long id = resultSet.getLong(1);
      getConnection().commit();
      return id;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to insert this trip: " + trip, e);
    }
  }

  @Override
  public Trip findTripById(Long id) {
    try (PreparedStatement statement = getConnection().prepareStatement("SELECT " +
        "trip.id, departure.id, departure.name, destination.id, destination.name, trip.price " +
        "FROM trips trip " +
        "JOIN places departure ON departure.id = trip.departure " +
        "JOIN places destination ON destination.id = trip.destination " +
        "WHERE trip.id = ?")) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Trip trip = new Trip();
        trip.setId(resultSet.getLong(1));

        Place departure = new Place();
        departure.setId(resultSet.getLong(2));
        departure.setName(resultSet.getString(3));
        trip.setDeparture(departure);

        Place destination = new Place();
        destination.setId(resultSet.getLong(4));
        destination.setName(resultSet.getString(5));
        trip.setDestination(destination);

        trip.setPrice(resultSet.getBigDecimal(6));
        return trip;
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to find trip with id: " + id, e);
    }
  }

  @Override
  public List<Trip> findAllTrip() {
    List<Trip> allTrip = new ArrayList<>();

    try (PreparedStatement statement = getConnection().prepareStatement("SELECT " +
        "trip.id, departure.id, departure.name, destination.id, destination.name, trip.price " +
        "FROM trips trip " +
        "JOIN places departure ON departure.id = trip.departure " +
        "JOIN places destination ON destination.id = trip.destination ")) {
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Trip trip = new Trip();
        trip.setId(resultSet.getLong(1));

        Place departure = new Place();
        departure.setId(resultSet.getLong(2));
        departure.setName(resultSet.getString(3));
        trip.setDeparture(departure);

        Place destination = new Place();
        destination.setId(resultSet.getLong(4));
        destination.setName(resultSet.getString(5));
        trip.setDestination(destination);

        trip.setPrice(resultSet.getBigDecimal(6));

        allTrip.add(trip);
      }
      return allTrip;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to find all trip", e);
    }
  }

  @Override
  public boolean removeTrip(Trip trip) {
    try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM trips WHERE id = ?")) {
      statement.setLong(1, trip.getId());
      boolean result = statement.executeUpdate() > 0;
      getConnection().commit();
      return result;
    } catch (SQLException e) {
      throw new RuntimeException("Unable to delete this trip: " + trip, e);
    }
  }

  @Override
  protected void finalize() throws Throwable {
    super.close();
    super.finalize();
  }
}