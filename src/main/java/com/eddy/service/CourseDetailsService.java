package com.eddy.service;

import com.eddy.courses.CourseDetails;
import com.eddy.domain.Course;
import com.eddy.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailsService {


    @Autowired
    CourseRepository courseRepo;

    public List<Course> getCourses(){
        return courseRepo.findAll();
    }

    public Course getCourse(int id){
        return courseRepo.findById(id).get();
    }

    public void addCourse(Course course){
        courseRepo.save(course);
    }

    public CourseDetails mapCourse(Course course){
        CourseDetails courseDetails = new CourseDetails();
         BeanUtils.copyProperties(course,courseDetails);
         return courseDetails;
    }
}
