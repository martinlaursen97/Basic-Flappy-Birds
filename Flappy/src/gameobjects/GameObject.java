package gameobjects;

public abstract class GameObject {
    protected int posX;
    protected int posY;

    GameObject(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    abstract void move();

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }
}
