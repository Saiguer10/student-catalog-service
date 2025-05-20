package com.example.demo.client;

import com.example.demo.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-info-service")
public interface StudentClient {

    @GetMapping("/api/students/{id}")
    Student getStudentById(@PathVariable("id") Long id);


    default Student getStudentByStringId(String id) {
        return getStudentById(Long.parseLong(id));
    }
}
