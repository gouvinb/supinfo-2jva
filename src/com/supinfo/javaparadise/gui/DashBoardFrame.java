package com.supinfo.javaparadise.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class DashBoardFrame extends JFrame {
  public DashBoardFrame() {
    this.setSize(600, 400);
    this.setTitle("Java Paradise - DashBoard");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setJMenuBar(buildJMenu());

    this.setVisible(true);
  }

  private JMenuBar buildJMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu menu = new JMenu("Menu");

    JMenuItem addPlaceMenuItem = new JMenuItem("Add a place");
    addPlaceMenuItem.addActionListener(e -> {
      JFrame frame = new AddPlaceFrame();
      frame.setAlwaysOnTop(true);
      frame.setVisible(true);

    });

    JMenuItem addTripMenuItem = new JMenuItem("Add a trip");
    addTripMenuItem.addActionListener(e -> {
      JFrame frame;
      frame = new AddTripFrame();
      frame.setAlwaysOnTop(true);
      frame.setVisible(true);
    });

    JMenuItem quitMenuItem = new JMenuItem("Quit");
    quitMenuItem.addActionListener(arg0 -> {
      setVisible(false);
      dispose();

    });

    menu.add(addPlaceMenuItem);
    menu.add(addTripMenuItem);
    menu.add(quitMenuItem);

    menuBar.add(menu);
    return menuBar;


  }
}
