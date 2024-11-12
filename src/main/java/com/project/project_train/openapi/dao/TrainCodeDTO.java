package com.project.project_train.openapi.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class TrainCodeDTO {
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    private String value;

}
