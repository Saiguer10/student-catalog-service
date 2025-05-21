package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeDto {
    private Long gradeId;
    private Double grade;
    private Long studentId;
}
