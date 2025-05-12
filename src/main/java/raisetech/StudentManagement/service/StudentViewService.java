package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.dto.SearchStudentForm;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.repository.StudentViewRepository;

/**
 * 受講生の管理を行うサービスです。検索・新規登録を行います。
 */
@Service
public class StudentViewService {

  @Autowired
  StudentService service;
  @Autowired
  StudentViewRepository viewRepository;

  @Autowired
  StudentRepository repository;

  public StudentViewService(StudentService service, StudentViewRepository viewRepository,
      StudentRepository repository) {
    this.service = service;
    this.viewRepository = viewRepository;
    this.repository = repository;
  }

  /**
   * 受講生検索のサービスです。
   *
   * @param searchStudentForm 　受講生の検索用フォーム
   * @return 値を代入した検索
   */
  public List<StudentDetail> findByCondition(SearchStudentForm searchStudentForm) {
    return viewRepository.findByCondition(searchStudentForm);
  }

  /**
   * 受講生の新規登録を行うサービスです。
   *
   * @param studentDetail 受講生詳細
   */
  @Transactional
  public void registerStudentAndStudentCourse(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    StudentCourse studentCourse = studentDetail.getStudentCourseList().get(0);
    String applicationStatus = studentDetail.getStudent().getApplicationStatus();
    if ("仮申し込み".equals(applicationStatus)) {
      studentCourse.setPayment_status("×");
    } else {
      studentCourse.setPayment_status("〇");
    }
    service.initStudentsCourse(studentCourse, student);
    repository.registerStudent(student);
    studentCourse.setStudentId(student.getId());
    viewRepository.registerStudentCourseAutoCourseId(studentCourse);
  }
}
