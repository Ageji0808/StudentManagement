package raisetech.student.management.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
  void 受講生の検索_リポジトリの処理が適切に呼び出せていること() {
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
  void 受講生詳細の登録_初期化処理が行われること(){
    String id = "1";
    Student student = new Student();
    student.setId(id);
    StudentsCourses studentsCourses = new StudentsCourses();

    sut.initStudentsCourse(studentsCourses, student.getId());
    Assertions.assertEquals("1", studentsCourses.getStudentID());
    Assertions.assertEquals(LocalDate.now(), studentsCourses.getStartDate());
    Assertions.assertEquals(LocalDate.now().plusYears(1), studentsCourses.getEndDate());
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

}