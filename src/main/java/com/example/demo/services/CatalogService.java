package com.example.demo.services;

import com.example.demo.Dtos.CourseDto;
import com.example.demo.Dtos.CourseGradeDto;
import com.example.demo.Dtos.GradeDto;
import com.example.demo.Dtos.StudentDto;
import com.example.demo.client.GradesDataServiceClient;
import com.example.demo.client.StudentInfoServiceClient;
import com.example.demo.models.Catalog;
import com.example.demo.models.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {


    @Autowired
    private StudentInfoServiceClient studentInfoServiceClient;

    @Autowired
    private GradesDataServiceClient gradesDataServiceClient;

    public Catalog getCatalogByCourseCode(Long courseCode) {
//        CourseDTO course = restTemplate.getForObject(
//                "http://grades-data-service/api/course/" + courseCode,
//                CourseDTO.class
//        );

        CourseDto course = gradesDataServiceClient.getCourseByCode(courseCode);

        if (course == null) {
            return null;
        }
//        CourseGradeDTO courseGrade = restTemplate.getForObject(
//                "http://grades-data-service/api/course/" + courseCode + "/grade",
//                CourseGradeDTO.class
//        );

        CourseGradeDto courseGrade = gradesDataServiceClient.getGradesByCourseCode(courseCode);

        List<StudentGrade> studentGrades = new ArrayList<>();

        if (courseGrade != null && courseGrade.getGrades() != null) {
            for (GradeDto grade : courseGrade.getGrades()) {
//                StudentDTO student = restTemplate.getForObject(
//                        "http://student-info-service/api/student/" + grade.getStudentId(),
//                        StudentDTO.class
//                );

                StudentDto student = studentInfoServiceClient.getStudentId(grade.getStudentId());


                if (student != null) {
                    studentGrades.add(new StudentGrade(
                            student.getStudentName(),
                            student.getStudentAge(),
                            grade.getGrade()
                    ));
                }
            }
        }

        return new Catalog(course.getCourseName(), studentGrades);
    }
}
