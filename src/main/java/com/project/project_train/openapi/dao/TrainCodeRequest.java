package com.project.project_train.openapi.dao;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrainCodeRequest {

    @NotBlank(message = "역을 입력해주세요")
    private String station;


}
