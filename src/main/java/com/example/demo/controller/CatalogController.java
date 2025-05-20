package com.example.demo.controller;

import com.example.demo.client.GradesClient;
import com.example.demo.client.StudentClient;
import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private GradesClient gradesClient;

    @Autowired
    private StudentClient studentClient;

    // CatalogController.java
    @GetMapping("/api/course/{courseCode}")
    public ResponseEntity<Catalog> getStudentCatalogByCourse(@PathVariable String courseCode) {
        // curso por código
        Course course = gradesClient.getCourseByCode(courseCode);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        //  notas de los estudiantes
        List<CourseGrade> courseGrades = gradesClient.getGradesByCourseCode(courseCode);

        if (courseGrades == null || courseGrades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // cada nota, obtener la información del estudiante
        List<StudentGrade> studentGrades = courseGrades.stream()
                .map(cg -> {
                    if (cg.getStudentId() != null) {
                        Student student = studentClient.getStudentById(cg.getStudentId());
                        if (student != null) {
                            return new StudentGrade(student.getName(), student.getAge(), cg.getGrade());
                        }
                    }
                    return null;
                })
                .filter(sg -> sg != null)
                .collect(Collectors.toList());

        // Crear y devolver el catálogo
        Catalog catalog = new Catalog(course.getCourseName(), studentGrades);
        return ResponseEntity.ok(catalog);
    }
}
