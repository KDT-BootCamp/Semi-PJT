package com.project.project_train.openapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.project_train.openapi.dao.TrainItemDTO;
import com.project.project_train.openapi.domain.TrainItems;

@Service
public class TrainItemService {
    
    public List<TrainItemDTO> parshingJson(String str){

        System.out.println("debug parshingJson >>> " + str);

        ObjectMapper mapper = new ObjectMapper();
        List<TrainItemDTO> list = null;

        try{
            TrainItems items = mapper.readValue(str, TrainItems.class);
            list = items.getDatas();
            System.out.println("service pasing json size : " + list.size());
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
