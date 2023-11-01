package esi.classmate.timetable.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableDto {
    @Id
    private Long id;
    private Long courseId;
    private LocalDate day;
    private LocalTime start_time;
    private LocalTime end_time;
    private String groupCat;

    public TimetableDto(Long id) {
        this.id = id;
    }
}
