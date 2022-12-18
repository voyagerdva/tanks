package nn.radio.server;

import nn.radio.model.Tank;
import nn.radio.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerThread extends Thread{


    private boolean alive = true;
    public Map<String, Tank>  tankMap;
    public List<User> userList;

    public ServerThread(Map<String, Tank> tankMap){
        this.tankMap = tankMap;

    }

    @Override
    public void run () {
        while (alive){
            tankMap.values().forEach(t -> {
                t.move();
            });
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }
}
