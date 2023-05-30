package com.example.springboothw21.Service;


import com.example.springboothw21.ApiException.ApiException;
import com.example.springboothw21.DTO.AddressDTO;
import com.example.springboothw21.DTO.CourseDTO;
import com.example.springboothw21.Model.Address;
import com.example.springboothw21.Model.Course;
import com.example.springboothw21.Model.Teacher;
import com.example.springboothw21.Repository.CourseRepository;
import com.example.springboothw21.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course){
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse==null){
            throw  new ApiException("course not found with this id");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }
    public void deleteCourse(Integer id){
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse==null){
            throw  new ApiException("course not found with this id");
        }
        courseRepository.delete(oldCourse);

    }

    public void assignCourseToTeacher(Integer teacher_id, Integer course_id){
        Teacher teacher =teacherRepository.findTeacherById(teacher_id);
        Course course=courseRepository.findCourseById(course_id);
        if (teacher==null || course==null){
            throw new ApiException("id wrong , can't assigned course");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public String teacherName(Integer id){
        Course course = courseRepository.findCourseById(id);
        if (course==null){
            throw  new ApiException("course not found with this id");
        }
        return courseRepository.findCourseById(id).getTeacher().getName();
    }
}
