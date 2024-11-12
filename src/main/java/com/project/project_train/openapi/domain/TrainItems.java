package com.project.project_train.openapi.domain;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.project_train.openapi.dao.TrainItemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainItems {
    
    private List<TrainItemDTO> datas;
    
    @JsonCreator
    public TrainItems(@JsonProperty("data") JsonNode node) {
        try{

            ObjectMapper mapper = new ObjectMapper();

            this.datas = Arrays.stream(mapper.treeToValue(node,TrainItemDTO[].class)).toList();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
