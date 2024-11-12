package com.project.project_train.openapi.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.project_train.openapi.dao.TrainCodeDTO;
import com.project.project_train.openapi.domain.TrainCodes;

import java.util.List;

@Service
public class TrainCodeService {
    
    public List<TrainCodeDTO> parshingJson(String str){

        System.out.println("debug parshingJson >>> " + str);

        ObjectMapper mapper = new ObjectMapper();
        List<TrainCodeDTO> list = null;

        try{
            TrainCodes items = mapper.readValue(str, TrainCodes.class);
            list = items.getDatas();
            System.out.println("service pasing json size : " + list.size());
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
