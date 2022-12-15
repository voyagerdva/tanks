package nn.radio.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static nn.radio.model.Constants.*;
//import static nn.radio.model.Constants.TANK_HEIGHT;

public class Tank extends JPanel implements ActionListener, MouseListener, KeyListener {

    private Color backgroundColor = Color.PINK;

    float Y;
    float X;
    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float speed = 0.25F;

    private Color tankColorMain;
    private Color tankColor1 = Color.WHITE;
    private Color tankColor2 = Color.YELLOW;
    private Color tankColor3 = Color.GRAY;

    public static float TANK_WIDTH = 50F;
    public static float TANK_HEIGHT = 50F;

    private boolean alreadyClicked = false;

    public Tank(float x, float y, Color c) {
        this.X = x;
        this.Y = y;
        this.tankColorMain = c;

        this.setFocusable(false);

        addMouseListener(this);
        addKeyListener(this);
    }

    public void move(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect((int) X, (int) Y, (int) TANK_WIDTH, (int) TANK_HEIGHT);

        if (X >= SCENA_WIDTH - TANK_WIDTH - 10) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_WIDTH - TANK_WIDTH - 15;
        }

        if (X < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_BORDER + 15;
        }

        if (Y >= SCENA_HEIGTH - TANK_HEIGHT - 10) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_HEIGTH - TANK_HEIGHT - 15;
        }

        if (Y < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_BORDER + 15;
        }

        X = X + deltaX;
        Y = Y + deltaY;
        g.setColor(tankColorMain);
        g.fillRect((int) X, (int) Y, (int) TANK_WIDTH, (int) TANK_HEIGHT);
    }

    public void keyEventPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            deltaX = -speed;
            deltaY = 0;
            tankColorMain = tankColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            deltaX = speed;
            deltaY = 0;
            tankColorMain = tankColor3;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            deltaX = 0;
            deltaY = -speed;
            tankColorMain = tankColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            deltaX = 0;
            deltaY = speed;
            tankColorMain = tankColor3;
        }
    }

    public void keyEventReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            deltaX = 0;
            deltaY = 0;
            tankColorMain = tankColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            deltaX = 0;
            deltaY = 0;
            tankColorMain = tankColor3;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            deltaX = 0;
            deltaY = 0;
            tankColorMain = tankColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            deltaX = 0;
            deltaY = 0;
            tankColorMain = tankColor3;
        }
    }

    public void  mouseEventClicked(MouseEvent e) {
        if ((       e.getPoint().getX() <= X + TANK_WIDTH)
                && (e.getPoint().getX() >= X)
                && (e.getPoint().getY() <= Y + TANK_HEIGHT)
                && (e.getPoint().getY() >= Y)
        ) {
            this.setFocusable(true);
//            this.requestFocusInWindow();
            this.grabFocus();
            System.out.printf("CLICKED on x=%s  y=%s\n", e.getPoint().getX(), e.getPoint().getY());

            if (alreadyClicked) {
                tankColorMain = tankColor1;
                alreadyClicked = false;
            } else {
                tankColorMain = tankColor2;
                alreadyClicked = true;
            }
        }
    }

//=======================================================================

    @Override
    public void keyPressed(KeyEvent e) {
//        this.keyEventPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        this.keyEventReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


//==========================================================================

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        this.mouseEventClicked(e);
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
