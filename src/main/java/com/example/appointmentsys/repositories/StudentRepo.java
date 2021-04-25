package com.example.appointmentsys.repositories;

import com.example.appointmentsys.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    List<Student> findAll();

//    List<Student> findByNameAndByAgeDesc(String name, int age);
}
