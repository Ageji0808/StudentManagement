package raisetech.student.management.service;

import raisetech.student.management.Controller.converter.StudentsConverter;
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
    studentsRepository.registerStudent(studentsDetail.getStudent());
    studentsDetail.getStudentsCoursesList().forEach(studentsCourses -> {
      initStudentsCourse(studentsDetail, studentsCourses);
      studentsRepository.registerStudentsCourses(studentsCourses);
    });
    return studentsDetail;
  }

  private static void initStudentsCourse(StudentsDetail studentsDetail,
      StudentsCourses studentsCourses) {
    studentsCourses.setStudentID(studentsDetail.getStudent().getId());
    LocalDate now = LocalDate.now();
    studentsCourses.setStartDate(now);
    studentsCourses.setEndDate(now.plusYears(1));
  }

  public List<StudentsDetail> searchStudentList() {
    List<Student> studentList = studentsRepository.getAllStudents();
    List<StudentsCourses> studentsCoursesList = studentsRepository.getAllStudentsCourses();
    return studentsConverter.convertStudentsDetails(studentList, studentsCoursesList);
  }

  public StudentsDetail findStudentById(String id) {
    Student student = studentsRepository.findStudentById(id);
    List<StudentsCourses> studentsCourses = studentsRepository.findStudentsCourseById(
        student.getId());

    return new StudentsDetail(student, studentsCourses);
  }


  public List<StudentsCourses> searchStudentsCoursesList() {
    return studentsRepository.getAllStudentsCourses();
  }

  @Transactional
  public void updateStudent(Student student) {
    studentsRepository.updateStudent(student);
  }

  @Transactional
  public void updateStudentsCourses(StudentsCourses studentsCourses) {
    studentsRepository.updateStudentsCourses(studentsCourses);
  }


}

