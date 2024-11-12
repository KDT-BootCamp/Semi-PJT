package com.project.project_train.pjt.domain;

import org.apache.ibatis.annotations.Mapper;

import com.project.project_train.pjt.dao.CustomerRequest;
import com.project.project_train.pjt.dao.CustomerResponse;

@Mapper
public interface PjtMapper {
    
    public CustomerResponse loginRow(CustomerRequest requestDTO);

    public CustomerResponse nameCheckRow(String name);

    public CustomerResponse pwdCheckRow(String name);

    public void joinRow(CustomerRequest requestDTO);
}

