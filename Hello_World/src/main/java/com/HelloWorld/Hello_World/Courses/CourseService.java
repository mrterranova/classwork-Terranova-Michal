package com.HelloWorld.Hello_World.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() throws NullPointerException{
        //return topics;
        List<Course> topics = new ArrayList<>();
        courseRepository.findAll().forEach(topics::add);
        return topics;
    }
    public Optional<Course> getCourse(String id) {
        return courseRepository.findById(id);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(String id, Course course) {
        courseRepository.save(course);
    }
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
