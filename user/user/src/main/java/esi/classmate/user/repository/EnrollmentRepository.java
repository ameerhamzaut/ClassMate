package esi.classmate.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esi.classmate.user.dto.UserCourseDto;
import esi.classmate.user.model.UserCourse;

@Repository
public interface EnrollmentRepository extends JpaRepository<UserCourse, Long> {
    List<UserCourseDto> findByUserId(Long userId);

}
