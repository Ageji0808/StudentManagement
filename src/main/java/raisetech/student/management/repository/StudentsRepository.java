package raisetech.student.management.repository;


import jakarta.validation.Valid;
import raisetech.student.management.data.Student;

import java.util.List;import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;


@Mapper

public interface StudentsRepository {


  List<Student> getAllStudents();


  Student findStudentById(String id);
  Student  findStudentByName(String Name);
  List<Student>  findStudentByMailaddress(String mailaddress);
  List<Student>  findStudentByArea(String area);
  List<Student> findStudentByAge(int age);
  List<Student> findStudentBySex(String Sex);


  void registerStudent(@Valid Student student);

  ;

  void updateStudent(Student student);


  List<StudentsCourses> getAllStudentsCourses();


  List<StudentsCourses> findStudentsCourseById(String studentID);

  void registerStudentsCourses(StudentsCourses studentsCourses);

  StudentsCourses findCourseById(String courseId);


  void updateStudentsCourses(StudentsCourses studentsCourses);


}










