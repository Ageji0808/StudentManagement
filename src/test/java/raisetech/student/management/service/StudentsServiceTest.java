package raisetech.student.management.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import raisetech.student.management.controller.converter.StudentsConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.repository.StudentsRepository;

@ExtendWith(MockitoExtension.class)
class StudentsServiceTest {

  @Mock
  private StudentsRepository repository;

  @Mock
  private StudentsConverter converter;

  private StudentsService sut;

  @BeforeEach
  void before() {
    sut = new StudentsService(repository, converter);
  }

  @Test
  void 受講生詳細一覧の検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {

    List<Student> studentList = new ArrayList<>();
    List<StudentsCourses> studentsCoursesList = new ArrayList<>();
    when(repository.getAllStudents()).thenReturn(studentList);
    when(repository.getAllStudentsCourses()).thenReturn(studentsCoursesList);

    sut.searchStudentList();

    verify(repository, times(1)).getAllStudents();
    verify(repository, times(1)).getAllStudentsCourses();
    verify(converter, times(1)).convertStudentsDetails(studentList, studentsCoursesList);
  }

  @Test
  void 受講生のIDによる検索_リポジトリの処理が適切に呼び出せていること() {
    String id = "1";
    Student student = new Student();
    student.setId(id);

    when(repository.findStudentById(id)).thenReturn(student);
    when(repository.findStudentsCourseById(id)).thenReturn(new ArrayList<>());
    StudentsDetail expected = new StudentsDetail(student, new ArrayList<>());
    StudentsDetail actual = sut.findStudentById(id);

    verify(repository, times(1)).findStudentById(id);
    verify(repository, times(1)).findStudentsCourseById(id);
    Assertions.assertEquals(expected.getStudent().getId(), actual.getStudent().getId());
  }

  @Test
  void 受講生の名前による検索_リポジトリの処理が適切に呼び出せていること() {
    String name = "永田浩紀";
    Student student = new Student();
    student.setName(name);

    when(repository.findStudentByName(name)).thenReturn(student);
    when(repository.findStudentsCourseById(student.getId())).thenReturn(new ArrayList<>());
    StudentsDetail expected = new StudentsDetail(student, new ArrayList<>());
    StudentsDetail actual = sut.findStudentByName(name);

    verify(repository, times(1)).findStudentByName(name);
    verify(repository, times(1)).findStudentsCourseById(student.getId());
    Assertions.assertEquals(expected.getStudent().getName(), actual.getStudent().getName());
  }

