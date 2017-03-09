package com.supinfo.javaparadise.model;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class Place {

  private Long id;
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Place place = (Place) o;

    if (id != null ? !id.equals(place.id) : place.id != null) {
      return false;
    }
    return name != null ? name.equals(place.name) : place.name == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    //return "Place{" +
    //    "id=" + id +
    //    ", name='" + name + '\'' +
    //    '}';
    return name;
  }
}