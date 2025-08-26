package com.krishnaallu009.springbootQiuckStarter.repository;

import com.krishnaallu009.springbootQiuckStarter.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {

}
