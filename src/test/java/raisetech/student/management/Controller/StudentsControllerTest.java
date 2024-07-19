package raisetech.student.management.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import raisetech.student.management.data.Student;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.service.StudentsService;

@WebMvcTest(StudentsController.class)
class StudentsControllerTest {

  @Autowired
  private MockMvc mockmvc;
  @MockBean
  private StudentsService service;
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索ができて空のリストが帰ってくること()throws  Exception{

    mockmvc.perform(get("/student"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力したときに入力チェックに異常が発生しないこと(){
    Student student = new Student();
    student.setId("1");
    student.setName("上路啓太");
    student.setHurigana("アゲジケイタ");
    student.setNickname("ケータロス");
    student.setMailaddress("test@example.com");
    student.setArea("青森県");
    student.setSex("男性");
    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertThat(violations.size()).isEqualTo(0);

  }
  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックにかかること(){
    Student student = new Student();
    student.setId("テストです");
    student.setName("上路啓太");
    student.setHurigana("アゲジケイタ");
    student.setNickname("ケータロス");
    student.setMailaddress("test@example.com");
    student.setArea("青森県");
    student.setSex("男性");
    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message").containsOnly("数字のみを入力してください。");
  }

}