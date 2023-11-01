package esi.classmate.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import esi.classmate.course.repository.CourseRepository;
import esi.classmate.course.dto.CourseDto;
import esi.classmate.course.model.Course;
import esi.classmate.course.model.CourseStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    private final KafkaTemplate<String, CourseDto> kafkaTemplate;

    public List<CourseDto> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses.stream().map(this::mapToCourseDto).toList();
    }

    private CourseDto mapToCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .coursename(course.getCoursename())
                .courseinfo(course.getCourseinfo())
                .courselanguage(course.getCourselanguage())
                .coursefees(course.getCoursefees())
                .status(course.getStatus())
                .build();
    }

    public Optional<CourseDto> getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(this::mapToCourseDto);
    }

    public void addcourse(CourseDto courseDto) {
        log.info("addcourse: {}", courseDto);
        Course course = Course.builder()
                .id(courseDto.getId())
                .userId(courseDto.getUserId())
                .coursename(courseDto.getCoursename())
                .courseinfo(courseDto.getCourseinfo())
                .courselanguage(courseDto.getCourselanguage())
                .coursefees(courseDto.getCoursefees())
                .build();

        // Setting the course status to ACTIVE
        course.setStatus(CourseStatus.ACTIVE);

        // Save the order to in its current state in the Database
        courseRepository.save(course);

        // Setting the OrderDto status to ACTIVE
        courseDto.setStatus(CourseStatus.ACTIVE);

        // Push the courseDto to the courseCreatedTopic topic
        kafkaTemplate.send("orderCreatedTopic", courseDto);

    }

    public void updateCourse(Long id, CourseDto courseDto) {
        Course course = Course.builder()
                .id(courseDto.getId())
                .coursename(courseDto.getCoursename())
                .courseinfo(courseDto.getCourseinfo())
                .courselanguage(courseDto.getCourselanguage())
                .coursefees(courseDto.getCoursefees())
                .build();
        courseRepository.save(course);
        log.info("Course {} is updated", course.getId());
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        log.info("Course {} is deleted", id);
    }

}
