package com.gestiune.app.repository;

import com.gestiune.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "SELECT * FROM students", nativeQuery = true)
    List<Student> findAllStudents();
    @Query(value = "SELECT * FROM students WHERE id = ?1", nativeQuery = true)
    Optional<Student> findStudentById(Long studentId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM students WHERE id = ?1", nativeQuery = true)
    void deleteStudentsById(Long studentId);

    @Query(value = "SELECT AVG(grade) FROM students", nativeQuery = true)
    BigDecimal findAverageGrade();
    @Query(value = """
            SELECT profile, COUNT(*)
            FROM students
            GROUP BY profile
            ORDER BY COUNT(*) DESC
            LIMIT 1
            """, nativeQuery = true)
    String findMostPopularProfile();
}
