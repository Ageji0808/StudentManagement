package Raisetech.Student.ManagementSystem.Controller.converter;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.domain.StudentsDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StudentsConverter {
  public List<StudentsDetail> convertStudentsDetails(List<Students> students,
      List<StudentsCourses> studentsCourses) {
    List<StudentsDetail> studentsDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentsDetail studentsDetail = new StudentsDetail();
      studentsDetail.setStudents(student);

      List<StudentsCourses> convertStudentsCourses = studentsCourses.stream()
          .filter(studentCourses -> student.getId().equals(studentCourses.getStudentID()))
          .collect(Collectors.toList());
      studentsDetail.setStudentsCourses(convertStudentsCourses);
      studentsDetails.add(studentsDetail);
    });
    return studentsDetails;
  }

}
