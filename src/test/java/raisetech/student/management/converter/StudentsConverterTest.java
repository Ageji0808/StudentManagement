package raisetech.student.management.converter;

import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.student.management.controller.converter.StudentsConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;

class StudentsConverterTest {
  private StudentsConverter sut;

  @BeforeEach
  void before(){
    sut = new StudentsConverter();
  }

  @Test
  void 受講生のリストと受講生コースのリストを渡して受講生詳細のリストを作成できること(){
    Student student = new Student();
    student.setId("1");
    student.setName("上路啓太");
    student.setHurigana("アゲジケイタ");
    student.setHurigana("アゲジケイタ");
    student.setNickname("ケータロス");
    student.setMailaddress("test@example.com");
    student.setArea("青森県");
    student.setSex("男性");
    student.setRemark("");
    student.setDeleted(false);

    StudentsCourses studentsCourses = new StudentsCourses();
    studentsCourses.setCourseID("1");
    studentsCourses.setStudentID("1");
    studentsCourses.setCourseName("Java");
    studentsCourses.setStartDate(LocalDate.now());
    studentsCourses.setEndDate(LocalDate.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentsCourses> studentsCoursesList = List.of(studentsCourses);
    List<StudentsDetail> actual = sut.convertStudentsDetails(studentList, studentsCoursesList);
    Assertions.assertThat(actual.get(0).getStudent()).isEqualTo(student);
    Assertions.assertThat(actual.get(0).getStudentsCoursesList()).isEqualTo(studentsCoursesList);

  }


}
