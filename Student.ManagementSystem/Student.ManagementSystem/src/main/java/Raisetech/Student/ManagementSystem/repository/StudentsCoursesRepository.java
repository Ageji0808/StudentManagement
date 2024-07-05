package Raisetech.Student.ManagementSystem.repository;

import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StudentsCoursesRepository {


  @Select("SELECT * FROM studentsCourses")
  List<StudentsCourses> getAllStudentsCourses();

}
