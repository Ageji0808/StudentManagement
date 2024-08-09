package raisetech.student.management.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PutMapping;
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


  @Autowired
  public StudentsController(StudentsService studentsService) {
    this.studentsService = studentsService;

  }

  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します")
  @GetMapping("/students")
  public List<StudentsDetail> getStudentsList() {

    return studentsService.searchStudentList();
  }

  @Operation(summary = "IDによる受講生検索", description = "IDを用いて受講生を検索します")
  @GetMapping("/studentsById/{id}")
  public StudentsDetail getStudentByID(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") @Size(min = 1, max = 3) String id) {

    return studentsService.findStudentById(id);
  }

  @Operation(summary = "名前による受講生検索", description = "名前を用いて受講生を検索します")
  @GetMapping("/studentsByName/{name}")
  public StudentsDetail getStudentByName(
      @PathVariable @NotBlank  String name) {

    return studentsService.findStudentByName(name);
  }
  @Operation(summary = "Eメールによる受講生検索", description = "Eメールを用いて受講生を検索します")
  @GetMapping("/studentsByMailaddress/{mailaddress}")
  public List<StudentsDetail> getStudentByMailaddress(
      @PathVariable @NotBlank  String mailaddress) {

    return studentsService.findStudentByMailaddress(mailaddress);
  }
  @Operation(summary = "地域による受講生検索", description = "地域を用いて受講生を検索します")
  @GetMapping("/studentsByArea/{area}")
  public List<StudentsDetail> getStudentByArea(
      @PathVariable @NotBlank  String area) {

    return studentsService.findStudentByArea(area);
  }
  @Operation(summary = "年齢による受講生検索", description = "年齢を用いて受講生を検索します")
  @GetMapping("/studentsByAge/{age}")
  public List<StudentsDetail> getStudentByAge(
      @PathVariable @NotNull int age) {

    return studentsService.findStudentByAge(age);
  }
  @Operation(summary = "性別による受講生検索", description = "性別を用いて受講生を検索します")
  @GetMapping("/studentsBySex/{sex}")
  public List<StudentsDetail> getStudentBySex(
      @PathVariable @NotBlank  String sex) {

    return studentsService.findStudentBySex(sex);
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

  @Operation(summary = "仮申し込みを本申し込みに変更", description = "特定のコースの仮申し込みを本申し込みに変更します")
  @PutMapping("/updateStatus/{courseId}")
  public ResponseEntity<String> updateToFullApplication(@PathVariable String courseId) {
    studentsService.updateToFullApplication(courseId);
    return ResponseEntity.ok("仮申し込みを本申し込みに変更しました。");
  }

  @Operation(summary = "例外処理テスト", description = "例外処理をテストします")

  @GetMapping("/testException")
  public void testException() throws TestException {
    throw new TestException("これはテスト例外です。");
  }
}

