package com.example.springboothw21.Controller;


import com.example.springboothw21.DTO.CourseDTO;
import com.example.springboothw21.Model.Course;
import com.example.springboothw21.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;



    @GetMapping("/get")
    public ResponseEntity getCourse(){
        return ResponseEntity.status(200).body(courseService.getAllCourse());
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(200).body("course added");
    }
    @PutMapping("update/{id}")
    public ResponseEntity updateCourse(@Valid @RequestBody Course course, @PathVariable Integer id){
        courseService.updateCourse(id,course);
        return ResponseEntity.status(200).body("course updated");

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body("course deleted");
    }

    @PutMapping("/assign/{teacher_id}/{course_id}")
    public ResponseEntity assignCourseToTeacher(@PathVariable Integer teacher_id, @PathVariable Integer course_id){
        courseService.assignCourseToTeacher(teacher_id,course_id);
        return ResponseEntity.status(200).body("course assigned done!!");
    }

    @GetMapping("/get-name/{id}")
    public ResponseEntity getTeacherName(@PathVariable Integer id){
        return ResponseEntity.status(200).body(courseService.teacherName(id));
    }
}
