package com.supinfo.javaparadise.gui;

import com.supinfo.javaparadise.dao.DaoFactory;
import com.supinfo.javaparadise.dao.TripDao;
import com.supinfo.javaparadise.gui.model.ListTripTableModel;
import com.supinfo.javaparadise.model.Trip;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class DashBoardFrame extends JFrame {
  public DashBoardFrame() {
    this.setSize(600, 400);
    this.setTitle("Java Paradise - DashBoard");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setContentPane(buildPane());
    this.setJMenuBar(buildJMenu());
    this.setVisible(true);
  }

  private Container buildPane() {
    JPanel rootPane = new JPanel(new BorderLayout());

    JTable jTable = new JTable();
    TripDao tripDao = DaoFactory.getTripDao();
    List<Trip> tripList = tripDao.findAllTrip();
    ListTripTableModel listTripTableModel = new ListTripTableModel(tripList);
    jTable.setModel(listTripTableModel);

    JScrollPane tableTrip = new JScrollPane();
    tableTrip.setColumnHeaderView(jTable.getTableHeader());
    tableTrip.setViewportView(jTable);

    rootPane.add(tableTrip, BorderLayout.CENTER);

    return rootPane;
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
      System.exit(0);
    });

    menu.add(addPlaceMenuItem);
    menu.add(addTripMenuItem);
    menu.add(quitMenuItem);

    menuBar.add(menu);
    return menuBar;
  }
}
