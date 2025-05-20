package com.example.demo.client;

import com.example.demo.models.Course;
import com.example.demo.models.CourseGrade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "grades")
public interface GradesClient {

    @GetMapping("/api/courses/{courseCode}")
    Course getCourseByCode(@PathVariable("courseCode") String courseCode);

    @GetMapping("/api/courses/{courseCode}/grades")
    List<CourseGrade> getGradesByCourseCode(@PathVariable("courseCode") String courseCode);

}