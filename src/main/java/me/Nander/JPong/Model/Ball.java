package me.Nander.JPong.Model;

public class Ball {
    int Groesse = 0;
    float X = 0;
    float Y = 0;
    int speedX = 0;
    int speedY = 0;
    public Ball(int Xneu, int Yneu, int speedXneu, int speedYneu, int Groesseneu) {
        X = Xneu;
        Y = Yneu;
        speedX = speedXneu;
        speedY = speedYneu;
        Groesse = Groesseneu;
    };

    public float BallX () {
        return X;
    }
    public float BallY () {
        return Y;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    public float RechtsObenX () {
        return X+Groesse;
    }
    public float RechtsUntenX () {
        return X+Groesse;
    }
    public float LinksObenX () {
        return X;
    }
    public float LinksUntenX () {
        return X;
    }
    public float RechtsObenY () {
        return Y;
    }
    public float RechtsUntenY () {
        return Y+Groesse;
    }
    public float LinksObenY () {
        return Y;
    }
    public float LinksUntenY () {
        return Y+Groesse;
    }
    public int Groesse () {
        return Groesse;
    }
    public void setGroesse (int Groesse) {
        this.Groesse = Groesse;
    }



}