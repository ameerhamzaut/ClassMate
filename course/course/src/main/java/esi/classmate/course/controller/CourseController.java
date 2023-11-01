package esi.classmate.course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esi.classmate.course.dto.CourseDto;
import esi.classmate.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<CourseDto> getCourse() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public Optional<CourseDto> getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<String> addcourse(@RequestBody CourseDto courseDto) {
        log.info("courses: {}", courseDto);
        courseService.addcourse(courseDto);
        return ResponseEntity.ok("Added Course Successfully");

    }

    @PutMapping("/courses/{id}")
    public void updateCourse(@RequestBody CourseDto courseDto, @PathVariable Long id) {
        courseService.updateCourse(id, courseDto);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

}
