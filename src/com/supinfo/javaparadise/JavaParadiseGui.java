package com.supinfo.javaparadise;

import com.supinfo.javaparadise.gui.AddPlaceFrame;
import com.supinfo.javaparadise.gui.AddTripFrame;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class JavaParadiseGui {

  public JavaParadiseGui() {
    System.out.println("Initialisation de JavaParadiseGui");
  }

  public void runJavaParadiseGuiPlace() {
    new AddPlaceFrame();
  }

  public void runJavaParadiseGuiTrip() {
    new AddTripFrame();
  }
}
