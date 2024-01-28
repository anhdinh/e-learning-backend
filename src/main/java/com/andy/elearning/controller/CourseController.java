package com.andy.elearning.controller;

import com.andy.elearning.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/findAllCourse")
    public ResponseEntity<?> getAllCourse() {
        return ResponseEntity.ok( courseService.data());
    }

}
