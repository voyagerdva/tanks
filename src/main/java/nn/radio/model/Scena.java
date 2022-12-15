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

        tankList.add(new Tank(100F, 100F, Color.DARK_GRAY, this));
        tankList.add(new Tank(400F, 400F, Color.CYAN, this));
    }

    @Override
    public void paintComponent (Graphics g) {
        // отрисовка всех объектов
        tankList.forEach(t -> t.move(g));

        repaint();
    }


//===================================================================================

    @Override
    public void keyPressed (KeyEvent e) {
        tankList.forEach(t -> {
            if(t.isFocusable()){
                t.keyEventPressed(e);
            }
        });
    }

    @Override
    public void keyReleased (KeyEvent e) {
        tankList.forEach(t -> {
            if(t.isFocusable()){
                t.keyEventReleased(e);
            }
        });
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
