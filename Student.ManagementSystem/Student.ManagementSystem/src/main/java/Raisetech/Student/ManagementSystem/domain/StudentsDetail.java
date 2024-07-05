package Raisetech.Student.ManagementSystem.domain;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsDetail {

  private Students students;
  private List<StudentsCourses> studentsCourses;



}
