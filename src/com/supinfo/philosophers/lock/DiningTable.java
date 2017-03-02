package com.supinfo.philosophers.lock;

import com.supinfo.philosophers.Fork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gouvinb on 01/03/2017.
 */
class DiningTable {

  private final List<Philosopher> philosophers;
  private final List<Fork> forks;

  private final Lock lock = new ReentrantLock();
  private final Condition condition = lock.newCondition();

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

  Fork[] askForForks(Philosopher philosopher) throws InterruptedException {
    lock.lock();
    int seatNumber = philosophers.indexOf(philosopher);
    Fork[] forks = new Fork[2];
    forks[0] = this.forks.get((seatNumber + 1) % numberOfSeat);
    forks[1] = this.forks.get(seatNumber);
    while (!(forks[0].isLockFree() && forks[1].isLockFree())) {
      condition.await();
    }
    for (Fork fork : forks) {
      fork.takeFork(philosopher);
    }
    lock.unlock();
    return forks;
  }

  Condition getCondition() {
    return condition;
  }

  Lock getLock() {
    return lock;
  }
}