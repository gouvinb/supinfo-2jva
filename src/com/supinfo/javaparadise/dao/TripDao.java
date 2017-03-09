package com.supinfo.javaparadise.dao;

import com.supinfo.javaparadise.model.Trip;

import java.util.List;

/**
 * Created by gouvinb on 01/03/2017.
 */
public interface TripDao {

  Long createTrip(Trip trip);

  Trip findTripById(Long id);

  List<Trip> findAllTrip();

  boolean removeTrip(Trip trip);
}