package raisetech.student.management.domain;

import raisetech.student.management.data.StudentsCourses;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import raisetech.student.management.data.Student;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Schema(description = "受講生詳細")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDetail {
  @Valid
  public Student student;
  @Valid
  public List<StudentsCourses> studentsCoursesList;



}