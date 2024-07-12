package raisetech.student.management.service;

import java.time.LocalDate;
import raisetech.student.management.Controller.converter.StudentsConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourse;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.repository.StudentsRepository;
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
    studentsRepository.registerStudent(studentsDetail.getStudent());
    studentsDetail.getStudentsCourseList().forEach(studentsCourses -> {
      initStudentsCourse(studentsDetail, studentsCourses);
      studentsRepository.registerStudentsCourses(studentsCourses);
    });
    return studentsDetail;
  }

  private static void initStudentsCourse(StudentsDetail studentsDetail,
      StudentsCourse studentsCourse) {
    studentsCourse.setStudentID(studentsDetail.getStudent().getId());
    LocalDate now = LocalDate.now();
    studentsCourse.setStartDate(now);
    studentsCourse.setEndDate(now.plusYears(1));
  }

  public List<StudentsDetail> searchStudentList() {
    List<Student> studentList = studentsRepository.getAllStudents();
    List<StudentsCourse> studentsCoursesList = studentsRepository.getAllStudentsCourses();
    return studentsConverter.convertStudentsDetails(studentList, studentsCoursesList);
  }

  public StudentsDetail findStudentById(String id) {
    Student student = studentsRepository.findStudentById(id);
    List<StudentsCourse> studentsCourse = studentsRepository.findStudentsCourseById(
        student.getId());

    return new StudentsDetail(student, studentsCourse);
  }


  public List<StudentsCourse> searchStudentsCoursesList() {
    return studentsRepository.getAllStudentsCourses();
  }

  @Transactional
  public void updateStudent(Student student) {
    studentsRepository.updateStudent(student);
  }

  @Transactional
  public void updateStudentsCourses(StudentsCourse studentsCourse) {
    studentsRepository.updateStudentsCourses(studentsCourse);
  }


}

