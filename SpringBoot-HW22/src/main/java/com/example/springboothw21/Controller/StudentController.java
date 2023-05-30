package com.example.springboothw21.Controller;


import com.example.springboothw21.Model.Course;
import com.example.springboothw21.Model.Student;
import com.example.springboothw21.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getAllStudent());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(200).body("student added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@Valid @RequestBody Student student, @PathVariable Integer id){
        studentService.updateStudent(id,student);
        return ResponseEntity.status(200).body("student updated");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body("student deleted");
    }

    @PutMapping("/{course_id}/assign/{student_id}")
    public ResponseEntity assignStudentToCourse(@PathVariable Integer course_id, @PathVariable Integer student_id){
        studentService.assignStudentToCourse(course_id,student_id);
        return ResponseEntity.status(200).body("assigned done");
    }

    @PutMapping("/change-major/{student_id}/{major}")
    public ResponseEntity changeMajor(@PathVariable  Integer student_id , @PathVariable String major){
        studentService.changeMajor(student_id,major);
        return ResponseEntity.status(200).body("major changed");
    }
    @GetMapping("/get-student/{course_id}")
    public ResponseEntity studentList(@PathVariable Integer course_id){
        return ResponseEntity.status(200).body(studentService.studentList(course_id));
    }
}
