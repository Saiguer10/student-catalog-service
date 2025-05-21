package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseGradeDto {
    private String courseName;
    private List<GradeDto> grades;
}
