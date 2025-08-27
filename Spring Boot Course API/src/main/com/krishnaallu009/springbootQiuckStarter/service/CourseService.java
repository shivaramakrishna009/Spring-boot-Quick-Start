package com.krishnaallu009.springbootQiuckStarter.service;

import com.krishnaallu009.springbootQiuckStarter.entity.Course;
import com.krishnaallu009.springbootQiuckStarter.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(String topicId){
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Course getCourse(String id) {
        return courseRepository.findById(id).orElse(new Course());
    }

    public void addCourse(Course topic) {
        courseRepository.save(topic);
    }

    public void updateCourse(String id, Course topic) {
        courseRepository.save(topic);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
