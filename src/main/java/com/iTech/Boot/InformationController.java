package com.iTech.Boot;

import com.iTech.Boot.model.Course;
import com.iTech.Boot.model.Staff;
import com.iTech.Boot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
public class InformationController {

    @Autowired
    InformationService informationService;

    @RequestMapping(value = "/biodata/{st}/all")
    public ResponseEntity fetchDetails(@PathVariable("st") String st){

        ResponseEntity responseEntity = null;

        if(st.equals("staff")) {
            informationService.staffDetails();
                responseEntity = new ResponseEntity( informationService.staffList, HttpStatus.OK);
            }
        else if(st.equals("student")){
            informationService.studentDetails();
            responseEntity = new ResponseEntity( informationService.studentList, HttpStatus.OK);
        }
        else{
            Map<String, Object> map = new HashMap<>();
            map.put("Status",404);
            map.put("error", "Not found");
            map.put("message","Invalid path");
            map.put("Path","/biodata/"+st+"/all");
            responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }
    return responseEntity;
    }

    @RequestMapping(value ="/biodata/{st}/{no}")
    public ResponseEntity fetchDetail(@PathVariable("st") String st,@PathVariable("no") Integer n){

        ResponseEntity responseEntity = null;
        boolean flag = false;
        if(st.equals("staff")) {
            informationService.staffDetails();
           for(Staff i:  informationService.staffList)
               if(i.getId() == n)
               {
                   responseEntity = new ResponseEntity(i,HttpStatus.OK);
                   flag = true;
                   break;
               }
        }
        else if(st.equals("student")){
            informationService.studentDetails();
            for(Student i:  informationService.studentList)
                if(i.getId() == n)
                {
                    responseEntity = new ResponseEntity(i,HttpStatus.OK);
                    flag = true;
                    break;
                }
        }
        else{
            Map<String, Object> map = new HashMap<>();
            map.put("Status",404);
            map.put("error", "Not found");
            map.put("message","Invalid path");
            map.put("Path","/biodata/"+st+"/all");
            responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
            flag = true;
        }
       if(!flag) {
           Map<String, Object> map = new HashMap<>();
           map.put("Status",404);
           map.put("error", "Not found");
           map.put("message","Invalid Id");
           responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
       }
        return responseEntity;
    }

    @RequestMapping(value ="/biodata/addstaff", method = RequestMethod.POST)
    public ResponseEntity addStaffDetail(@RequestBody Staff obj){

        ResponseEntity responseEntity = null;
        informationService.staffDetails();
        Staff staff = (Staff) obj;
        boolean flag =  informationService.addStaff(staff);
        Map<String, Object> map = new HashMap<>();
        if(!flag){
            map.put("Status",400);
            map.put("error", "Bad request");
            map.put("message","already exist");
            responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        else
        {
            map.put("Status",200);
            map.put("message","Added successfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value ="/biodata/addstudent", method = RequestMethod.POST)
    public ResponseEntity addStudentDetail(@RequestBody Student obj){
        ResponseEntity responseEntity =null;
        informationService.studentDetails();
        boolean flag =  informationService.addStudent(obj);
        Map<String, Object> map = new HashMap<>();
        if(!flag){
            map.put("Status",400);
            map.put("error", "Bad request");
            map.put("message","already exist");
            responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        else
        {
            map.put("Status",200);
            map.put("message","Added successfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value ="/biodata/updatestudent", method = RequestMethod.PUT)
    public ResponseEntity updateStudentDetail(@RequestBody Student obj){
        ResponseEntity responseEntity = null;
        informationService.studentDetails();
        boolean flag =  informationService.updateStudent(obj);
        Map<String, Object> map = new HashMap<>();
        if(!flag){
            map.put("Status",404);
            map.put("error", "Bad request");
            map.put("message","Not found");
            responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }
        else
        {
            map.put("Status",200);
            map.put("message","Updated successfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value ="/biodata/updatestaff", method = RequestMethod.PUT)
    public ResponseEntity updateStaffDetail(@RequestBody Staff obj){
        ResponseEntity responseEntity = null;
        informationService.studentDetails();
        boolean flag =  informationService.updateStaff(obj);
        Map<String, Object> map = new HashMap<>();
        if(!flag){
            map.put("Status",404);
            map.put("error", "Bad request");
            map.put("message","Not found");
            responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }
        else
        {
            map.put("Status",200);
            map.put("message","Updated successfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        return responseEntity;
    }


}
