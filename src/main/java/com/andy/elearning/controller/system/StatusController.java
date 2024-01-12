package com.andy.elearning.controller.system;

import com.andy.elearning.dto.system.StatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/status")
public class StatusController {

    @GetMapping
    public ResponseEntity<?> getStatus(){
        StatusDto statusDto =  new StatusDto();
        statusDto.setStatus("ok");
        return ResponseEntity.ok(statusDto);
    }
}
