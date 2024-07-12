package raisetech.student.management.repository;


import raisetech.student.management.data.Student;

import java.util.List;import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.StudentsCourse;


@Mapper

public interface StudentsRepository {


  // 削除されていない受講生のみを取得するクエリに変更
  List<Student> getAllStudents();


  Student findStudentById(String id);


  void registerStudent(Student student);

  ;

  void updateStudent(Student student);


  List<StudentsCourse> getAllStudentsCourses();


  List<StudentsCourse> findStudentsCourseById(String studentID);

  void registerStudentsCourses(StudentsCourse studentsCourse);


  void updateStudentsCourses(StudentsCourse studentsCourse);
}










