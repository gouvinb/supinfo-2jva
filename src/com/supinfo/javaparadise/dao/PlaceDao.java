package com.supinfo.javaparadise.dao;

import com.supinfo.javaparadise.model.Place;

import java.util.List;

/**
 * Created by gouvinb on 01/03/2017.
 */
public interface PlaceDao {

  Long createPlace(Place place);

  Place findPlaceById(Long id);

  boolean updatePlace(Place place);

  boolean removePlace(Place place);

  List<Place> findAllPlaces();
}