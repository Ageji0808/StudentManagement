package Raisetech.Student.ManagementSystem.Controller;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.service.StudentsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsController {

  private StudentsService studentsService;

  @Autowired
  public StudentsController(StudentsService studentsService) {
    this.studentsService = studentsService;
  }

  @GetMapping("/students")
  public List<Students> getStudentsList() {
    return studentsService.searchStudentsList();
  }

  @GetMapping("/studentsCourses")
  public List<StudentsCourses> getStudentsCoursesList() {
    return studentsService.searchStudentsCoursesList();
  }
}


