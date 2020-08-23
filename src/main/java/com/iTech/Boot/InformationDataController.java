package com.iTech.Boot;

import com.iTech.Boot.model.Student;
import org.jcp.xml.dsig.internal.dom.ApacheOctetStreamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InformationDataController {

    @Autowired
    InformationDataService service;

    @RequestMapping(value ="/student/data/all")
    List<Student> displayAll()
    {
       return service.display();
    }
    @RequestMapping(value="/student/data/delete/{id}", method = RequestMethod.DELETE)
    String deleteStudent(@PathVariable("id") int id)
    {
        int change = service.deleteStudent(id);

        return "Deleted Successfully " + change + " record";
    }
    @RequestMapping(value = "/student/data/add",method = RequestMethod.POST)
    String addStudent(@RequestBody Student student)
    {
        int change = service.addStudent(student);
        return "SUCCESSFULLY inserted " + change + " record";
    }

}
