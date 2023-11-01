package esi.classmate.timetable.controller;

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

import esi.classmate.timetable.dto.TimetableDto;
import esi.classmate.timetable.service.TimetableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/timetables")
    public List<TimetableDto> getTimetables() {
        return timetableService.getTimetables();
    }

    @GetMapping("/timetables/{id}")
    public Optional<TimetableDto> getTimetableById(@PathVariable Long id) {
        return timetableService.getTimetableById(id);
    }

    @PostMapping(value = "/timetables")
    public ResponseEntity<String> addTimetable(@RequestBody TimetableDto timetableDto) {
        timetableService.addTimetable(timetableDto);
        return ResponseEntity.ok("Added Successfully");

    }

    @PutMapping("/timetables/{id}")
    public void updateTimetable(@RequestBody TimetableDto timetableDto, @PathVariable Long id) {
        timetableService.updateTimetable(id, timetableDto);
    }

    @DeleteMapping("/timetables/{id}")
    public void deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
    }

}
