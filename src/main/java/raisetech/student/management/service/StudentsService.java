package raisetech.student.management.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import raisetech.student.management.controller.converter.StudentsConverter;
import raisetech.student.management.repository.StudentsRepository;
import java.time.LocalDate;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentsService {

  private StudentsRepository studentsRepository;

  private StudentsConverter studentsConverter;

  @Autowired
  public StudentsService(StudentsRepository studentsRepository,
      StudentsConverter studentsConverter) {
    this.studentsRepository = studentsRepository;
    this.studentsConverter = studentsConverter;

  }

  @Transactional
  public StudentsDetail registerStudent(StudentsDetail studentsDetail) {
    Student student = studentsDetail.getStudent();
    studentsRepository.registerStudent(student);
    studentsDetail.getStudentsCoursesList().forEach(studentsCourses -> {
      initStudentsCourse(studentsCourses, student.getId());
      studentsRepository.registerStudentsCourses(studentsCourses);
    });
    return studentsDetail;
  }

  void initStudentsCourse(StudentsCourses studentsCourses, String id) {

    LocalDate now = LocalDate.now();
    studentsCourses.setStudentID(id);
    studentsCourses.setStartDate(now.plusDays(10));
    studentsCourses.setEndDate(studentsCourses.getStartDate().plusYears(1));
    studentsCourses.setFullApplicationFlag(false);
    studentsCourses.updateStatus();
  }

  public List<StudentsDetail> searchStudentList() {
    List<Student> studentList = studentsRepository.getAllStudents();
    List<StudentsCourses> studentsCoursesList = studentsRepository.getAllStudentsCourses();
    studentsCoursesList.forEach(StudentsCourses::updateStatus);
    return studentsConverter.convertStudentsDetails(studentList, studentsCoursesList);
  }

  public StudentsDetail findStudentById(String id) {
    Student student = studentsRepository.findStudentById(id);
    List<StudentsCourses> studentsCoursesList = studentsRepository.findStudentsCourseById(
        student.getId());
    studentsCoursesList.forEach(StudentsCourses::updateStatus);

    return new StudentsDetail(student, studentsCoursesList);
  }

  public StudentsDetail findStudentByName(String name) {
    Student student = studentsRepository.findStudentByName(name);
    List<StudentsCourses> studentsCoursesList = studentsRepository.findStudentsCourseById(
        student.getId());
    studentsCoursesList.forEach(StudentsCourses::updateStatus);

    return new StudentsDetail(student, studentsCoursesList);
  }
  public List<StudentsDetail> findStudentByMailaddress(String mailaddress) {
    List<Student> studentsList = studentsRepository.findStudentByMailaddress(mailaddress);
    return studentsList.stream().map(student -> {

      List<StudentsCourses> studentsCoursesList = studentsRepository.findStudentsCourseById(student.getId());
      studentsCoursesList.forEach(StudentsCourses::updateStatus);

      return new StudentsDetail(student, studentsCoursesList);
    }).collect(Collectors.toList());
  }

  public List<StudentsDetail> findStudentByArea(String area) {
    List<Student> studentsList = studentsRepository.findStudentByArea(area);
    return studentsList.stream().map(student -> {

      List<StudentsCourses> studentsCoursesList = studentsRepository.findStudentsCourseById(student.getId());
      studentsCoursesList.forEach(StudentsCourses::updateStatus);

      return new StudentsDetail(student, studentsCoursesList);
    }).collect(Collectors.toList());
  }

  public List<StudentsDetail> findStudentByAge(int age) {
    List<Student> studentsList = studentsRepository.findStudentByAge(age);
    return studentsList.stream().map(student -> {

      List<StudentsCourses> studentsCoursesList = studentsRepository.findStudentsCourseById(student.getId());
      studentsCoursesList.forEach(StudentsCourses::updateStatus);

      return new StudentsDetail(student, studentsCoursesList);
    }).collect(Collectors.toList());
  }

  public List<StudentsDetail> findStudentBySex(String sex) {
    List<Student> studentsList = studentsRepository.findStudentBySex(sex);
    return studentsList.stream().map(student -> {

      List<StudentsCourses> studentsCoursesList = studentsRepository.findStudentsCourseById(student.getId());
      studentsCoursesList.forEach(StudentsCourses::updateStatus);

      return new StudentsDetail(student, studentsCoursesList);
    }).collect(Collectors.toList());
  }




  @Transactional
  public void updateStudent(StudentsDetail studentsDetail) {
    studentsRepository.updateStudent(studentsDetail.getStudent());
    studentsDetail.getStudentsCoursesList().forEach(studentsCourses -> {
          studentsCourses.updateStatus();
          studentsRepository.updateStudentsCourses(studentsCourses);
        }
    );

  }

  @Transactional
  public void updateToFullApplication(String courseId) {
    StudentsCourses studentsCourses = studentsRepository.findCourseById(courseId);
    if (studentsCourses != null && "仮申し込み".equals(studentsCourses.getStatus())) {
      studentsCourses.setStatus("本申し込み");
      studentsCourses.setFullApplicationFlag(true);
      studentsRepository.updateStudentsCourses(studentsCourses);
    }
  }

}
