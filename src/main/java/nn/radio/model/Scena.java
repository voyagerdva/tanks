package nn.radio.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.stream.IntStream;

import static nn.radio.model.Constants.*;

public class Scena extends JPanel implements ActionListener, MouseListener, KeyListener {


//===========================================================================

    public Scena() {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();

        addMouseListener(this);
        addKeyListener(this);
    }

    Tank tank1 = new Tank(100F, 100F, Color.DARK_GRAY);
    Tank tank2 = new Tank(400F, 400F, Color.CYAN);

    @Override
    public void paintComponent(Graphics g) {
        // отрисовка всех объектов
        drawTank1(g);
        drawTank2(g);

        repaint();
    }

    private void drawTank1(Graphics g) {
        tank1.move(g);
    }

    private void drawTank2(Graphics g) {
        tank2.move(g);
    }



//===================================================================================

    @Override
    public void keyPressed(KeyEvent e) {
        if (tank1.isFocusable()) {
            tank2.setFocusable(false);
            tank1.keyEventPressed(e);
        }

        if (tank2.isFocusable()) {
            tank1.setFocusable(false);
            tank2.keyEventPressed(e);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (tank1.isFocusable()) {
            tank2.setFocusable(false);
            tank1.keyEventReleased(e);
        }

        if (tank2.isFocusable()) {
            tank1.setFocusable(false);
            tank2.keyEventReleased(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }



//===================================================================================

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((       e.getPoint().getX() <= tank1.X + tank1.TANK_WIDTH)
                && (e.getPoint().getX() >= tank1.X)
                && (e.getPoint().getY() <= tank1.Y + tank1.TANK_HEIGHT)
                && (e.getPoint().getY() >= tank1.Y)
        ) {
            tank1.setFocusable(true);
//            tank1.requestFocusInWindow();
            tank1.grabFocus();
            tank2.setFocusable(false);
            System.out.printf("CLICKED on x=%s  y=%s\n", e.getPoint().getX(), e.getPoint().getY());

//            if (alreadyClicked) {
//                tankColorMain = tankColor1;
//                alreadyClicked = false;
//            } else {
//                tankColorMain = tankColor2;
//                alreadyClicked = true;
//            }
        }

        if ((       e.getPoint().getX() <= tank2.X + tank2.TANK_WIDTH)
                && (e.getPoint().getX() >= tank2.X)
                && (e.getPoint().getY() <= tank2.Y + tank2.TANK_HEIGHT)
                && (e.getPoint().getY() >= tank2.Y)
        ) {
            tank2.setFocusable(true);
//            tank2.requestFocusInWindow();
            tank2.grabFocus();
            tank1.setFocusable(false);
            System.out.printf("CLICKED on x=%s  y=%s\n", e.getPoint().getX(), e.getPoint().getY());

//            if (alreadyClicked) {
//                tankColorMain = tankColor1;
//                alreadyClicked = false;
//            } else {
//                tankColorMain = tankColor2;
//                alreadyClicked = true;
//            }
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
