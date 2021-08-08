package gameobjects;

public class Wall extends GameObject{
    private int gap;
    private int size;

    public Wall(int posX, int posY, int gap, int size) {
        super(posX, posY);
        this.gap = gap;
        this.size = size;
    }

    @Override
    public void move() {
        posX-=2;
    }

    public boolean collision(Bird bird) {
        return bird.getPosX() >= getPosX()  &&
               bird.getPosX() <= getPosX() + size &&
               !(bird.getPosY() >= posY - (gap/2) &&
               bird.getPosY() <= posY + ((gap/2) - (bird.getSIZE()/2)));
    }
}
