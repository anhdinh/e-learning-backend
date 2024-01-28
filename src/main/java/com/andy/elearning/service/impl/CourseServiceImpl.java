package com.andy.elearning.service.impl;

import com.andy.elearning.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public String data() {
        return "Hello";
    }
}
