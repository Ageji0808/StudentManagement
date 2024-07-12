package raisetech.student.management.Controller.converter;

import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourse;
import raisetech.student.management.domain.StudentsDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StudentsConverter {

  public List<StudentsDetail> convertStudentsDetails(List<Student> studentList,
      List<StudentsCourse> studentsCourses) {
    List<StudentsDetail> studentsDetails = new ArrayList<>();
    studentList.forEach(student -> {
      StudentsDetail studentsDetail = new StudentsDetail();
      studentsDetail.setStudent(student);

      List<StudentsCourse> convertStudentsCourses = studentsCourses.stream()
          .filter(studentCourses -> student.getId().equals(studentCourses.getStudentID()))
          .collect(Collectors.toList());
      studentsDetail.setStudentsCourseList(convertStudentsCourses);
      studentsDetails.add(studentsDetail);
    });
    return studentsDetails;
  }

}
