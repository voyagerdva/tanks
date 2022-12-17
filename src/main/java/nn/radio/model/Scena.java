package nn.radio.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Scena extends JPanel implements ActionListener, MouseListener, KeyListener {

    List<Tank> tankList = new ArrayList<>();
//===========================================================================

    public Scena () {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addMouseListener(this);
        addKeyListener(this);

        tankList.add(new Tank(200F, 100F));
        tankList.add(new Tank(200F, 400F));
        tankList.add(new Tank(200F, 600F));
        tankList.add(new Tank(200F, 700F));

        tankList.add(new Tank(900F, 100F));
        tankList.add(new Tank(900F, 400F));
        tankList.add(new Tank(900F, 600F));
        tankList.add(new Tank(900F, 700F));

    }

    @Override
    public void paintComponent (Graphics g) {
        // отрисовка всех объектов
        intersectChargeAndTanks();
        tankList.forEach(t -> t.draw(g));
        repaint();
    }

    private void intersectChargeAndTanks () {
        tankList.forEach(tank -> {
            tankList.forEach(t -> {
                if(!t.equals(tank)) {
                    t.chargeList.forEach( charge -> {
                        if(tank.intersect(charge)){
                            tank.makeDead();
                            charge.alive = false;
                        }
                    });
                }
            });
        });
    }


//===================================================================================

    @Override
    public void keyPressed (KeyEvent e) {
        tankList.stream().filter(t->t.isFocusable())
                .findFirst()
                .get()
                .keyEventPressed(e);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        tankList.stream().filter(t->t.isFocusable())
                .findFirst()
                .get()
                .keyEventReleased(e);
    }

    @Override
    public void keyTyped (KeyEvent e) {
    }


//===================================================================================

    @Override
    public void mouseClicked (MouseEvent e) {
        tankList.forEach(t -> t.mouseEventClicked(e));
    }


    @Override
    public void actionPerformed (ActionEvent e) {
    }

    @Override
    public void mousePressed (MouseEvent e) {
    }

    @Override
    public void mouseReleased (MouseEvent e) {
    }

    @Override
    public void mouseEntered (MouseEvent e) {
    }

    @Override
    public void mouseExited (MouseEvent e) {
    }

//====================================================================================
}
