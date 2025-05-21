package com.example.demo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private Long studentId;
    private String studentName;
    private int studentAge;
}
