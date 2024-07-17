package raisetech.student.management.repository;


import jakarta.validation.Valid;
import raisetech.student.management.data.Student;

import java.util.List;import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.StudentsCourses;


@Mapper

public interface StudentsRepository {


  List<Student> getAllStudents();


  Student findStudentById(String id);


  void registerStudent(@Valid Student student);

  ;

  void updateStudent(Student student);


  List<StudentsCourses> getAllStudentsCourses();


  List<StudentsCourses> findStudentsCourseById(String studentID);

  void registerStudentsCourses(StudentsCourses studentsCourses);


  void updateStudentsCourses(StudentsCourses studentsCourses);
}










