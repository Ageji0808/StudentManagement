package Raisetech.Student.ManagementSystem.repository;

import Raisetech.Student.ManagementSystem.data.Students;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;




  @Mapper
  public interface StudentsRepository {



  @Select("SELECT * FROM students")
  List<Students> getAllStudents();





  }



