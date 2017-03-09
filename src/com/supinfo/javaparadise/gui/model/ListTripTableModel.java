package com.supinfo.javaparadise.gui.model;

import com.supinfo.javaparadise.model.Place;
import com.supinfo.javaparadise.model.Trip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * Created by gouvinb on 09/03/2017.
 */
public class ListTripTableModel extends AbstractTableModel {

  private List<Trip> tripList = new ArrayList<>();
  private int coloumnCount = 4;

  public ListTripTableModel(List<Trip> tripList) {
    System.out.println(tripList);
    this.tripList = tripList;
  }

  @Override
  public int getRowCount() {
    try {
      System.out.println("rowCount=" + tripList.size());
      return tripList.size();
    } catch (NullPointerException e) {
      return 0;
    }
  }

  @Override
  public int getColumnCount() {
    System.out.println("coloumnCount=" + coloumnCount);
    return coloumnCount;
  }

  @Override
  public String getColumnName(int columnIndex) {
    System.out.println("columnIndex=" + columnIndex);
    switch (columnIndex) {
      case 0:
        return "ID";
      case 1:
        return "Departure";
      case 2:
        return "Destination";
      case 3:
        return "Price";
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    System.out.println("columnIndex=" + columnIndex);
    switch (columnIndex) {
      case 0:
        return Long.class;
      case 1:
      case 2:
        return Place.class;
      case 3:
        return BigDecimal.class;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Trip trip = tripList.get(rowIndex);
    System.out.println(trip);
    switch (columnIndex) {
      case 0:
        return trip.getId();
      case 1:
        return trip.getDeparture();
      case 2:
        return trip.getDestination();
      case 3:
        return trip.getPrice();
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

  }

  @Override
  public void addTableModelListener(TableModelListener l) {

  }

  @Override
  public void removeTableModelListener(TableModelListener l) {

  }
}
