package Raisetech.Student.ManagementSystem.Controller;

import Raisetech.Student.ManagementSystem.Controller.converter.StudentsConverter;
import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.domain.StudentsDetail;
import Raisetech.Student.ManagementSystem.service.StudentsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {

  private StudentsService studentsService;
  private StudentsConverter converter;

  @Autowired
  public StudentsController(StudentsService studentsService, StudentsConverter converter) {
    this.studentsService = studentsService;
    this.converter = converter;
  }

  @GetMapping("/students")
  public List<StudentsDetail> getStudentsList() {
    List<Students> students = studentsService.searchStudentsList();
    List<StudentsCourses> studentsCourses = studentsService.searchStudentsCoursesList();

    return converter.convertStudentsDetails(students, studentsCourses);
  }


  @GetMapping("/studentsCourses")
  public List<StudentsCourses> getStudentsCoursesList() {
    return studentsService.searchStudentsCoursesList();
  }
}


