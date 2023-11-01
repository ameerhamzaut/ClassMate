package esi.classmate.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esi.classmate.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
