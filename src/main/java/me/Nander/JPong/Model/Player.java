package me.Nander.JPong.Model;

public class Player {
    boolean Hoch = false;
    boolean Runter = false;
    int Punkte = 0;
    int X = 0;
    int Y = 0;
    int Groesse = 0;


    public Player(int Xneu, int Yneu, int Groesseneu) {
        X = Xneu;
        Y = Yneu;
        Groesse = Groesseneu;
    };
    public void HochTrue () {
        Hoch = true;
    }
    public void RunterTrue () {
        Runter = true;
    }
    public void HochFalse () {
        Hoch = false;
    }
    public void RunterFalse () {
        Runter = false;
    }
    public int PunkteSpieler () {
        return Punkte;
    }
    public void PunktfuerSP () {
        Punkte = Punkte+1;
    }
    public int SpielerX () {
        return X;
    }
    public int SpielerY () {
        return Y;
    }
    public void SetzeSpielerX (int SetzeX) {
        X = SetzeX;
    }
    public void SetzeSpielerY (int SetzeY) {
        Y = SetzeY;
    }
    public boolean LeseHoch () {
        return Hoch;
    }
    public boolean LeseRunter () {
        return Runter;
    }
    public int RechtsObenX () {
        return X+50;
    }
    public int RechtsUntenX () {
        return X+50;
    }
    public int LinksObenX () {
        return X;
    }
    public int LinksUntenX () {
        return X;
    }
    public int RechtsObenY () {
        return Y;
    }
    public int RechtsUntenY () {
        return Y+Groesse;
    }
    public int LinksObenY () {
        return Y;
    }
    public int LinksUntenY () {
        return Y+Groesse;
    }
    public int getGroesse() {
        return Groesse;
    }
    public void setGroesse(int groesse) {
        Groesse = groesse;
    }


}