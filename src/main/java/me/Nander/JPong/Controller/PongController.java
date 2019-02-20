package me.Nander.JPong.Controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import me.Nander.JPong.Model.Ball;
import me.Nander.JPong.Model.Player;
import me.Nander.JPong.View.View;

public class PongController implements KeyListener, WindowListener, ActionListener {
	Player Spieler1;
	Player Spieler2;
	Ball PongBall;
	View Spielfeld;
	boolean SpielLaeuft;
	Dimension BildschirmGroesse = new Dimension();

	public void Spielstart() {
		BildschirmGroesse = Toolkit.getDefaultToolkit().getScreenSize();
		Spieler1 = new Player(10, BildschirmGroesse.height / 2 - 300 / 2, 300);
		Spieler2 = new Player(BildschirmGroesse.width - 60, BildschirmGroesse.height / 2 - 300 / 2, 300);
		PongBall = new Ball(BildschirmGroesse.width / 2, BildschirmGroesse.height / 2, -10, -10, 100);
		Spielfeld = new View(BildschirmGroesse, this);
		Spielfeld.addKeyListener(this);
		Spielfeld.addWindowListener(this);
		Spielfeld.setVisible(true);
		Aktualisierer();
		StarteZeit();

	}

	@Override
	public void actionPerformed(ActionEvent Event) {
		if (Event.getActionCommand().equals("Pause")) {
			StoppeZeit();
		}
		if (Event.getActionCommand().equals("Weiter")) {
			StarteZeit();
		}
		if (Event.getActionCommand().equals("Spieler")) {
			Spieler1.setGroesse(Event.getID());
			Spieler2.setGroesse(Event.getID());

		}
		if (Event.getActionCommand().equals("BallGeschwindigkeit")) {
			if (PongBall.getSpeedX() <= 0) {
				PongBall.setSpeedX(Event.getID() - (Event.getID() * 2));
			} else {
				PongBall.setSpeedX(Event.getID());
			}
			if (PongBall.getSpeedY() <= 0) {
				PongBall.setSpeedY(Event.getID() - (Event.getID() * 2));
			} else {
				PongBall.setSpeedY(Event.getID());

			}

		}
		if (Event.getActionCommand().equals("BallGroesse")) {
			PongBall.setGroesse(Event.getID());
		}

	}

	public void StarteZeit() {
		Runnable Zuruecksetzen = new Runnable() {

			@Override
			public void run() {
				while (SpielLaeuft) {
					Bewegen();
					Aktualisierer();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		};
		Runnable BallBewegenThread = new Runnable() {

			@Override
			public void run() {
				while (SpielLaeuft) {
					BewegeBall();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		};
		Thread Zuruecksetzer = new Thread(Zuruecksetzen, "Pong Thread");
		Thread BallThread = new Thread(BallBewegenThread, "PongBall Thread");
		SpielLaeuft = true;
		Zuruecksetzer.start();
		BallThread.start();

	}

	public void StoppeZeit() {
		SpielLaeuft = false;
	}

	public void Aktualisierer() {
		Spielfeld.AktualisiereBall((int) PongBall.BallX(), (int) PongBall.BallY(), PongBall.Groesse());
		Spielfeld.AktualisierePunkte(Spieler1.PunkteSpieler(), Spieler2.PunkteSpieler());
		Spielfeld.AktualisiereSpieler1(Spieler1.SpielerX(), Spieler1.SpielerY(), Spieler1.getGroesse());
		Spielfeld.AktualisiereSpieler2(Spieler2.SpielerX(), Spieler2.SpielerY(), Spieler2.getGroesse());

	}

	public void Bewegen() {
		int Geschwindigkeit = 1;
		if (Spieler1.LeseHoch()) {
			if (Spieler1.SpielerY() > 0) {
				Spieler1.SetzeSpielerY(Spieler1.SpielerY() - Geschwindigkeit);
			}
		}
		if (Spieler1.LeseRunter()) {
			if (Spieler1.SpielerY() < BildschirmGroesse.height - Spieler1.getGroesse()) {
				Spieler1.SetzeSpielerY(Spieler1.SpielerY() + Geschwindigkeit);
			}
		}
		if (Spieler2.LeseHoch()) {
			if (Spieler2.SpielerY() > 0) {
				Spieler2.SetzeSpielerY(Spieler2.SpielerY() - Geschwindigkeit);
			}
		}
		if (Spieler2.LeseRunter()) {
			if (Spieler2.SpielerY() < BildschirmGroesse.height - Spieler2.getGroesse()) {
				Spieler2.SetzeSpielerY(Spieler2.SpielerY() + Geschwindigkeit);
			}
		}
		SchlaegerGetroffen();

	}

	private void SchlaegerGetroffen() {
		Rectangle Spieler1Rechteck = new Rectangle(Spieler1.SpielerX(), Spieler1.SpielerY(), 50, Spieler1.getGroesse());
		Rectangle Spieler2Rechteck = new Rectangle(Spieler2.SpielerX(), Spieler2.SpielerY(), 50, Spieler2.getGroesse());
		Rectangle BallRechteck = new Rectangle((int) PongBall.BallX(), (int) PongBall.BallY(), PongBall.Groesse(),
				PongBall.Groesse());
		if (Spieler1Rechteck.intersects(BallRechteck)) {
			if (Spieler1.RechtsObenX()-1 == PongBall.BallX()) {

				int speedX = PongBall.getSpeedX() * -1;
				PongBall.setSpeedX(speedX);
			}
		}
		if (Spieler2Rechteck.intersects(BallRechteck)) {
			if (Spieler2.LinksObenX()+1 == PongBall.RechtsObenX()) {

				int speedX = PongBall.getSpeedX() * -1;
				PongBall.setSpeedX(speedX);
			}
			// HA: Wieso keine Kollision vorkommt googlen und Schl�ger wird gr�sser wenn ein Punkt gemacht wird //
		}
	}

	public void BewegeBall() {
		PongBall.setX(PongBall.BallX() + PongBall.getSpeedX() / 10f);
		PongBall.setY(PongBall.BallY() + PongBall.getSpeedY() / 10f);
		if (PongBall.BallY() <= 0) {
			int speedY = PongBall.getSpeedY() * -1;
			PongBall.setSpeedY(speedY);
		}
		if (PongBall.BallX() <= 0) {
			Spieler2.PunktfuerSP();
			if (Spieler2.getGroesse() >= 200) {
				Spieler2.setGroesse(Spieler2.getGroesse() - 25);
			}

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PongBall.setX(BildschirmGroesse.width / 2);
			PongBall.setY(BildschirmGroesse.height / 2);
		}
		if (PongBall.BallX() >= BildschirmGroesse.getWidth()) {
			Spieler1.PunktfuerSP();
			if (Spieler1.getGroesse() >= 200) {
				Spieler1.setGroesse(Spieler1.getGroesse() - 25);
			}

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PongBall.setX(BildschirmGroesse.width / 2);
			PongBall.setY(BildschirmGroesse.height / 2);
		}
		if (PongBall.BallY() >= BildschirmGroesse.height) {
			int speedY = PongBall.getSpeedY() * -1;
			PongBall.setSpeedY(speedY);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			Spieler2.HochTrue();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			Spieler2.RunterTrue();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			Spieler1.HochTrue();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_S) {
			Spieler1.RunterTrue();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			Spieler2.HochFalse();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			Spieler2.RunterFalse();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			Spieler1.HochFalse();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_S) {
			Spieler1.RunterFalse();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		StoppeZeit();

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
