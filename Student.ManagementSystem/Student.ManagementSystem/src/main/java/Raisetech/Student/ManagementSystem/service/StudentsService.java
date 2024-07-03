package Raisetech.Student.ManagementSystem.service;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.repository.StudentsCoursesRepository;
import Raisetech.Student.ManagementSystem.repository.StudentsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {

  private StudentsRepository studentsRepository;
  private StudentsCoursesRepository studentsCoursesRepository;

  @Autowired
  public StudentsService(StudentsRepository studentsRepository, StudentsCoursesRepository studentsCoursesRepository) {
    this.studentsRepository = studentsRepository;
    this.studentsCoursesRepository = studentsCoursesRepository;
  }

  public List<Students> searchStudentsList() {
    int startAge = 30;
    int endAge = 39;
    return studentsRepository.getAllStudents()
        .stream()
        .filter(student -> student.getAge() >= startAge && student.getAge() <= endAge)
        .collect(Collectors.toList());}

  public List<StudentsCourses> searchStudentsCoursesList() {
    return studentsCoursesRepository.getAllStudentsCourses()
        .stream()
        .filter(sc -> "Java".equals(sc.getCourseName()))
        .collect(Collectors.toList());
  }
}

