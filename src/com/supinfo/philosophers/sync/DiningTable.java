package com.supinfo.philosophers.sync;

import com.supinfo.philosophers.Fork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouvinb on 01/03/2017.
 */
class DiningTable {

  private final List<Philosopher> philosophers;
  private final List<Fork> forks;

  private int numberOfSeat;

  public DiningTable() {
    philosophers = new ArrayList<>();
    forks = new ArrayList<>();
  }

  public void addPhilosopher(Philosopher philosopher) {
    philosopher.setDiningTable(this);
    philosophers.add(philosopher);
    forks.add(new Fork());
    numberOfSeat++;
  }

  synchronized Fork[] askForForks(Philosopher philosopher) throws InterruptedException {
    int seatNumber = philosophers.indexOf(philosopher);
    Fork[] forks = new Fork[2];
    forks[0] = this.forks.get((seatNumber + 1) % numberOfSeat);
    forks[1] = this.forks.get(seatNumber);
    while (!(forks[0].isSyncFree() && forks[1].isSyncFree())) {
      wait();
    }
    for (Fork fork : forks) {
      fork.takeFork(philosopher);
    }
    return forks;
  }
}