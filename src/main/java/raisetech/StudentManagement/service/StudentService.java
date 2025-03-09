package raisetech.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
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
   *
   * @return　受講生一覧
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生検索を実行します。 受講生IDに紐づく受講生情報を取得し、その受講生のコース情報を取得・設定します。
   *
   * @param id 　受講生ID
   * @return　受講生情報
   */
  public StudentDetail searchStudent(long id) {
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentsCourses = repository.searchStudentCourseList();
    return new StudentDetail(student, studentsCourses);
  }

  /**
   * 受講生の新規登録を実行します。 1、 受講生情報を登録します。 ２、 受講生が受講するコース情報（受講開始日を登録時の日付に、受講終了日を開始日の１年後に設定済み。）を登録します。
   *
   * @param studentDetail 　受講生詳細情報（受講生情報と受講コース情報を含む）
   * @return 登録した受講生情報
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentsCourses -> {
      initStudentsCourse(studentsCourses, student);
      repository.registerStudentCourse(studentsCourses);
    });
    return studentDetail;
  }

  /**
   * 受講コース情報の初期値の設定を行います。指定の受講生IDを設定し、受講開始日、受講終了日を設定します。
   *
   * @param studentCourse 　初期化する受講コース情報
   * @param student       　受講生情報
   */
  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    LocalDateTime now = LocalDateTime.now();
    studentCourse.setStudentId(student.getId());

    studentCourse.setStart(String.valueOf(now));
    studentCourse.setEnd(String.valueOf(now.plusYears(1)));
  }

  /**
   * 受講生の更新処理を実行します。 System.out.println(~getCourses());はデバッグ用のコードです。
   *
   * @param studentDetail 　受講生詳細情報
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());

    for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
      System.out.println(
          "Updating course for student ID: " + studentCourse.getStudentId() + ", Course: "
              + studentCourse.getCourses());
      repository.updateStudentCourse(studentCourse);
    }
  }

}
