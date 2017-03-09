package com.supinfo.javaparadise.gui;

import com.supinfo.javaparadise.dao.DaoFactory;
import com.supinfo.javaparadise.dao.PlaceDao;
import com.supinfo.javaparadise.model.Place;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class AddPlaceFrame extends JFrame {

  private JTextField placeName;
  private final PlaceDao placeDao;

  public AddPlaceFrame() {
    placeDao = DaoFactory.getPlaceDao();
    init();
  }

  private void init() {
    placeName = new JTextField();
    placeName.setPreferredSize(new Dimension(150, (int) placeName.getPreferredSize().getHeight()));
    JButton placeSubmit = new JButton("Add place");
    placeSubmit.addActionListener(e -> {
      Place place = new Place();
      place.setName(placeName.getText());
      Long id = placeDao.createPlace(place);
      JOptionPane.showConfirmDialog(AddPlaceFrame.this, "Place added into DB with ID: " + id, "Message", JOptionPane.DEFAULT_OPTION);
    });

    JPanel rootPane = new JPanel();
    rootPane.add(placeName);
    rootPane.add(placeSubmit);

    this.setTitle("Add place");
    this.setSize(280, 70);
    // this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setContentPane(rootPane);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
}
