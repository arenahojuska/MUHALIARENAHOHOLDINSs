package net.javaguide.springboot_rest_api.controller;

import net.javaguide.springboot_rest_api.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
/// http://localhost:8080/student
    @GetMapping("/student")
    public Student getStudent() {
        return new Student("Arenaho", 1, "Muhali");
    }

    /// http://localhost:8080/students
    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
                students.add(new Student("juska", 2, "Muhali"));
        students.add(new Student("juska", 3, "Muhali"));
        students.add(new Student("Are", 4 , "UHON"));
        return students;
    }



 ///  spring boot with path Variable
// {id} - URL TEMPLATE Variable , we use path variable to bind
// /// http://localhost:8080/student/1

 @GetMapping("/students/{id}")
 public Student studentPathVariable(@PathVariable("id") int studentId) {
     return new Student("ARENAHO", studentId, "Muhali");
 }
}