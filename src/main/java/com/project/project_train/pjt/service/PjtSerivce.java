package com.project.project_train.pjt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.project_train.pjt.dao.CustomerRequest;
import com.project.project_train.pjt.dao.CustomerResponse;
import com.project.project_train.pjt.domain.PjtMapper;

@Service
public class PjtSerivce {
    @Autowired
    private PjtMapper pjtMapper ;

    public CustomerResponse login(CustomerRequest requestDTO){
        System.out.println("debug >>> login start");

        return pjtMapper.loginRow(requestDTO);
    }

    public CustomerResponse nameCheck(String name){
        System.out.println("debug >>> nameCheck start");

        return pjtMapper.nameCheckRow(name);
    }

    public CustomerResponse pwdCheck(String pwd){
        System.out.println("debug >>> pwdCheck start");

        return pjtMapper.pwdCheckRow(pwd);
    }

    public void join(CustomerRequest requestDTO){
        System.out.println("debug >>> join start");

        pjtMapper.joinRow(requestDTO);
    }
}
