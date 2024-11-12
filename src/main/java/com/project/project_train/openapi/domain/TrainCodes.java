package com.project.project_train.openapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.project_train.openapi.dao.TrainCodeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Arrays;


@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainCodes {

    private List<TrainCodeDTO> datas;
    
    @JsonCreator
    public TrainCodes(@JsonProperty("data") JsonNode node) {
        try{

            ObjectMapper mapper = new ObjectMapper();

            this.datas = Arrays.stream(mapper.treeToValue(node,TrainCodeDTO[].class)).toList();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
