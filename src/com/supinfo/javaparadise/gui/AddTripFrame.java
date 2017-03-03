package com.supinfo.javaparadise.gui;

import com.supinfo.javaparadise.dao.DaoFactory;
import com.supinfo.javaparadise.dao.PlaceDao;
import com.supinfo.javaparadise.dao.TripDao;
import com.supinfo.javaparadise.gui.model.PlaceComboBoxModel;
import com.supinfo.javaparadise.model.Place;
import com.supinfo.javaparadise.model.Trip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Created by gouvinb on 01/03/2017.
 */
public class AddTripFrame extends JFrame {

  private JComboBox<Place> departureComboBox;
  private JComboBox<Place> destinationComboBox;
  private JTextField priceField;
  private JButton tripSubmit;
  private JButton tripCancel;
  private final PlaceDao placeDao;
  private final TripDao tripDao;

  public AddTripFrame() {
    placeDao = DaoFactory.getPlaceDao();
    tripDao = DaoFactory.getTripDao();
    init();
  }

  private void init() {
    List<Place> places;
    try {
      places = placeDao.findAllPlaces();
    } catch (RuntimeException e) {
      places = new ArrayList<>();
    }
    departureComboBox = new JComboBox<>(new PlaceComboBoxModel(places));
    destinationComboBox = new JComboBox<>(new PlaceComboBoxModel(places));
    priceField = new JTextField();
    priceField.setPreferredSize(new Dimension(60, (int) priceField.getPreferredSize().getHeight()));
    tripSubmit = new JButton("Add trip");
    tripCancel = new JButton("Cancel");
    initListeners();

    JPanel rootPane = new JPanel(new BorderLayout());
    JPanel topPanel = new JPanel(new GridBagLayout());

    GridBagConstraints labelConstraints = buildLabelConstraints();
    GridBagConstraints fieldConstraints = buildFieldConstraints();
    GridBagConstraints comboBoxConstraints = buildComboBoxConstraints();

    JLabel fromLabel = new JLabel("From: ");
    topPanel.add(fromLabel, labelConstraints);
    topPanel.add(departureComboBox, comboBoxConstraints);

    incrementConstraintsGridY(labelConstraints, fieldConstraints, comboBoxConstraints);

    JLabel toLabel = new JLabel("To: ");
    topPanel.add(toLabel, labelConstraints);
    topPanel.add(destinationComboBox, comboBoxConstraints);

    incrementConstraintsGridY(labelConstraints, fieldConstraints, comboBoxConstraints);

    JLabel priceLabel = new JLabel("Price: ");
    topPanel.add(priceLabel, labelConstraints);
    topPanel.add(priceField, fieldConstraints);

    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(tripCancel);
    bottomPanel.add(tripSubmit);

    rootPane.add(topPanel, BorderLayout.NORTH);
    rootPane.add(bottomPanel, BorderLayout.SOUTH);

    this.setTitle("Add trip");
    this.setSize(260, 170);
    this.setMinimumSize(new Dimension(260, 100));
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setContentPane(rootPane);
    this.setVisible(true);
  }

  private void incrementConstraintsGridY(GridBagConstraints labelConstraints, GridBagConstraints fieldConstraints, GridBagConstraints comboBoxConstraints) {
    labelConstraints.gridy++;
    comboBoxConstraints.gridy++;
    fieldConstraints.gridy++;
  }

  private GridBagConstraints buildComboBoxConstraints() {
    GridBagConstraints comboBoxConstraints = new GridBagConstraints();
    comboBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
    comboBoxConstraints.insets = new Insets(5, 5, 5, 5);
    comboBoxConstraints.weightx = 2;
    comboBoxConstraints.gridy = 0;
    return comboBoxConstraints;
  }

  private GridBagConstraints buildFieldConstraints() {
    GridBagConstraints fieldConstraints = new GridBagConstraints();
    fieldConstraints.anchor = GridBagConstraints.WEST;
    fieldConstraints.insets = new Insets(5, 5, 5, 5);
    fieldConstraints.gridy = 0;
    return fieldConstraints;
  }

  private GridBagConstraints buildLabelConstraints() {
    GridBagConstraints labelConstraints = new GridBagConstraints();
    labelConstraints.anchor = GridBagConstraints.EAST;
    labelConstraints.insets = new Insets(5, 5, 5, 5);
    labelConstraints.gridy = 0;
    return labelConstraints;
  }

  private void initListeners() {
    tripSubmit.addActionListener(e -> {
      Trip trip = new Trip();
      trip.setDeparture((Place) departureComboBox.getSelectedItem());
      trip.setDestination((Place) destinationComboBox.getSelectedItem());
      trip.setPrice(new BigDecimal(priceField.getText()));
      Long id = tripDao.createTrip(trip);
      JOptionPane.showConfirmDialog(AddTripFrame.this, "Trip added into DB with ID: " + id, "Message", JOptionPane.DEFAULT_OPTION);
    });
    tripCancel.addActionListener(e -> {
      AddTripFrame.this.dispose();
      System.exit(0);
    });
  }
}