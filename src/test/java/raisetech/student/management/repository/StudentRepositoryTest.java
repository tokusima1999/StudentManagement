package raisetech.student.management.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.StudentManagementApplication;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.student.management.TestDatabaseInitializer;

@Import(TestDatabaseInitializer.class)
@SpringBootTest(classes = StudentManagementApplication.class)
@ActiveProfiles("test")
@Transactional
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TestDatabaseInitializer initializer;

  private String initialCourseId = "A";

 30th-kadai
  main
  @Test
  void 受講生の全件検索が成功すること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 受講生検索が成功すること() {
    Long id = 1L;
    Student actual = sut.searchStudent(id);
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(id);
  }

  @Test
  void 受講生コース情報の全件検索が成功すること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 受講生IDに紐づく受講生コース情報の検索が成功すること() {
    Long studentId = 1L;
    List<StudentCourse> actual = sut.searchStudentCourse(studentId);
    assertThat(actual.size()).isEqualTo(1);
  }

  @Test
  void 受講生の新規登録が成功すること() {
    Student student = new Student();
    student.setName("テスト");
    student.setHurigana("テスト");
    student.setNickname("テスト");
    student.setAddress("test@example.com");
    student.setArea("徳島県阿南市");
    student.setYears(21);
    student.setGender("男");
    student.setRemark("特になし");
    student.setIsDeleted(false);
    student.setApplicationStatus("仮申し込み");
    sut.registerStudent(student);
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(4);
  }

 30th-kadai
  main

  @Test
  void コースIDが自動生成されること() {

    jdbcTemplate.execute("DELETE FROM students_courses");

    String firstCourseId = initialCourseId + "1";
    jdbcTemplate.update("INSERT INTO students_courses(course_id,student_id,courses,start,endDate)"
            + "VALUES(?,?,?,?,?)", firstCourseId, 1, "JAVA", "2025-04-01 00:00:00",
        "2026-04-01 00:00:00");
    String courseId = jdbcTemplate.queryForObject(
        "SELECT course_id FROM students_courses WHERE student_id=1",
        String.class);
    assertThat(courseId).startsWith("A");
    initialCourseId = String.valueOf((char) (initialCourseId.charAt(0) + 1));
    String secondCourseId = initialCourseId + "1";
    jdbcTemplate.update("INSERT INTO students_courses(course_id,student_id,courses,start,endDate)"
            + "VALUES(?,?,?,?,?)", secondCourseId, 2, "AWS", "2025-05-01 00:00:00",
        "2026-05-01 00:00:00");
    String nextCourseId = jdbcTemplate.queryForObject(
        "SELECT course_id FROM students_courses WHERE student_id=2",
        String.class);
    assertThat(nextCourseId).startsWith("B");
  }

  @Test
  void 受講生コース情報の新規登録が成功してそれが確認されること() {
    Long id = 1L;
    String courseId = initializer.generateNextCourseId();
    assertThat(courseId).isNotNull();
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseID(courseId);
    studentCourse.setStudentId(id);
    studentCourse.setCourses("JAVAコース");
    LocalDateTime startDate = LocalDateTime.of(2025, 3, 1, 0, 0);
    LocalDateTime endDate = LocalDateTime.of(2026, 3, 1, 0, 0);
    studentCourse.setStart(startDate);
    studentCourse.setEndDate(endDate);
    studentCourse.setCourse_fee(BigDecimal.valueOf(200000));
    studentCourse.setPayment_status("〇");
    int beforeSize = sut.searchStudentCourse(id).size();
    sut.registerStudentCourse(studentCourse);
    int afterSize = sut.searchStudentCourse(id).size();
    assertThat(afterSize).isEqualTo(beforeSize + 1);
  }

  @Test
  void 受講生IDによる受講生検索を行い該当受講生の更新処理が成功すること() {
    Long id = 1L;
    Student updateStudent = new Student();
    updateStudent.setId(id);
    updateStudent.setName("田中二郎");
    updateStudent.setHurigana("たなかじろう");
    updateStudent.setNickname("ジロ");
    updateStudent.setAddress("zirou.tanaka@example.com");
    updateStudent.setArea("徳島県板野郡藍住町");
    updateStudent.setYears(22);
    updateStudent.setGender("男");
    updateStudent.setRemark("特になし");
    updateStudent.setIsDeleted(false);
    sut.updateStudent(updateStudent);
    Student actual = sut.searchStudent(id);
    assertThat(actual.getName()).isEqualTo("田中二郎");
    assertThat(actual.getHurigana()).isEqualTo("たなかじろう");
    assertThat(actual.getNickname()).isEqualTo("ジロ");
    assertThat(actual.getAddress()).isEqualTo("zirou.tanaka@example.com");
    assertThat(actual.getArea()).isEqualTo("徳島県板野郡藍住町");
    assertThat(actual.getIsDeleted()).isEqualTo(false);
  }

  @Test
  void 受講生IDによるコース情報検索を実行し該当コース情報の更新処理が成功すること() {
    Long studentId = 1L;
    StudentCourse updateStudentCourse = new StudentCourse();
    updateStudentCourse.setStudentId(studentId);
    updateStudentCourse.setCourses("タイピング練習");
    sut.updateStudentCourse(updateStudentCourse);
    List<StudentCourse> actualList = sut.searchStudentCourse(studentId);
    StudentCourse actual = actualList.get(0);
    assertThat(actual.getCourses()).isEqualTo("タイピング練習");
  }
}