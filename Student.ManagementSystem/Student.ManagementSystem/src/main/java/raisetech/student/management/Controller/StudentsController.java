package raisetech.student.management.Controller;

import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.Controller.converter.StudentsConverter;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.service.StudentsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Validated
@RestController
public class StudentsController {

  private StudentsService studentsService;
  private StudentsConverter converter;

  @Autowired
  public StudentsController(StudentsService studentsService) {
    this.studentsService = studentsService;

  }

  @GetMapping("/student")
  public List<StudentsDetail> getStudentsList() {

    return studentsService.searchStudentList();
  }
  @GetMapping("/students/{id}")
  public StudentsDetail getStudent(@PathVariable @Size(min = 1,max = 3) String id) {

    return studentsService.findStudentById(id);
  }


  @PostMapping("/registerStudent")
  public ResponseEntity<StudentsDetail> registerStudent(@RequestBody StudentsDetail studentsDetail
  ) {
    StudentsDetail responseStudentsDetail = studentsService.registerStudent(studentsDetail);

    return ResponseEntity.ok(responseStudentsDetail);
  }


  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentsDetail studentsDetail) {
    studentsService.updateStudent(studentsDetail.getStudent());
    return ResponseEntity.ok("更新処理が成功しました。");
  }
}

