package com.iTech.Boot;

import com.iTech.Boot.model.Course;
import com.iTech.Boot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InformationDataRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Student> displayall()
    {
        String q = "SELECT * FROM student";
           return jdbcTemplate.query(q, new RowMapper<Student>() {

                @Override
                public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                    Student student = new Student();

                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setYear(resultSet.getInt("year"));

                    Course course = new Course();
                    course.setDept(resultSet.getString("dept"));
                    course.setDegree(resultSet.getString("degree"));
                    course.setBatch(resultSet.getInt("batch"));
                    student.setCourse(course);

                    return student;
                }
            });
    }
    int deleteStudent(int id)
    {
        String query = "DELETE FROM student where id = ?";

        return  jdbcTemplate.update(query,id);
    }

    int addStudent(Student student)
    {
        String query  = "INSERT INTO student value(?,?,?,?,?,?)";

      return jdbcTemplate.update(query,student.getId(),student.getName(),student.getYear(),
                student.getCourse().getDegree(),student.getCourse().getDept(),student.getCourse().getBatch());
    }

}
