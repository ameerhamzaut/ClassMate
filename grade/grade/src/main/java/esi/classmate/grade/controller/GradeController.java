package esi.classmate.grade.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esi.classmate.grade.dto.GradeDto;
import esi.classmate.grade.service.GradeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/grades")
    public List<GradeDto> getGrade() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/grades/{id}")
    public Optional<GradeDto> getGrade(@PathVariable Long id) {
        return gradeService.getGrade(id);
    }

    @PostMapping(value = "/grades")
    public ResponseEntity<String> addgrade(@RequestBody GradeDto gradeDto) {
        gradeService.addgrade(gradeDto);
        return ResponseEntity.ok("Added Grades Successfully");

    }

    @PutMapping("/grades/{id}")
    public void updateCourse(@RequestBody GradeDto gradeDto, @PathVariable Long id) {
        gradeService.updateGrade(id, gradeDto);
    }

    @DeleteMapping("/grades/{id}")
    public void deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);

    }
}
