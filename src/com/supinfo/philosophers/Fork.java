package com.supinfo.philosophers;


/**
 * Created by gouvinb on 01/03/2017.
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class Fork {

  private com.supinfo.philosophers.sync.Philosopher philosopherSync;
  private com.supinfo.philosophers.lock.Philosopher philosopherLock;

  public void leaveSyncFork() {
    philosopherSync = null;
  }

  public void leaveLockFork() {
    philosopherLock = null;
  }

  public void takeFork(com.supinfo.philosophers.sync.Philosopher philosopher) {
    this.philosopherSync = philosopher;
  }

  public void takeFork(com.supinfo.philosophers.lock.Philosopher philosopher) {
    this.philosopherLock = philosopher;
  }

  public boolean isSyncFree() {
    return philosopherSync == null;
  }

  public boolean isLockFree() {
    return philosopherLock == null;
  }
}
