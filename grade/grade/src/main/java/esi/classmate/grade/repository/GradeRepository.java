package esi.classmate.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esi.classmate.grade.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
