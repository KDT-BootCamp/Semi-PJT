package com.project.project_train.openapi.ctrl;

import org.springframework.web.bind.annotation.RestController;

import com.project.project_train.openapi.dao.TrainCodeDTO;
import com.project.project_train.openapi.dao.TrainCodeRequest;
import com.project.project_train.openapi.dao.TrainItemDTO;
import com.project.project_train.openapi.dao.TrainTimeRequestDTO;
import com.project.project_train.openapi.service.TrainCodeService;
import com.project.project_train.openapi.service.TrainItemService;
import com.project.project_train.pjt.dao.CustomerResponse;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/project")
public class TrainCtrlController {
    
    @Autowired
    private TrainItemService trainItemService;

    @Autowired
    private TrainCodeService trainCodeService;


    @Value("${openapi.serviceKey}")
    private String serviceKey ;

    @Value("${openapi.callBackUrl}")
    private String callBackUrl ;

    @Value("${openapi.dataType}")
    private String dataType ;

    @PostMapping("/trainApi")
    public ResponseEntity<Object> callTrainApi( HttpSession session,@Valid @RequestBody TrainTimeRequestDTO requestDTO, BindingResult bindingResult) {

        System.out.println("debug >>> login check ");

        CustomerResponse user = (CustomerResponse) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("debug >>> login check success !!");
        System.out.println("client path >>> /project/trainApi");    
        System.out.println("service key >>> " + serviceKey);
        System.out.println("callBackurl >>> " + callBackUrl);
        System.out.println("dataType >>> " + dataType);
        System.out.println("debug >>> requestDTO : " + requestDTO);
        System.out.println("params >>> 운행기간 : " + requestDTO.getStartDate() + " ~ " + requestDTO.getLastDate());
        
        String encodedDpStation = null;
        String encodedArrStation = null;

            try {
                encodedDpStation = URLEncoder.encode(requestDTO.getDpStation(), StandardCharsets.UTF_8.toString());
                encodedArrStation = URLEncoder.encode(requestDTO.getArrStation(), StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        String requestUrl = callBackUrl+"travelerTrainRunPlan2"+"?serviceKey="+serviceKey+"&dataType="+dataType+"&cond[run_ymd::GTE]="+requestDTO.getStartDate()+
                            "&cond[run_ymd::LTE]="+requestDTO.getLastDate()+"&cond[dptre_stn_nm::EQ]="+encodedDpStation+
                            "&cond[arvl_stn_nm::EQ]="+encodedArrStation;

        System.out.println("url >>> " + requestUrl);

        if(bindingResult.hasErrors()){

            System.out.println("debug >>> valiate error");
        
            List<ObjectError> list = bindingResult.getAllErrors();

            System.out.println("Erros : " + list);

            Map<String,String> map = new HashMap<>();
        
            for(int i = 0; i < list.size(); i++){
                FieldError field = (FieldError)list.get(i);
                String msg = list.get(i).getDefaultMessage();
                System.out.println("debug >>> " + field.getField() + "\t" + msg);
                map.put(field.getField(),msg);
            }
            
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        } else {

            HttpURLConnection http = null;
            InputStream stream = null;
            String result = null;
            List<TrainItemDTO> list = null;

            try{

                URL url  = new URL(requestUrl);
                http = (HttpURLConnection)url.openConnection();
                System.out.println("http connection >>> " + http);
                int code = http.getResponseCode();
                System.out.println("http response code >>> " + code);

                if(code == 200){
                    stream = http.getInputStream(); // 여기서 json 값을 바이너리로 받아옴
                    result = readString(stream);
                    System.out.println("result : " + result);
                    list = trainItemService.parshingJson(result);
                
                    System.out.println("debug api response >>> " + list);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }

    @PostMapping("/trainCode")
    public ResponseEntity<Object> getTrainCode( HttpSession session ,@Valid @RequestBody TrainCodeRequest trainCodeRequest, BindingResult bindingResult) {
    
        System.out.println("debug >>> login check ");

        CustomerResponse user = (CustomerResponse) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("debug >>> login check success !!");

        System.out.println("client path >>> /project/getTrainCode");    
        System.out.println("service key >>> " + serviceKey);
        System.out.println("callBackurl >>> " + callBackUrl);
        System.out.println("dataType >>> " + dataType);
        System.out.println("params >>> 역 이름 : " + trainCodeRequest.getStation());
    
        String encodedValue = null;
        try {
            encodedValue = URLEncoder.encode(trainCodeRequest.getStation(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }

        String requestUrl = callBackUrl+"codes2"+"?serviceKey="+serviceKey+"&dataType="+dataType+"&cond[type::EQ]=stn_cd"+
                            "&cond[value::LIKE]="+encodedValue;

        System.out.println("url >>> " + requestUrl);
    
        if(bindingResult.hasErrors()){
            System.out.println("debug >>> valiate error");
        
            List<ObjectError> list = bindingResult.getAllErrors();
    
            System.out.println("Erros : " + list);
    
            Map<String,String> map = new HashMap<>();
        
            for(int i = 0; i < list.size(); i++){
                FieldError field = (FieldError)list.get(i);
                String msg = list.get(i).getDefaultMessage();
                System.out.println("debug >>> " + field.getField() + "\t" + msg);
                map.put(field.getField(),msg);
            }
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
            } else {
    
            HttpURLConnection http = null;
            InputStream stream = null;
            String result = null;
            List<TrainCodeDTO> list = new ArrayList<>();
            try{
    
                URL url  = new URL(requestUrl);
                http = (HttpURLConnection)url.openConnection();
                System.out.println("http connection >>> " + http);
                int code = http.getResponseCode();
                System.out.println("http response code >>> " + code);

                if(code == 200){
                    stream = http.getInputStream(); // 여기서 json 값을 바이너리로 받아옴
                    result = readString(stream);
                    System.out.println("result : " + result);
                    trainCodeService.parshingJson(result).forEach(name -> {
                        if (name.getValue().equals(trainCodeRequest.getStation())) {
                            list.add(name);
                        }
                    });                    
                    System.out.println("debug api response >>> " + list);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
    
            return new ResponseEntity<>(list,HttpStatus.OK);
        }    
    } 

    public String readString(InputStream stream) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
        String input = null;
        StringBuilder result = new StringBuilder(); 
        while((input = br.readLine()) != null){
            result.append(input+"\n\r");
        }
        br.close();

        return result.toString();
    }

}
