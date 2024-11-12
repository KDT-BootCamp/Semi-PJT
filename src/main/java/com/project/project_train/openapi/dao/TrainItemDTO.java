package com.project.project_train.openapi.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainItemDTO {
    
// //"arvl_stn_cd": "3900114",
// "arvl_stn_nm": "부산",
// "dptre_stn_cd": "3900023",
// "dptre_stn_nm": "서울",
// "run_ymd": "20231201",
// "trn_no": "00001",
// "trn_plan_arvl_dt": "2023-12-01 07:49:00.0",
// "trn_plan_dptre_dt": "2023-12-01 05:12:00.0"
    @JsonProperty("arvl_stn_cd")
    private String arrStationCode;

    @JsonProperty("arvl_stn_nm")
    private String arrStationName;

    @JsonProperty("dptre_stn_cd")
    private String dpStationCode;

    @JsonProperty("dptre_stn_nm")
    private String dpStationName;

    @JsonProperty("run_ymd")
    private String run_date;

    @JsonProperty("trn_no")
    private String trn_no;

    @JsonProperty("trn_plan_arvl_dt")
    private String trn_plan_arvl_dt;

    @JsonProperty("trn_plan_dptre_dt")
    private String trn_plan_dptre_dt;


}
