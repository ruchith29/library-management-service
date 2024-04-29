package com.nextrow.actuator.service;

import com.nextrow.actuator.service.entity.Children;
import com.nextrow.actuator.service.repository.ChildRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomActuator implements InfoContributor {

    @Autowired
    private ChildRepository childRepository;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String,List<Children>> mp=new HashMap<>();
        List<Children> children=childRepository.findAll();
        List<Children> playing=new ArrayList<>();
        List<Children> notPlaying=new ArrayList<>();
        for(Children i: children) {
            if (i.getAction().equals("Playing")) {
                playing.add(i);
            } else {
                notPlaying.add(i);
            }
        }
        mp.put("Playing",playing);
        mp.put("Not Playing",notPlaying);
        builder.withDetail("Children Status",mp);
    }
}
