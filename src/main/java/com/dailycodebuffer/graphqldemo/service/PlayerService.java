package com.dailycodebuffer.graphqldemo.service;

import com.dailycodebuffer.graphqldemo.model.Player;
import com.dailycodebuffer.graphqldemo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private List<Player> playerList = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll(){
        return playerList;
    }

    public Optional<Player> findOne(Integer id){
        return playerList.stream().filter(player -> player.Id() == id).findFirst();
    }

    public Player create(String name, Team team){
        Player player = new Player(id.incrementAndGet(), name, team);
        playerList.add(player);
        return player;
    }

    public Player delete(Integer id){
        Player player = playerList.stream().filter(p -> p.Id() == id).findFirst().orElseThrow(() -> new IllegalArgumentException());
        playerList.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team){
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> optional = playerList.stream().filter(p -> p.Id() == id).findFirst();
        if(optional.isPresent()){
            Player player = optional.get();
            int index = playerList.indexOf(player);
            playerList.set(index, updatedPlayer);
        }else{
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }

    //@PostConstruct注解，用于标记在依赖注入完成后应该被自动调用的方法
    @PostConstruct
    private void init(){
        playerList.add(new Player(id.incrementAndGet(), "Sam", Team.NY));
        playerList.add(new Player(id.incrementAndGet(), "Toni", Team.CA));
        playerList.add(new Player(id.incrementAndGet(), "Kros", Team.MI));
        playerList.add(new Player(id.incrementAndGet(), "Ronaldo", Team.WAS));
        playerList.add(new Player(id.incrementAndGet(), "Rony", Team.CA));
        playerList.add(new Player(id.incrementAndGet(), "Kim", Team.MI));
        playerList.add(new Player(id.incrementAndGet(), "Tim", Team.WAS));
    }
}
