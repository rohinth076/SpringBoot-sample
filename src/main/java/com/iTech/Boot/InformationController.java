package com.iTech.Boot;

import com.iTech.Boot.model.Course;
import com.iTech.Boot.model.Staff;
import com.iTech.Boot.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InformationController {

    List<Student> studentList = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();

    @RequestMapping(value = "/biodata/{st}/all")
    public ResponseEntity fetchDetails(@PathVariable("st") String st){

        ResponseEntity responseEntity = null;

        if(st.equals("staff")) {
            staffDetails();
                responseEntity = new ResponseEntity(staffList, HttpStatus.OK);
            }
        else if(st.equals("student")){
            studentDetails();
            responseEntity = new ResponseEntity(studentList, HttpStatus.OK);
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
            staffDetails();
           for(Staff i: staffList)
               if(i.getId() == n)
               {
                   responseEntity = new ResponseEntity(i,HttpStatus.OK);
                   flag = true;
                   break;
               }
        }
        else if(st.equals("student")){
            studentDetails();
            for(Student i: studentList)
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
        staffDetails();
        Staff staff = (Staff) obj;
        boolean flag = addStaff(staff);
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
        ResponseEntity responseEntity = null;
       studentDetails();
        boolean flag = addStudent(obj);
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
        studentDetails();
        boolean flag = updateStudent(obj);
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
        studentDetails();
        boolean flag = updateStaff(obj);
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

    boolean updateStaff(Staff staff){
        boolean flag = false;
        for(Staff i:staffList)
            if(i.getId() == staff.getId())
            {
                staffList.remove(i);
                flag =true;
                staffList.add(staff);
                break;
            }
        return flag;
    }
    boolean updateStudent(Student student) {
        boolean flag = false;
        for(Student i:studentList)
            if(i.getId() == student.getId())
            {
                studentList.remove(i);
                flag =true;
                studentList.add(student);
                break;
            }
            return flag;
    }

    boolean addStaff(Staff staff){
        if(staffList.stream().filter(i->i.getId() == staff.getId()).count() != 0)return false;
        staffList.add(staff);
        return true;
    }

    boolean addStudent(Student student){
        if(studentList.stream().filter(i->i.getId() == student.getId()).count() != 0)return false;
        studentList.add(student);
        return true;
    }

    private List<Student> studentDetails(){

        if(studentList.isEmpty()){
            Student s1 = new Student();

            s1.setId(1);
            s1.setName("Rohinth");
            s1.setYear(2);

            Course c1 = new Course();

            c1.setBatch(2023);
            c1.setDegree("B-Tech");
            c1.setDept("IT");

            s1.setCourse(c1);

            Student s2 = new Student();

            s2.setId(2);
            s2.setName("Dharshan");
            s2.setYear(2);

            Course c2 = new Course();

            c2.setBatch(2023);
            c2.setDegree("BE");
            c2.setDept("CSE");

            s2.setCourse(c2);
            studentList.add(s1);
            studentList.add(s2);
        }
        return studentList;
    }

    private List<Staff> staffDetails(){

        if(staffList.isEmpty()) {

            Staff s1 = new Staff();

            s1.setName("Sachin");
            s1.setDept("IT");
            s1.setId(1);
            s1.setExperience(5);

            List<String> l1 = new ArrayList<>();
            l1.add("IOT");l1.add("C");
            s1.setSubjects(l1);

            Staff s2 = new Staff();

            s2.setName("Dhoni");
            s2.setId(2);
            s2.setDept("cse");
            s2.setExperience(3);

            List<String> l2 = new ArrayList<>();
            l2.add("Java");l2.add("python");
            s2.setSubjects(l2);

            staffList.add(s1);
            staffList.add(s2);
        }

        return staffList;
    }
}
