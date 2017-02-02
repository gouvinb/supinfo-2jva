package com.supinfo.gameoflife;

public interface Cell {

  Cell newGeneration(int numberOfNeighbours);

  String getAsString();

  boolean isAlive();
}