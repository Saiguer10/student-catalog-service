package com.example.demo.client;

import com.example.demo.Dtos.CourseDto;
import com.example.demo.Dtos.CourseGradeDto;
import com.example.demo.Dtos.GradeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("grades-data-service")
public interface GradesDataServiceClient {

    @GetMapping("/api/course/{courseCode}")
    CourseDto getCourseByCode(@PathVariable Long courseCode);

    @GetMapping("/api/course/{courseCode}/grade")
    CourseGradeDto getGradesByCourseCode(@PathVariable Long courseCode);

    @GetMapping("/api/grade/{id}")
    GradeDto getGradeById(@PathVariable Long id);
}