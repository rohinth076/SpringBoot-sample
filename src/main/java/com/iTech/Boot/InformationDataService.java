package com.iTech.Boot;

import com.iTech.Boot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class InformationDataService {

    @Autowired
    InformationDataRepository repository;

    List<Student> display()
    {
       return repository.displayall();
    }

    int deleteStudent(int id)
    {
      return repository.deleteStudent(id);
    }

    int addStudent(Student student)
    {
        return repository.addStudent(student);
    }
}
