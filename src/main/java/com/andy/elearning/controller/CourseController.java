package com.andy.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @GetMapping("/findAllCourse")
    public ResponseEntity<?> getAllCourse() {


        
        return ResponseEntity.ok("{}");
    }

}
