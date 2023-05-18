package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {
     @Autowired
    StudentServices studentServices;
       @PostMapping("/add")
    public String createStudent(@RequestBody Student student){

           return studentServices.createStudent(student);
       }
       @GetMapping("get_user")
    public String getNameByEmail(@RequestParam("email") String email){
           return studentServices.findNameByEmail(email);
       }
       @PutMapping("update_mob")
    public String updateMob(@RequestBody StudentUpdateMobRequestDto studentReqDto){
            return studentServices.updateMobNo(studentReqDto);
       }
}
