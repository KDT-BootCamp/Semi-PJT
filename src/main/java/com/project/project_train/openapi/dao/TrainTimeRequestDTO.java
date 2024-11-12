package com.project.project_train.openapi.dao;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrainTimeRequestDTO {
    //cond[run_ymd::GTE]=20231201&cond[run_ymd::LTE]=20231201&cond[dptre_stn_nm::EQ]=서울&cond[arvl_stn_nm::EQ]=부산
    @NotBlank(message = "기간을 입력해주세요")
    private String startDate;

    @NotBlank(message = "기간을 입력해주세요")
    private String lastDate ;

    @NotBlank(message = "출발역을 입력해주세요")
    private String dpStation ;

    @NotBlank(message = "도착역을 입력해주세요")
    private String arrStation ;
}
