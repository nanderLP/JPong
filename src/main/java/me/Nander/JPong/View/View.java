package me.Nander.JPong.View;

import me.Nander.JPong.Controller.PongController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import java.awt.Dimension;
import java.awt.Toolkit;

public class View extends JFrame{


    JLabel PunkteSpieler1 = new JLabel();
    JLabel PunkteSpieler2 = new JLabel();
    JLabel Pong1 = new JLabel();
    JLabel Pong2 = new JLabel();
    JLabel Ball = new JLabel();
    BufferedImage Zwischenspeicher;
    PongController Controller = new PongController();
    Dimension BildschirmGroesse = new Dimension();
    ActionListener OptionenListener;

    public View(Dimension Groesse, ActionListener OptionenListenerneu) {
        BildschirmGroesse = Groesse;
        OptionenListener = OptionenListenerneu;
        initialisieren();

    }
    private void initialisieren() {

        setTitle("Pong v.0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, BildschirmGroesse.width, BildschirmGroesse.height);
        setResizable(false);

        add(PunkteSpieler1);
        add(PunkteSpieler2);
        add(Pong1);
        add(Pong2);
        Zwischenspeicher = new BufferedImage(BildschirmGroesse.width, BildschirmGroesse.height, BufferedImage.TYPE_INT_RGB);
        Graphics Grafik = Zwischenspeicher.createGraphics();


        PunkteSpieler1.setFont(new Font("Dialog", 0, 50));
        PunkteSpieler1.setBounds(BildschirmGroesse.width/4, 50, 100, 100);
        PunkteSpieler1.setText("0");

        PunkteSpieler2.setFont(new Font("Dialog", 0, 50));
        PunkteSpieler2.setBounds(BildschirmGroesse.width/4*3, 50, 100, 100);
        PunkteSpieler2.setText("0");


        Pong1.setBackground(Color.RED);
        Pong1.setBounds(10, 400, 50, 200);
        Pong1.setOpaque(true);
        Pong1.setEnabled(false);

        Pong2.setBackground(Color.RED);
        Pong2.setBounds(BildschirmGroesse.width-10, 400, 50, 200);
        Pong2.setOpaque(true);
        Pong2.setEnabled(false);


        Ball.setBounds(0, 0, 100, 100);

        JMenu Menue2 = new JMenu ();
        JMenuItem Item = new JMenuItem ();
        Item.setText("Restart");
        Menue2.add(Item);
        JMenuItem Item2 = new JMenuItem ();
        Menue2.add(Item2);
        Menue2.setText("Game");
        Item2.setText("Exit");
        JMenuItem Optionen = new JMenuItem();
        JMenuBar Menue = new JMenuBar ();
        Menue.add(Menue2);
        Menue2.add(Optionen);
        setJMenuBar(Menue);
        Optionen.setText("Optionen");




        ActionListener Item2Listener = new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        };

        Item2.addActionListener(Item2Listener);

        ActionListener ItemListener = new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                Controller.Spielstart();


            }

        };

        ActionListener OptionenMenueListener = new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ActionEvent EventPause  = new ActionEvent(this, 0, "Pause");
                OptionenListener.actionPerformed(EventPause);
                Options Settings = new Options(OptionenListener);
                Settings.setVisible(true);
                Settings.setBounds(BildschirmGroesse.width/2, BildschirmGroesse.height/2, 300, 300);



            }

        };

        Item.addActionListener(ItemListener);
        Optionen.addActionListener(OptionenMenueListener);






    }
    public void AktualisiereSpieler1(int Spieler1X, int Spieler1Y, int Groesse) {
        Pong1.setBounds(Spieler1X, Spieler1Y, 50, Groesse);

    }
    public void AktualisiereSpieler2(int Spieler2X, int Spieler2Y, int Groesse) {
        Pong2.setBounds(Spieler2X, Spieler2Y, 50, Groesse);
    }
    public void AktualisiereBall(int BallX, int BallY, int Groesse) {
        Ball.setBounds(BallX, BallY, Groesse, Groesse);
        repaint();
    }
    public void AktualisierePunkte(int PunkteSP1, int PunkteSP2) {
        PunkteSpieler1.setText(""+PunkteSP1);
        PunkteSpieler2.setText(""+PunkteSP2);


    }


    @Override
    public void paint(Graphics g) {
        Graphics Grafik = Zwischenspeicher.getGraphics();
        Grafik.setColor(Color.white);
        Grafik.fillRect(0, 0, 1920, 1080);
        super.paint(Grafik);
        Grafik.setColor(Color.black);
        Grafik.fillOval(Ball.getX(), Ball.getY(), Ball.getWidth(), Ball.getHeight());
        g.drawImage(Zwischenspeicher, 0, 0, null);





    }
}
