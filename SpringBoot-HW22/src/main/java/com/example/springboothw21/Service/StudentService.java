package com.example.springboothw21.Service;


import com.example.springboothw21.ApiException.ApiException;
import com.example.springboothw21.Model.Course;
import com.example.springboothw21.Model.Student;
import com.example.springboothw21.Repository.CourseRepository;
import com.example.springboothw21.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;



    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student){
        Student student1 = studentRepository.findStudentById(id);
        if (student1==null){
            throw new ApiException("wrong id");
        }
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setMajor(student.getMajor());

        studentRepository.save(student1);
    }

    public void deleteStudent(Integer id){
        Student student1 = studentRepository.findStudentById(id);
        if (student1==null){
            throw new ApiException("wrong id");
        }

        studentRepository.delete(student1);
    }


    public void assignStudentToCourse(Integer student_id, Integer course_id){

        Student student = studentRepository.findStudentById(student_id);
        Course course=courseRepository.findCourseById(course_id);
        if (student==null || course==null){
            throw new ApiException("wrong id");
        }
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);

    }

    public void changeMajor(Integer student_id,String major){
        Student student1 = studentRepository.findStudentById(student_id);
        if (student1==null){
            throw new ApiException("wrong id");
        }
        student1.setMajor(major);
        student1.getCourses().clear();
        studentRepository.save(student1);
    }

    public Set<Student> studentList(Integer course_id){
        Course course=courseRepository.findCourseById(course_id);
        if (course==null){
            throw new ApiException("wrong id");
        }
        return course.getStudents();
    }
}
