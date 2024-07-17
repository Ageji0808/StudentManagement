package raisetech.student.management.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDetail {

  public Student student;
  public List<StudentsCourse> studentsCourseList;



}