package raisetech.student.management.Controller.converter;

import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StudentsConverter {

  public List<StudentsDetail> convertStudentsDetails(List<Student> studentList,
      List<StudentsCourses> studentsCoursesList) {
    List<StudentsDetail> studentsDetails = new ArrayList<>();
    studentList.forEach(student -> {
      StudentsDetail studentsDetail = new StudentsDetail();
      studentsDetail.setStudent(student);

      List<StudentsCourses> convertStudentsCourses = studentsCoursesList.stream()
          .filter(studentCourses -> student.getId().equals(studentCourses.getStudentID()))
          .collect(Collectors.toList());
      studentsDetail.setStudentsCoursesList(convertStudentsCourses);
      studentsDetails.add(studentsDetail);
    });
    return studentsDetails;
  }

}
