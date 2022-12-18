package nn.radio.model;

import nn.radio.dto.TankDto;
import nn.radio.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scena extends JPanel implements ActionListener, MouseListener, KeyListener {

    Map<String, Tank> tankMap = new HashMap<>();
    Map<String, User> userMap = new HashMap<>();

    ServerThread tankTread;
//===========================================================================

    public Scena () {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addMouseListener(this);
        addKeyListener(this);

        createUser("Andy");
        createUser("Kirry");

        createTank( userMap.get(0),200F, 100F);
        createTank( userMap.get(0),200F, 400F);

        createTank(userMap.get(1),900F, 100F);
        createTank(userMap.get(1),900F, 400F);


        tankTread = new ServerThread(tankMap);
        tankTread.start();

    }

    public Tank createTank(User user){
        return createTank( user,200F, 100F);
    }

    public Tank createTank(User user, float x, float y){
        Tank tank = new Tank(UUID.randomUUID().toString(), user,x, y);
        tankMap.put(tank.getId(),  tank);
        return tank;
    }

    public User createUser(String name){
        User user = new User(UUID.randomUUID().toString(), name);
        userMap.put(user.id, user);
        return user;
    }

    public void updateTankMap(Map<String, TankDto> map){

    }

    @Override
    public void paintComponent (Graphics g) {
        // отрисовка всех объектов
        intersectChargeAndTanks();
        tankMap.values().forEach(t -> t.draw(g));
        repaint();
    }

    private void intersectChargeAndTanks () {
        tankMap.values().forEach(tank -> {
            tankMap.values().forEach(t -> {
                if(!t.equals(tank)) {
                    t.chargeList.forEach( charge -> {
                        if(tank.intersect(charge)){
                            tank.makeDead();
                            charge.alive = false;
                            return;
                        }
                    });
                }
            });
        });
    }


//===================================================================================

    @Override
    public void keyPressed (KeyEvent e) {
        tankMap.values().stream().filter(t->t.isFocusable())
                .findFirst()
                .get()
                .keyEventPressed(e);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        tankMap.values().stream().filter(t->t.isFocusable())
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
        tankMap.values().forEach(t -> t.mouseEventClicked(e));
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
