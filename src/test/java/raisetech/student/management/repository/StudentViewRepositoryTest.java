package raisetech.student.management.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import raisetech.StudentManagement.StudentManagementApplication;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.dto.SearchStudentForm;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.repository.StudentViewRepository;
import raisetech.student.management.TestDatabaseInitializer;

@Import(TestDatabaseInitializer.class)
@MybatisTest
@ContextConfiguration(classes = StudentManagementApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentViewRepositoryTest {

  @Autowired
  StudentViewRepository sut;

  @Autowired
  StudentRepository repository;

  @Autowired
  TestDatabaseInitializer testDatabaseInitializer;

  @Test
  void IDに基づいて受講生の検索が成功すること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setId("1");
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    assertThat(actual).hasSize(1);
  }

  @Test
  void 名前に基づく検索が成功すること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setName("田中 太郎");
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    assertThat(actual).hasSize(1);
  }

  @Test
  void 性別に基づく検索が成功すること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setGender("男性");
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    assertThat(actual).hasSize(2);
  }

  @Test
  void 申し込み状況に基づく検索が成功すること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setApplicationStatus("受講中");
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    assertThat(actual).hasSize(2);
  }

  @Test
  void 受講生コース情報の新規登録が成功すること() {
    String courseId = "D1";
    Long studentId = 1L;
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseID(courseId);
    studentCourse.setStudentId(studentId);
    studentCourse.setCourses("JAVAコース");
    studentCourse.setStart(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));
    studentCourse.setCourseFee(BigDecimal.valueOf(50000));
    studentCourse.setPaymentStatus("〇");
    int beforeSize = sut.searchStudentCourse(studentId).size();
    sut.registerStudentCourseWithCourseId(studentCourse);
    int afterSize = sut.searchStudentCourse(studentId).size();

    assertThat(afterSize).isEqualTo(beforeSize + 1);
  }
}