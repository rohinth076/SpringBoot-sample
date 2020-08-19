package com.iTech.Boot;

import com.iTech.Boot.model.Course;
import com.iTech.Boot.model.Staff;
import com.iTech.Boot.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformationService {

    List<Student> studentList = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();

   public boolean updateStaff(Staff staff){
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
   public boolean updateStudent(Student student) {
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

   public boolean addStaff(Staff staff){
        if(staffList.stream().filter(i->i.getId() == staff.getId()).count() != 0)return false;
        staffList.add(staff);
        return true;
    }

   public boolean addStudent(Student student){
       /* for(int i=0;i<studentList.size();i++)
            if(studentList.get(i).getId() == student.getId())return false;*/

        /* for(Student i: studentList)
             if(i.getId() == student.getId())return false; */
        if(studentList.stream().filter(i->i.getId() == student.getId()).count() != 0)return false;

        studentList.add(student);
        return true;
    }

    public List<Student> studentDetails(){

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

    public List<Staff> staffDetails(){

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
