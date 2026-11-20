
package com.student.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.studentmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByNameContainingIgnoreCase(String name);

}
