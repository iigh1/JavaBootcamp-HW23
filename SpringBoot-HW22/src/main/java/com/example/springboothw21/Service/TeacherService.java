package com.example.springboothw21.Service;


import com.example.springboothw21.ApiException.ApiException;
import com.example.springboothw21.Model.Teacher;
import com.example.springboothw21.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher){

        Teacher oldTeacher = teacherRepository.findTeacherById(id);

        if (oldTeacher==null){
            throw new ApiException("not found");
        }

        oldTeacher.setName(teacher.getName());
        oldTeacher.setSalary(teacher.getSalary());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setAddress(teacher.getAddress());

        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer id){
        Teacher oldTeacher= teacherRepository.findTeacherById(id);
        teacherRepository.delete(oldTeacher);
    }

    public Teacher getTeacherDetails(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher==null){
            throw  new ApiException("teacher not found");
        }
        return teacherRepository.findTeacherById(id);
    }
}
