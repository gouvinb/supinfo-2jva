package com.supinfo.javaparadise.gui.model;

import com.supinfo.javaparadise.model.Place;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class PlaceComboBoxModel implements ComboBoxModel<Place> {

  private final List<Place> places;
  private Place selectedPlace;

  public PlaceComboBoxModel(List<Place> places) {
    this.places = places;
  }

  @Override
  public void setSelectedItem(Object anItem) {
    selectedPlace = (Place) anItem;
  }

  @Override
  public Place getSelectedItem() {
    return selectedPlace;
  }

  @Override
  public int getSize() {
    return places.size();
  }

  @Override
  public Place getElementAt(int index) {
    return places.get(index);
  }

  @Override
  public void addListDataListener(ListDataListener l) {

  }

  @Override
  public void removeListDataListener(ListDataListener l) {

  }
}