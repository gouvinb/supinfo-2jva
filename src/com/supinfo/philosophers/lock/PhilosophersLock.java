package com.supinfo.philosophers.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class PhilosophersLock {
  public PhilosophersLock() {
    System.out.println("Initialisation de PhilosophersLock");
  }

  public void runPhilosophersLock() {
    DiningTable diningTable = new DiningTable();
    List<Thread> philosophers = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Philosopher philosopher = new Philosopher("Philosopher n°" + i);
      diningTable.addPhilosopher(philosopher);
      philosophers.add(new Thread(philosopher, philosopher.getName()));
    }
    for (Thread philosopher : philosophers) {
      philosopher.start();
    }
  }
}
