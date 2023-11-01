package esi.classmate.timetable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import esi.classmate.timetable.dto.TimetableDto;
import esi.classmate.timetable.model.Timetable;
import esi.classmate.timetable.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    private final KafkaTemplate<String, TimetableDto> kafkaTemplate;

    public List<TimetableDto> getTimetables() {
        List<Timetable> timetable = new ArrayList<>();
        timetableRepository.findAll().forEach(timetable::add);
        return timetable.stream().map(this::mapToTimetableDto).toList();
    }

    private TimetableDto mapToTimetableDto(Timetable timetable) {
        return TimetableDto.builder()
                .id(timetable.getId())
                .day(timetable.getDay())
                .start_time(timetable.getStart_time())
                .end_time(timetable.getEnd_time())
                .groupCat(timetable.getGroupCat())
                .build();
    }

    public Optional<TimetableDto> getTimetableById(Long id) {
        Optional<Timetable> timetable = timetableRepository.findById(id);
        return timetable.map(this::mapToTimetableDto);
    }

    public void addTimetable(TimetableDto timetableDto) {
        Timetable timetable = Timetable.builder()
                .id(timetableDto.getId())
                .courseId(timetableDto.getCourseId())
                .day(timetableDto.getDay())
                .start_time(timetableDto.getStart_time())
                .end_time(timetableDto.getEnd_time())
                .groupCat(timetableDto.getGroupCat())
                .build();

        // Save the timetable to in its current state in the Database
        timetableRepository.save(timetable);

        // Push the timetableDto to the timetableCreatedTopic topic
        kafkaTemplate.send("timetableCreatedTopic", timetableDto);

        log.info("Timetable added Successfully", timetable.getId());
    }

    public void updateTimetable(Long id, TimetableDto timetableDto) {
        Timetable timetable = Timetable.builder()
                .id(timetableDto.getId())
                .day(timetableDto.getDay())
                .start_time(timetableDto.getStart_time())
                .end_time(timetableDto.getEnd_time())
                .groupCat(timetableDto.getGroupCat())
                .build();
        timetableRepository.save(timetable);
        log.info("Timetable {} is updated", timetable.getId());
    }

    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
        log.info("Timetable {} is deleted", id);
    }

}