  @Test
  void 受講生のEメールによる検索_リポジトリの処理が適切に呼び出せていること() {
    String mailaddress = "hiroki8833yyy@yahoo.co.jp";
    Student student1 = new Student();
    student1.setMailaddress(mailaddress);

    List<Student> students = Arrays.asList(student1);

    when(repository.findStudentByMailaddress(mailaddress)).thenReturn(students);
    when(repository.findStudentsCourseById(student1.getId())).thenReturn(new ArrayList<>());

    List<StudentsDetail> expectedDetails = students.stream()
        .map(student -> new StudentsDetail(student, new ArrayList<>()))
        .collect(Collectors.toList());

    List<StudentsDetail> actualDetails = sut.findStudentByMailaddress(mailaddress);

    verify(repository, times(1)).findStudentByMailaddress(mailaddress);
    verify(repository, times(1)).findStudentsCourseById(student1.getId());

    for (int i = 0; i < expectedDetails.size(); i++) {
      Assertions.assertEquals(expectedDetails.get(i).getStudent().getMailaddress(),
          actualDetails.get(i).getStudent().getMailaddress());
    }
  }
  @Test
  void 受講生の地域による検索_リポジトリの処理が適切に呼び出せていること() {
    String area = "青森県";
    Student student1 = new Student();
    student1.setArea(area);
    student1.setId("1");

    Student student2 = new Student();
    student2.setArea(area);
    student2.setId("6");

    List<Student> students = Arrays.asList(student1, student2);

    when(repository.findStudentByArea(area)).thenReturn(students);
    when(repository.findStudentsCourseById(student1.getId())).thenReturn(new ArrayList<>());

    List<StudentsDetail> expectedDetails = students.stream()
        .map(student -> new StudentsDetail(student, new ArrayList<>()))
        .collect(Collectors.toList());

    List<StudentsDetail> actualDetails = sut.findStudentByArea(area);

    verify(repository, times(1)).findStudentByArea(area);
    verify(repository, times(2)).findStudentsCourseById(anyString());

    for (int i = 0; i < expectedDetails.size(); i++) {
      Assertions.assertEquals(expectedDetails.get(i).getStudent().getMailaddress(),
          actualDetails.get(i).getStudent().getMailaddress());
    }
  }
  @Test
  void 受講生の年齢による検索_リポジトリの処理が適切に呼び出せていること() {
    int age = 32;
    Student student1 = new Student();
    student1.setAge(age);

    List<Student> students = Arrays.asList(student1);

    when(repository.findStudentByAge(age)).thenReturn(students);
    when(repository.findStudentsCourseById(student1.getId())).thenReturn(new ArrayList<>());

    List<StudentsDetail> expectedDetails = students.stream()
        .map(student -> new StudentsDetail(student, new ArrayList<>()))
        .collect(Collectors.toList());

    List<StudentsDetail> actualDetails = sut.findStudentByAge(age);

    verify(repository, times(1)).findStudentByAge(age);
    verify(repository, times(1)).findStudentsCourseById(student1.getId());

    for (int i = 0; i < expectedDetails.size(); i++) {
      Assertions.assertEquals(expectedDetails.get(i).getStudent().getAge(),
          actualDetails.get(i).getStudent().getAge());
    }
  }
  @Test
  void 受講生の性別による検索_リポジトリの処理が適切に呼び出せていること() {
    String sex = "回答なし";
    Student student1 = new Student();
    student1.setSex(sex);

    List<Student> students = Arrays.asList(student1);

    when(repository.findStudentBySex(sex)).thenReturn(students);
    when(repository.findStudentsCourseById(student1.getId())).thenReturn(new ArrayList<>());

    List<StudentsDetail> expectedDetails = students.stream()
        .map(student -> new StudentsDetail(student, new ArrayList<>()))
        .collect(Collectors.toList());

    List<StudentsDetail> actualDetails = sut.findStudentBySex(sex);

    verify(repository, times(1)).findStudentBySex(sex);
    verify(repository, times(1)).findStudentsCourseById(student1.getId());

    for (int i = 0; i < expectedDetails.size(); i++) {
      Assertions.assertEquals(expectedDetails.get(i).getStudent().getSex(),
          actualDetails.get(i).getStudent().getSex());
    }
  }



  @Test
  void 受講生詳細の登録_リポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();
    StudentsCourses studentsCourses = new StudentsCourses();
    List<StudentsCourses> studentsCoursesList = List.of(studentsCourses);
    StudentsDetail studentsDetail = new StudentsDetail(student, studentsCoursesList);

    sut.registerStudent(studentsDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentsCourses(studentsCourses);

  }

  @Test
  void 受講生詳細の登録_初期化処理が行われること() {
    String id = "1";
    Student student = new Student();
    student.setId(id);
    StudentsCourses studentsCourses = new StudentsCourses();

    sut.initStudentsCourse(studentsCourses, student.getId());
    Assertions.assertEquals("1", studentsCourses.getStudentID());
    Assertions.assertEquals(LocalDate.now().plusDays(10), studentsCourses.getStartDate());
    Assertions.assertEquals(studentsCourses.getStartDate().plusYears(1),
        studentsCourses.getEndDate());
  }

  @Test
  void 受講生詳細の更新_リポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();
    StudentsCourses studentsCourses = new StudentsCourses();
    List<StudentsCourses> studentsCoursesList = List.of(studentsCourses);
    StudentsDetail studentsDetail = new StudentsDetail(student, studentsCoursesList);
    sut.updateStudent(studentsDetail);
    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentsCourses(studentsCourses);
  }

  @Test
  void 仮申し込みから本申し込みへの更新_リポジトリの処理が適切に呼び出せていること() {
    String courseId = "1";
    StudentsCourses studentsCourses = new StudentsCourses();
    studentsCourses.setCourseID(courseId);
    studentsCourses.setStatus("仮申し込み");

    when(repository.findCourseById(courseId)).thenReturn(studentsCourses);

    sut.updateToFullApplication(courseId);

    verify(repository, times(1)).findCourseById(courseId);
    verify(repository, times(1)).updateStudentsCourses(studentsCourses);
    Assertions.assertEquals("本申し込み", studentsCourses.getStatus());
    Assertions.assertTrue(studentsCourses.isFullApplicationFlag());
  }


}