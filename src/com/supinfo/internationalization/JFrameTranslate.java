package com.supinfo.internationalization;

import java.awt.Container;
import java.awt.HeadlessException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Created by gouvinb on 09/03/2017.
 */
public class JFrameTranslate extends JFrame {

  private static final String TITLE = "frame_name";
  private static final String LANG = "lang";

  private ResourceBundle bundle = ResourceBundle.getBundle("com.supinfo.internationalization.strings");

  public JFrameTranslate() throws HeadlessException {
    System.out.println("init JFrameTranslate !");
  }

  public void runJFrameTranslate() {
    this.setSize(600, 400);
    this.setTitle(bundle.getString(TITLE));
    this.setContentPane(buildContentPane());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  private Container buildContentPane() {
    JPanel rootPane = new JPanel();

    JButton jButton = new JButton(bundle.getString(LANG));
    jButton.addActionListener(e -> {
      JFrameTranslate.this.dispose();
      if(Locale.getDefault() == Locale.ENGLISH){
        Locale.setDefault(Locale.FRANCE);
      } else {
        Locale.setDefault(Locale.ENGLISH);
      }
      bundle = ResourceBundle.getBundle("com.supinfo.internationalization.strings");

      jButton.setText(bundle.getString(LANG));
      jButton.revalidate();
      JFrameTranslate.this.setVisible(true);
    });

    rootPane.add(jButton);

    return rootPane;
  }
}
