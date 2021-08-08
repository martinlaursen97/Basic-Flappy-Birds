package gameobjects;

import main.ViewPort;

import java.awt.*;

public class Bird extends GameObject{
    private int score = 0;
    private final int SIZE;
    private final double GRAVITY = 0.2;
    private final double VELOCITY = -5;
    private int S_0 = posY;
    private double t = 0;
    private final Color COLOR;

    public Bird(int posX, int posY, int size, Color color) {
        super(posX, posY);
        this.SIZE = size;
        this.COLOR = color;
    }

    @Override
    public void move() {
        t++;
        posY = (int) (GRAVITY /2 * Math.pow(t, 2) + VELOCITY * t + S_0);
    }

    public void flap() {
        t = 0;
        S_0 = posY;
    }

    public void gainPoint() {
        score++;
    }

    public void reset() {
        ViewPort.spacing = ViewPort.INITIAL_SPACING;
        posY = ViewPort.HEIGHT/2;
        score = 0;
        t = 0;
        S_0 = posY;
    }

    public int getScore() {
        return score;
    }

    public int getSIZE() {
        return SIZE;
    }

    public Color getCOLOR() {
        return COLOR;
    }
}
