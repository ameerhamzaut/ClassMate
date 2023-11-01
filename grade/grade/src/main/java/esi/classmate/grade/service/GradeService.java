package esi.classmate.grade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import esi.classmate.grade.repository.GradeRepository;
import esi.classmate.grade.dto.EnrollNotificationDto;
import esi.classmate.grade.dto.GCourseDto;
import esi.classmate.grade.dto.GUserDto;
import esi.classmate.grade.dto.GradeDto;
import esi.classmate.grade.model.Grade;
import esi.classmate.grade.model.GradeStatus;
import esi.classmate.grade.model.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String, EnrollNotificationDto> kafkaTemplate;

    public List<GradeDto> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        gradeRepository.findAll().forEach(grades::add);
        return grades.stream().map(this::mapToGradeDto).toList();
    }

    private GradeDto mapToGradeDto(Grade grade) {
        return GradeDto.builder()
                .id(grade.getId())
                .student_grade(grade.getStudent_grade())
                .transcript(grade.getTranscript())
                .report(grade.getReport())
                .remarks(grade.getRemarks())
                .status(grade.getStatus())
                .build();
    }

    public Optional<GradeDto> getGrade(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        return grade.map(this::mapToGradeDto);
    }

    public void addgrade(GradeDto gradeDto) {
        log.info("service method");
        // Save the order to in its current state in the Database
        Grade pgrade = gradeRepository.save(mapToGradeCourse(gradeDto));
        log.info("saved entity: {}", pgrade);
        // send notif
        sendGradeNotif(pgrade);
    }

    private Grade mapToGradeCourse(GradeDto gradeDto) {
        return Grade.builder()
                .courseId(gradeDto.getCourseId())
                .userId(gradeDto.getUserId())
                .student_grade(gradeDto.getStudent_grade())
                .transcript(gradeDto.getTranscript())
                .report(gradeDto.getReport())
                .remarks(gradeDto.getRemarks())
                .build();
        // gradeDto.setStatus(GradeStatus.PASS);
        // if (gradeDto.getStudent_grade().equals('A')) {

        // } else {

        // }
    }

    public void sendGradeNotif(Grade pgrade) {

        log.info("sendEnrollmentNotif ");
        String url = "http://localhost:8081/api/courses/{id}";
        GCourseDto courseDto = webClientBuilder.build()
                .get()
                .uri(url, pgrade.getCourseId())
                .retrieve()
                .bodyToMono(GCourseDto.class)
                .block();

        String url2 = "http://localhost:8083/api/users/{id}";
        GUserDto userDto = webClientBuilder.build()
                .get()
                .uri(url2, pgrade.getUserId())
                .retrieve()
                .bodyToMono(GUserDto.class)
                .block();

        log.info("course dto: {}", courseDto);

        EnrollNotificationDto notificationDto = new EnrollNotificationDto();
        notificationDto.setType(NotificationType.PAYMENT);
        notificationDto.setCourseName(courseDto.getCoursename());
        notificationDto.setUserId(pgrade.getUserId());
        notificationDto.setEmail(userDto.getEmail());

        log.info("sending the notif");
        kafkaTemplate.send("enrollmentTopic", notificationDto);
        // kafkaTemplate.send("enrollmentTopic", notificationDto);
        log.info("sending the notif DONE");
    }

    // public void addgrade(GradeDto gradeDto) {
    // Grade grade = Grade.builder()
    // .id(gradeDto.getId())
    // .student_grade(gradeDto.getStudent_grade())
    // .transcript(gradeDto.getTranscript())
    // .report(gradeDto.getReport())
    // .remarks(gradeDto.getRemarks())
    // .build();

    // // Setting the Grades status to PASS
    // grade.setStatus(GradeStatus.PASS);

    // // Save the order to in its current state in the Database
    // gradeRepository.save(grade);

    // // Setting the OrderDto status to ACTIVE
    // gradeDto.setStatus(GradeStatus.PASS);

    // // Push the courseDto to the courseCreatedTopic topic
    // kafkaTemplate.send("orderCreatedTopic", gradeDto);

    // }

    public void updateGrade(Long id, GradeDto gradeDto) {
        Grade grade = Grade.builder()
                .id(gradeDto.getId())
                .courseId(gradeDto.getCourseId())
                .userId(gradeDto.getUserId())
                .student_grade(gradeDto.getStudent_grade())
                .transcript(gradeDto.getTranscript())
                .report(gradeDto.getReport())
                .remarks(gradeDto.getRemarks())
                .build();
        gradeRepository.save(grade);
        log.info("Grade {} are updated", grade.getId());
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
        log.info("Grade {} are deleted", id);
    }

}
