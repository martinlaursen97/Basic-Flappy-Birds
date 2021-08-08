package main;

import gameobjects.Bird;
import gameobjects.Wall;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class ViewPort extends JPanel implements ActionListener, MouseListener {
    private final String NAME = "flap";
    public final static int WIDTH   = 500;
    public final static int HEIGHT  = 400;
    public static final int INITIAL_SPACING = 100;
    public static int spacing = INITIAL_SPACING;
    private int count = 0;

    private static final Bird bird = new Bird(50, HEIGHT/2, 10, new Color(255,215,0));
    private static ArrayList<Wall> walls = new ArrayList<>();

    private final int GAP = 100;
    private final int SIZE = 40;
    private final Color BACKGROUND = new Color(0,191,255);
    private final Color WALL_COLOR = new Color(50,255,50);
    private final Font FONT = new Font("Cambria", Font.BOLD, 40);
    private final Timer t = new Timer(1, this);

    void run() {
        JFrame frame = new JFrame(NAME);
        frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
        frame.pack();
        frame.add(new ViewPort());
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(BACKGROUND);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(WALL_COLOR);
        for (Wall wall : walls) {
            g.fillRect(wall.getPosX(), wall.getPosY()+(GAP /2), SIZE, 1000);
            g.fillRect(wall.getPosX(), wall.getPosY()-(GAP /2), SIZE, -1000);
        }

        g.setColor(bird.getCOLOR());
        g.fillRect(bird.getPosX(), bird.getPosY(), 10, 10);

        g.setFont(FONT);
        g.drawString(String.valueOf(bird.getScore()), WIDTH/2 , 30);

        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();

        count++;
        if (count >= spacing) {
            createWall();
            count = 0;
        }

        for (Wall wall : walls) {
            wall.move();
            if (wall.collision(bird)) {
                walls.clear();
                bird.reset();
                break;
            }
            else if (bird.getPosX() == wall.getPosX() + (SIZE /2)) {
                bird.gainPoint();
                spacing--;
            }
            else if (wall.getPosX() < -SIZE) {
                walls.remove(wall);
                break;
            }
        }

        repaint();
    }

    void createWall() {
        int min = GAP;
        int max = HEIGHT - GAP;
        int y = new Random().nextInt(max+1 - min) + min;
        walls.add(new Wall(WIDTH, y, GAP, SIZE));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        bird.flap();
    }

    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}