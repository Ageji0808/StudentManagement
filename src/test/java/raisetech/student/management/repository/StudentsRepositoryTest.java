package raisetech.student.management.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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
  void 受講生のIDによる個人検索が行えること() {

    String id = "2";

    Student student = sut.findStudentById(id);
    assertThat(student).isNotNull();
    assertEquals("永田浩紀", student.getName());
    assertEquals("ながたひろき", student.getHurigana());
    assertEquals("ヒロ", student.getNickname());
    assertEquals("hiroki8833yyy@yahoo.co.jp", student.getMailaddress());
    assertEquals("品川区", student.getArea());
    assertEquals(32, student.getAge());
    assertEquals("男性", student.getSex());

  }
  @Test
  void 受講生の名前による個人検索が行えること() {

    String name = "永田浩紀";

    Student student = sut.findStudentByName(name);
    assertThat(student).isNotNull();
    assertEquals("永田浩紀", student.getName());
    assertEquals("ながたひろき", student.getHurigana());
    assertEquals("ヒロ", student.getNickname());
    assertEquals("hiroki8833yyy@yahoo.co.jp", student.getMailaddress());
    assertEquals("品川区", student.getArea());
    assertEquals(32, student.getAge());
    assertEquals("男性", student.getSex());

  }
  @Test
  void Eメールによる受講生検索が行えること() {

    String mailaddress = "hiroki8833yyy@yahoo.co.jp";
    List<Student> studentsList = sut.findStudentByMailaddress(mailaddress);

    assertThat(studentsList).isNotNull();
    assertEquals(1, studentsList.size());

    Student student1 = studentsList.get(0);
    assertEquals("永田浩紀", student1.getName());
    assertEquals("ながたひろき", student1.getHurigana());
    assertEquals("ヒロ", student1.getNickname());
    assertEquals("hiroki8833yyy@yahoo.co.jp", student1.getMailaddress());
    assertEquals("品川区", student1.getArea());
    assertEquals(32, student1.getAge());
    assertEquals("男性", student1.getSex());;


  }


  @Test
  void 地域による複数の受講生検索が行えること() {

    String area = "青森県";
    List<Student> studentsList = sut.findStudentByArea(area);

    assertThat(studentsList).isNotNull();
    assertEquals(2, studentsList.size());

    Student student1 = studentsList.get(0);
    assertEquals("上路啓太", student1.getName());
    assertEquals("あげじけいた", student1.getHurigana());
    assertEquals("ケータロス", student1.getNickname());
    assertEquals("test@example.com", student1.getMailaddress());
    assertEquals("青森県", student1.getArea());
    assertEquals(24, student1.getAge());
    assertEquals("男性", student1.getSex());

    Student student2 = studentsList.get(1);
    assertEquals("町田千尋", student2.getName());
    assertEquals("まちだちひろ", student2.getHurigana());
    assertEquals("ちっひー", student2.getNickname());
    assertEquals("senntochihiro52736d@fish3ksi.com", student2.getMailaddress());
    assertEquals("青森県", student2.getArea());
    assertEquals(19, student2.getAge());
    assertEquals("回答なし", student2.getSex());
  }
  @Test
  void 年齢による受講生検索が行えること() {

    int age = 32;
    List<Student> studentsList = sut.findStudentByAge(age);

    assertThat(studentsList).isNotNull();
    assertEquals(1, studentsList.size());

    Student student1 = studentsList.get(0);
    assertEquals("永田浩紀", student1.getName());
    assertEquals("ながたひろき", student1.getHurigana());
    assertEquals("ヒロ", student1.getNickname());
    assertEquals("hiroki8833yyy@yahoo.co.jp", student1.getMailaddress());
    assertEquals("品川区", student1.getArea());
    assertEquals(32, student1.getAge());
    assertEquals("男性", student1.getSex());;
  }
  @Test
  void 性別による受講生検索が行えること() {

    String sex= "回答なし";
    List<Student> studentsList = sut.findStudentBySex(sex);

    assertThat(studentsList).isNotNull();
    assertEquals(1, studentsList.size());

    Student student1 = studentsList.get(0);
    assertEquals("町田千尋", student1.getName());
    assertEquals("まちだちひろ", student1.getHurigana());
    assertEquals("ちっひー", student1.getNickname());
    assertEquals("senntochihiro52736d@fish3ksi.com", student1.getMailaddress());
    assertEquals("青森県", student1.getArea());
    assertEquals(19, student1.getAge());
    assertEquals("回答なし", student1.getSex());;


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
    assertEquals("上路啓太", updatedStudent.getName());
    assertEquals("アゲジケイタ", updatedStudent.getHurigana());
    assertEquals("ケータ", updatedStudent.getNickname());
    assertEquals("new@example.co.jp", updatedStudent.getMailaddress());
    assertEquals("青森県", updatedStudent.getArea());
    assertEquals(24, updatedStudent.getAge());
    assertEquals("男性", updatedStudent.getSex());
    assertEquals("", updatedStudent.getRemark());
    assertEquals(false, updatedStudent.isDeleted());

  }

  @Test
  void 受講生コースの全件検索が行えること() {
    List<StudentsCourses> actual = sut.getAllStudentsCourses();
    assertThat(actual.size()).isEqualTo(10);
  }

  @Test
  void 受講生コースの個人検索が行えること() {
    String courseId = "1";
    String studentId = "1";
    List<StudentsCourses> searchStudentsCourse = sut.findStudentsCourseById(studentId);
    StudentsCourses courses = searchStudentsCourse.stream()
        .filter(course -> course.getCourseID().equals(courseId))
        .findFirst()
        .orElse(null);
    assertThat(courses).isNotNull();



      assertEquals("公務員", courses.getCourseName());
      assertEquals("2024-06-11", courses.getStartDate().toString());
      assertEquals("2025-09-11", courses.getEndDate().toString());
    }

  @Test
  void 受講生のコース登録が行えること() {

    StudentsCourses newCourse = new StudentsCourses();
    newCourse.setStudentID("2"); // ここで登録する受講生のIDを指定
    newCourse.setCourseName("jazz");
    newCourse.setStartDate(LocalDate.of(2024, 7, 28));
    newCourse.setEndDate(LocalDate.of(2025, 7, 28));
    newCourse.setStatus("仮申し込み");
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
    assertEquals("English", courses.getCourseName());


  }
  @Test
  void 受講生コースの検索が行えること() {

    String courseId = "2";

    StudentsCourses studentsCourses = sut.findCourseById(courseId);
    assertThat(studentsCourses).isNotNull();
    assertEquals("Java", studentsCourses.getCourseName());
    assertEquals("2023-11-26", studentsCourses.getStartDate().toString());
    assertEquals("2024-02-26", studentsCourses.getEndDate().toString());

  }
  @Test
  void 存在しない受講生コースの検索でnullが返されること() {
    String nullCourseId = "999"; // 存在しないIDを指定

    StudentsCourses studentsCourses = sut.findCourseById(nullCourseId);

    // 存在しないIDの場合、nullが返されることを確認
    assertNull(studentsCourses);
  }

}