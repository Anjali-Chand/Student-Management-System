
package com.student.studentmanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.student.studentmanagement.entity.Student;
import com.student.studentmanagement.exception.StudentNotFoundException;
import com.student.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        //return studentRepository.findById(id).orElse(null);
    	
    	 return studentRepository.findById(id)
    	            .orElseThrow(() ->
    	                new StudentNotFoundException(
    	                    "Student not found with ID: " + id
    	                )
    	            );
    }

    // Update student
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                    new StudentNotFoundException(
                        "Student not found with ID: " + id
                    )
                );

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setAge(student.getAge());

        return studentRepository.save(existingStudent);
    }

    // Delete student
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                    new StudentNotFoundException(
                        "Student not found with ID: " + id
                    )
                );

        studentRepository.delete(student);
    }
    
   // Search students by name
    public List<Student> searchStudents(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }
}
