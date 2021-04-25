package com.example.appointmentsys.controllers;

import com.example.appointmentsys.model.Student;
import com.example.appointmentsys.repositories.StudentRepo;
import com.example.appointmentsys.utils.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//api design guidance: https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design
//api implementation guidance: https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-implementation
//db design basics: https://support.microsoft.com/en-us/office/database-design-basics-eb2159cf-1e30-401a-8084-bd4f9c9ca1f5

//@CrossOrigin(origins = "http://localhost:8080")
//@RequestMapping(path = "/app")
@RestController
public class StudentController {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/test")
    public List<Student> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<Student> list = studentRepo.findByName("maitao");
        return list;
    }

    @GetMapping("/add")
    public String addNewStudent(@RequestParam(value = "name", defaultValue = "World") String name,
                                @RequestParam(value = "city", defaultValue = "World") String city,
                                @RequestParam(value = "age", defaultValue = "0") int age) {
        Student stu = new Student(name, city, age);
        studentRepo.save(stu);
        return "Saved";
    }

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student) {
        //TODO: do checks
        return studentRepo.save(student);
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new CustomNotFoundException(id));
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentRepo.findById(id).map(student -> {
            student.setName(newStudent.getName());
            student.setAge(newStudent.getAge());
            student.setCity(newStudent.getCity());
            return studentRepo.save(student);
        }).orElseThrow(() -> new CustomNotFoundException(id));
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepo.deleteById(id);
    }
}
