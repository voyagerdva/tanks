package nn.radio.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.stream.IntStream;

import static nn.radio.model.Constants.*;

public class Scena extends JPanel implements ActionListener, MouseListener, KeyListener {

    float Y = 350F;
    float X = 5F;
    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float speed = 0.25F;

    private Color backgroundColor = Color.PINK;
    private Color rectColorMain = Color.DARK_GRAY;
    private Color rectColor1 = Color.WHITE;
    private Color rectColor2 = Color.YELLOW;
    private Color rectColor3 = Color.GRAY;

    private boolean alreadyClicked = false;

//===========================================================================

    public Scena() {
        super();
        addMouseListener(this);
        addKeyListener(this);
        this.setFocusable(true);
    }


    @Override

    public void paintComponent(Graphics g) {
        // отрисовка всех объектов
        drawFigure(g);

        repaint();
    }

    private void drawFigure(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect((int) X, (int) Y, (int) RECT_WIDTH, (int) RECT_HEIGHT);

        if (X >= SCENA_WIDTH - RECT_WIDTH - 10) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_WIDTH - RECT_WIDTH - 15;
        }

        if (X < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_BORDER + 15;
        }

        if (Y >= SCENA_HEIGTH - RECT_HEIGHT - 10) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_HEIGTH - RECT_HEIGHT - 15;
        }

        if (Y < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_BORDER + 15;
        }

        X = X + deltaX;
        Y = Y + deltaY;
        g.setColor(rectColorMain);
        g.fillRect((int) X, (int) Y, (int) RECT_WIDTH, (int) RECT_HEIGHT);

    }


//===================================================================================

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            deltaX = -speed;
            deltaY = 0;
            rectColorMain = rectColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            deltaX = speed;
            deltaY = 0;
            rectColorMain = rectColor3;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            deltaX = 0;
            deltaY = -speed;
            rectColorMain = rectColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            deltaX = 0;
            deltaY = speed;
            rectColorMain = rectColor3;
        }

        if (e.getKeyCode() == KeyEvent.VK_END) {
            deltaX = 0;
            deltaY = 0;
            rectColorMain = rectColor3;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            deltaX = 0;
            deltaY = 0;
            rectColorMain = rectColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            deltaX = 0;
            deltaY = 0;
            rectColorMain = rectColor3;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            deltaX = 0;
            deltaY = 0;
            rectColorMain = rectColor1;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            deltaX = 0;
            deltaY = 0;
            rectColorMain = rectColor3;
        }
    }

//===================================================================================

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getPoint().getX() <= X + RECT_WIDTH)
                && (e.getPoint().getX() >= X)
                && (e.getPoint().getY() <= Y + RECT_HEIGHT)
                && (e.getPoint().getY() >= Y)
        ) {
            if (alreadyClicked) {
                rectColorMain = rectColor1;
                alreadyClicked = false;
            } else {
                rectColorMain = rectColor2;
                alreadyClicked = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

//====================================================================================
}
