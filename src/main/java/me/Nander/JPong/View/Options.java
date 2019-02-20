package me.Nander.JPong.View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Options extends JFrame implements WindowListener{
    JLabel BallGeschwindigkeit = new JLabel();
    JLabel Schlaegergroesse = new JLabel();
    JLabel BallGroesse = new JLabel();
    JSlider BallGeschwindigkeitaendern = new JSlider();
    JSlider SchlaegerGroesseaendern = new JSlider();
    JSlider BallGroesseaendern = new JSlider();
    JButton Speichern = new JButton();
    ActionListener OptionenListener;
    GridBagConstraints Raster = new GridBagConstraints();

    public Options(ActionListener OptionenListenerNeu) {
        OptionenListener = OptionenListenerNeu;
        setLayout(new GridBagLayout());
        initialisieren();
    }
    private void initialisieren() {
        BallGeschwindigkeit.setText("Ballgeschwindigkeit");
        Raster.gridx = 0;
        Raster.gridy = 0;
        add(BallGeschwindigkeit, Raster);
        Schlaegergroesse.setText("Schlaegergroesse");
        Raster.gridx = 0;
        Raster.gridy = 1;
        add(Schlaegergroesse, Raster);
        SchlaegerGroesseaendern.setMinimum(1);
        SchlaegerGroesseaendern.setMaximum(5);
        SchlaegerGroesseaendern.setValue(3);
        SchlaegerGroesseaendern.setPreferredSize(new Dimension(100, 50));
        SchlaegerGroesseaendern.setPaintTicks(true);
        SchlaegerGroesseaendern.setPaintLabels(true);
        SchlaegerGroesseaendern.setMajorTickSpacing(1);
        SchlaegerGroesseaendern.setMinorTickSpacing(1);
        Raster.gridx = 1;
        Raster.gridy = 1;
        add(SchlaegerGroesseaendern, Raster);
        addWindowListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Speichern.setText("Speichern");
        Raster.gridx = 0;
        Raster.gridy = 3;
        add(Speichern, Raster);
        BallGeschwindigkeitaendern.setMinimum(0);
        BallGeschwindigkeitaendern.setMaximum(10);
        BallGeschwindigkeitaendern.setValue(5);
        BallGeschwindigkeitaendern.setPreferredSize(new Dimension(100, 50));
        BallGeschwindigkeitaendern.setPaintTicks(true);
        BallGeschwindigkeitaendern.setPaintLabels(true);
        BallGeschwindigkeitaendern.setMajorTickSpacing(2);
        BallGeschwindigkeitaendern.setMinorTickSpacing(2);
        Raster.gridx = 1;
        Raster.gridy = 0;
        add(BallGeschwindigkeitaendern, Raster);
        BallGroesseaendern.setMinimum(0);
        BallGroesseaendern.setMaximum(10);
        BallGroesseaendern.setValue(5);
        BallGroesseaendern.setPreferredSize(new Dimension(100, 50));
        BallGroesseaendern.setPaintTicks(true);
        BallGroesseaendern.setPaintLabels(true);
        BallGroesseaendern.setMajorTickSpacing(2);
        BallGroesseaendern.setMinorTickSpacing(2);
        Raster.gridx = 1;
        Raster.gridy = 2;
        add(BallGroesseaendern, Raster);
        BallGroesse.setText("Ballgroesse");
        Raster.gridx = 0;
        Raster.gridy = 2;
        add(BallGroesse, Raster);


        ActionListener SpeichernListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ActionEvent SpeicherEvent = new ActionEvent(this, SchlaegerGroesseaendern.getValue()*100, "Spieler");
                OptionenListener.actionPerformed(SpeicherEvent);
                ActionEvent SpeicherEvent2 = new ActionEvent(this, BallGeschwindigkeitaendern.getValue()*2, "BallGeschwindigkeit");
                OptionenListener.actionPerformed(SpeicherEvent2);
                ActionEvent SpeicherEvent3 = new ActionEvent(this, BallGroesseaendern.getValue()*20, "BallGroesse");
                OptionenListener.actionPerformed(SpeicherEvent3);
                setVisible(false);
                ActionEvent StartEvent = new ActionEvent(this, 2, "Weiter");
                OptionenListener.actionPerformed(StartEvent);

            }
        };
        Speichern.addActionListener(SpeichernListener);

    }
    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void windowClosed(WindowEvent arg0) {
        ActionEvent Event = new ActionEvent(this, 1, "Weiter");
        OptionenListener.actionPerformed(Event);


    }
    @Override
    public void windowClosing(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }


}