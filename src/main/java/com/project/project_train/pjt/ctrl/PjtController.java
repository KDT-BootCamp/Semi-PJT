package com.project.project_train.pjt.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.project_train.pjt.dao.CustomerRequest;
import com.project.project_train.pjt.dao.CustomerResponse;
import com.project.project_train.pjt.service.PjtSerivce;

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/project")
public class PjtController {
    @Autowired
    private PjtSerivce pjtSerivce;

    // 로그인 세션 확인
    @GetMapping(path = "/me")
    public ResponseEntity<CustomerResponse> me(HttpSession session){
        System.out.println("client path >>> /project/me");
        System.out.println("debug >>> session : " + session);

        CustomerResponse user = (CustomerResponse) session.getAttribute("user");
        
        if(ObjectUtils.isEmpty(user)){
            throw new RuntimeException("User is not authenticated");
        } else{

            System.out.println("session uuid >> " + session.getId());
            return new ResponseEntity<>(user,HttpStatus.OK);

        }
            
    }

    @PostMapping("/login")
    public ResponseEntity<Object> trainLogin(HttpSession session , @RequestBody CustomerRequest requestDTO) {
        System.out.println("client path >>> /project/login");
        System.out.println("debug >>> requestDTO : " + requestDTO);

        CustomerResponse response = pjtSerivce.login(requestDTO);
        
        if(response != null){
            session.setAttribute("user", response);
            System.out.println("debug >>> login success !! ");
            return new ResponseEntity<>(response,HttpStatus.OK);

        } else {
            System.out.println("debug >>> login fail !!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> trainLogout(HttpSession session) {
        
        //session.invalidate();  // 세션 무효화
        session.removeAttribute("user");

        return new ResponseEntity<>(HttpStatus.OK);
    }
    

    @GetMapping("/namecheck")
    public ResponseEntity<Void> trainNameCheck(@RequestParam(name = "name") String name) {
        System.out.println("client path >>> /project/namecheck");
        System.out.println("debug >>> name : " + name);        

        CustomerResponse result = pjtSerivce.nameCheck(name);

        if(result == null){
            System.out.println("debug >>> Name Possible !! ");
            return new ResponseEntity<>(HttpStatus.OK);            

        } else {
            System.out.println("debug >>> Name Unpossible !! ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);            
        }
    }
    
    @GetMapping("/pwdcheck")
    public ResponseEntity<Void> trainPwdCheck(@RequestParam(name = "pwd") String pwd) {
        System.out.println("client path >>> /project/namecheck");
        System.out.println("debug >>> password : " + pwd);        

        CustomerResponse result = pjtSerivce.pwdCheck(pwd);

        if(result == null){
            System.out.println("debug >>> PassWord Possible !! ");
            return new ResponseEntity<>(HttpStatus.OK);            

        } else {
            System.out.println("debug >>> PassWord Unpossible !! ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);            
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Void> trainJoin(@RequestBody CustomerRequest requestDTO) {
        
        System.out.println("client path >>> /project/join");
        System.out.println("debug >>> join : " + requestDTO);        
        
        pjtSerivce.join(requestDTO);

        System.out.println("join success !!");        
        
        return null;
    }
    
}
