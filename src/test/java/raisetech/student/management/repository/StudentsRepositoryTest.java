package raisetech.student.management.repository;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

@MybatisTest
class StudentsRepositoryTest {

  @Autowired
  private StudentsRepository sut;


  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.getAllStudents();
    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生の個人検索が行えること() {

    String id = "2";

    Student student = sut.findStudentById(id);
    assertThat(student).isNotNull();
    System.out.println(String.join(", ",
        "Name: " + student.getName(),
        "Hurigana: " + student.getHurigana(),
        "Nickname: " + student.getNickname(),
        "Mailaddress: " + student.getMailaddress(),
        "Area: " + student.getArea(),
        "Age: " + student.getAge(),
        "Sex: " + student.getSex(),
        "Remark: " + student.getRemark(),
        "Deleted: " + student.isDeleted()
    ));
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("柏徹");
    student.setHurigana("かしわとおる");
    student.setNickname("トール");
    student.setMailaddress("test@example.co.jp");
    student.setArea("柏市");
    student.setAge(38);
    student.setSex("男性");
    student.setRemark("");
    student.setDeleted(false);
    sut.registerStudent(student);
    List<Student> actual = sut.getAllStudents();
    assertThat(actual.size()).isEqualTo(7);
  }

  @Test
  void 受講生更新が行えること() {
    String id = "1";

    Student updateStudent = sut.findStudentById(id);

    updateStudent.setName("上路啓太");
    updateStudent.setHurigana("アゲジケイタ");
    updateStudent.setNickname("ケータ");
    updateStudent.setMailaddress("new@example.co.jp");
    updateStudent.setArea("青森県");
    updateStudent.setAge(24);
    updateStudent.setSex("男性");
    updateStudent.setRemark("");
    updateStudent.setDeleted(false);
    sut.updateStudent(updateStudent);

    Student updatedStudent = sut.findStudentById(id);
    System.out.println(String.join(", ",
        "Name: " + updatedStudent.getName(),
        "Hurigana: " + updatedStudent.getHurigana(),
        "Nickname: " + updatedStudent.getNickname(),
        "Mailaddress: " + updatedStudent.getMailaddress(),
        "Area: " + updatedStudent.getArea(),
        "Age: " + updatedStudent.getAge(),
        "Sex: " + updatedStudent.getSex(),
        "Remark: " + updatedStudent.getRemark(),
        "Deleted: " + updatedStudent.isDeleted()
    ));
  }

  @Test
  void 受講生コースの全件検索が行えること() {
    List<StudentsCourses> actual = sut.getAllStudentsCourses();
    assertThat(actual.size()).isEqualTo(10);
  }

  @Test
  void 受講生コースの個人検索が行えること() {

    String studentId = "1";

    List<StudentsCourses> studentsCourses = sut.findStudentsCourseById(studentId);
    assertThat(studentsCourses).isNotNull();
    studentsCourses.forEach(course -> System.out.println(String.join(", ",
        "Course Name: " + course.getCourseName(),
        "Start Date: " + course.getStartDate(),
        "End Date: " + course.getEndDate()
    )));
  }
  @Test
  void 受講生のコース登録が行えること() {

    StudentsCourses newCourse = new StudentsCourses();
    newCourse.setStudentID("2"); // ここで登録する受講生のIDを指定
    newCourse.setCourseName("jazz");
    newCourse.setStartDate(LocalDate.of(2024, 7, 28));
    newCourse.setEndDate(LocalDate.of(2025, 7, 28));
    sut.registerStudentsCourses(newCourse);
    List<StudentsCourses> actual = sut.getAllStudentsCourses();
    assertThat(actual.size()).isEqualTo(11);

  }
  @Test
  void 受講生コース更新が行えること() {
    String courseId = "1";
    String studentId = "1";
    // 受講生情報を更新する
    List<StudentsCourses> updateStudentsCourse = sut.findStudentsCourseById(studentId);
    StudentsCourses courses = updateStudentsCourse.stream()
        .filter(course -> course.getCourseID().equals(courseId))
        .findFirst()
        .orElse(null);
    courses.setCourseName("English");
    sut.updateStudentsCourses(courses);
    System.out.println(String.join(", ",
        "Course ID: " + courses.getCourseID(),
        "Course Name: " + courses.getCourseName(),
        "Start Date: " + courses.getStartDate(),
        "End Date: " + courses.getEndDate()
    ));

  }
}