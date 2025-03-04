package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。 検索・登録・更新を実行します。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生一覧検索。 条件指定なしの全件検索を行います。
   * @return　受講生一覧
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList=repository.search();
    List<StudentsCourses> studentsCoursesList=repository.searchStudentsCoursesList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  /**
   * 受講生検索を実行します。 受講生IDに紐づく受講生情報を取得し、その受講生のコース情報を取得・設定します。
   * @param id 　受講生ID
   * @return　受講生情報
   */
  public StudentDetail searchStudent(long id) {
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    return new StudentDetail(student, studentsCourses);
  }

  /**
   * 受講生の新規登録を実行します。
   * @param studentDetail　受講生詳細情報
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      studentsCourses.setStudentId(studentDetail.getStudent().getId());
      studentsCourses.setStart(String.valueOf(LocalDateTime.now()));
      studentsCourses.setEnd(String.valueOf(LocalDateTime.now().plusYears(1)));
      repository.registerStudentsCourses(studentsCourses);
    }
    return studentDetail;
  }

  /**
   * 受講生の更新処理を実行します。
   * System.out.println(~getCourses());はデバッグ用のコードです。
   * @param studentDetail　受講生詳細情報
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {

    repository.updateStudent(studentDetail.getStudent());

    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      System.out.println(
          "Updating course for student ID: " + studentsCourses.getStudentId() + ", Course: "
              + studentsCourses.getCourses());
      repository.updateStudentsCourses(studentsCourses);
    }
  }

}
