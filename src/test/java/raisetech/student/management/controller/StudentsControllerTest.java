package raisetech.student.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import raisetech.student.management.data.Student;
import raisetech.student.management.service.StudentsService;

@WebMvcTest(StudentsController.class)
class StudentsControllerTest {

  @Autowired
  private MockMvc mockmvc;
  @MockBean
  private StudentsService service;
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索ができて空のリストが帰ってくること() throws Exception {

    mockmvc.perform(get("/student"))
        .andExpect(status().isOk());
    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力したときに入力チェックに異常が発生しないこと() {
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
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックにかかること() {
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

  @Test
  void 受講生検索ができて空で帰ってくること() throws Exception {
    String id = "1";
    mockmvc.perform(get("/students/{id}", id))
        .andExpect(status().isOk());
    verify(service, times(1)).findStudentById(id);
  }

  @Test
  void 受講生詳細の登録ができて空で帰ってくること() throws Exception {
    mockmvc.perform(post("/registerStudent")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                {"student":{
                  "name":"上路啓太",
                  "hurigana":"アゲジケイタ",
                  "nickname":"ケータロス",
                  "mailaddress":"test@example.com",
                  "area":"青森県",
                  "age":24,
                  "sex":"男性",
                  "remark":""
                },
                "studentsCoursesList": [
                  {"courseName":"Java"}
                ]}
                """
            ))

        .andExpect(status().isOk());



  verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新ができて空で帰ってくること() throws Exception {
    mockmvc.perform(put("/updateStudent")
            .contentType(MediaType.APPLICATION_JSON).content(
                """
                                
                            {"student":{
                      "name":"上路啓太",
                          "hurigana":"アゲジケイタ",
                          "nickname":"ケータロス",
                          "mailaddress":"test@example.com",
                          "area":"青森県",
                          "age":24,
                          "sex":"男性",
                          "remark":""
                    },
                    "studentsCoursesList": [
                    {

                      "courseName":"Java"

                    }
                        ]
                                }"""
            ))
        .andExpect(status().isOk());
    verify(service, times(1)).updateStudent(any());
  }

  @Test
  void 受講生詳細の例外APIが実行できてステータスが400でかえってくること() throws Exception {
    mockmvc.perform(get("/testException"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("これはテスト例外です。"));
  }


}