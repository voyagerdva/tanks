package nn.radio.server;

import nn.radio.client.model.ClientTank;
import nn.radio.client.model.ClientUser;
import nn.radio.client.view.Scena;
import nn.radio.dto.KeyEventDto;
import nn.radio.dto.MouseEventDto;
import nn.radio.dto.TankDto;
import nn.radio.server.model.ServerTank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ServerThread extends Thread {


    private boolean alive = true;
    public Map<String, ServerTank> tankMap = new ConcurrentHashMap<>();
    public Map<String, ClientTank> clientTankMap = new ConcurrentHashMap<>();
    public List<ClientUser> clientUserList = new ArrayList<>();
    List<Scena> scenaList;

    public ServerThread () {
    }

    public void updateTankMap (Map<String, ClientTank> tMap) {
        tankMap.clear();
        tMap.entrySet().forEach(t -> {
            tankMap.put(t.getKey(), TankDto.fromTank(t.getValue()));
        });
    }

    public void updateScenaList (List<Scena> scenaList) {
        this.scenaList = scenaList;
    }

    @Override
    public void run () {
        while (alive) {
            tankMap.values().forEach(t -> {
                t.move();
            });
            intersectChargeAndTanks();
            scenaList.stream().forEach(sc ->
                    sc.updateTankMapWithDto(tankMap.values()
                            .stream()
                            .collect(Collectors.toMap(t1 -> t1.id,
                                    k1 -> TankDto.fromTank(k1)
                                    )
                            )
                    )
            );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void intersectChargeAndTanks () {
        tankMap.values().forEach(clientTank -> {
            tankMap.values().forEach(t -> {
                if(!t.equals(clientTank)) {
                    t.getTore().clientChargeList.forEach(charge -> {
                        if(clientTank.intersect(charge)){
                            clientTank.alive = false;
                            charge.alive = false;
                            return;
                        }
                    });
                }
            });
        });
    }

    public void keyPressed (KeyEventDto e) {
        tankMap.values().stream().filter(t -> t.isFocusable())
                .findFirst()
                .get()
                .keyEventPressed(e);
    }

    public void keyReleased (KeyEventDto e) {
        tankMap.values().stream().filter(t -> t.isFocusable())
                .findFirst()
                .get()
                .keyEventReleased(e);
    }

    public void mouseClicked (MouseEventDto e) {
        tankMap.values().forEach(t -> t.mouseEventClicked(e));
    }

    public void setAlive (boolean alive) {
        this.alive = alive;
    }
}
