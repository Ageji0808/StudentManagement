package raisetech.student.management.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import raisetech.student.management.controller.converter.StudentsConverter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.exception.TestException;
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

  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します")
  @GetMapping("/student")
  public List<StudentsDetail> getStudentsList() {

    return studentsService.searchStudentList();
  }

  @Operation(summary = "受講生検索", description = "受講生を検索します")
  @GetMapping("/students/{id}")
  public StudentsDetail getStudent(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") @Size(min = 1, max = 3) String id) {

    return studentsService.findStudentById(id);
  }

  @Operation(summary = "受講生登録", description = "受講生を登録します")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentsDetail> registerStudent(
      @RequestBody @Valid StudentsDetail studentsDetail
  ) {
    StudentsDetail responseStudentsDetail = studentsService.registerStudent(studentsDetail);

    return ResponseEntity.ok(responseStudentsDetail);
  }

  @Operation(summary = "受講生更新", description = "受講生を更新します")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentsDetail studentsDetail) {
    studentsService.updateStudent(studentsDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  @Operation(summary = "例外処理テスト", description = "例外処理をテストします")

  @GetMapping("/testException")
  public void testException() throws TestException {
    throw new TestException("これはテスト例外です。");
  }
}

