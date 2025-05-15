package raisetech.student.management.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.dto.SearchStudentForm;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.repository.StudentViewRepository;
import raisetech.StudentManagement.service.StudentService;
import raisetech.StudentManagement.service.StudentViewService;

@ExtendWith(MockitoExtension.class)
class StudentViewServiceTest {

  @Mock
  private StudentRepository repository;
  @Mock
  private StudentViewRepository viewRepository;
  @Mock
  private StudentService service;
  @InjectMocks
  private StudentViewService sut;

  @Test
  void IDで検索できること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setId("1");
    Student student = new Student();
    student.setId(1L);
    StudentDetail studentDetail = new StudentDetail();
    List<StudentDetail> expect = List.of(studentDetail);
    when(viewRepository.findByCondition(searchStudentForm)).thenReturn(expect);
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    Assertions.assertEquals(expect, actual);
  }

  @Test
  void 名前で検索できること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setName("田中太郎");
    Student student = new Student();
    student.setName("田中太郎");
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    List<StudentDetail> expect = List.of(studentDetail);
    when(viewRepository.findByCondition(searchStudentForm)).thenReturn(expect);
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    Assertions.assertEquals(expect, actual);
  }

  @Test
  void 申し込み状況で検索できること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setApplicationStatus("受講中");
    Student student = new Student();
    student.setApplicationStatus("受講中");
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    List<StudentDetail> expect = List.of(studentDetail);
    when(viewRepository.findByCondition(searchStudentForm)).thenReturn(expect);
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    Assertions.assertEquals(expect, actual);
  }

  @Test
  void 性別で検索できること() {
    SearchStudentForm searchStudentForm = new SearchStudentForm();
    searchStudentForm.setGender("男性");
    Student student = new Student();
    student.setGender("男性");
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    List<StudentDetail> expect = List.of(studentDetail);
    when(viewRepository.findByCondition(searchStudentForm)).thenReturn(expect);
    List<StudentDetail> actual = sut.findByCondition(searchStudentForm);
    Assertions.assertEquals(expect, actual);
  }

  @Test
  void 受講生新規登録ができること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.registerStudentAndStudentCourse(studentDetail);
    verify(repository, times(1)).registerStudent(student);
    verify(viewRepository, times(1)).registerStudentCourseAutoCourseId(studentCourse);
  }

  @Test
  void 仮申し込みのときバツと入金状況に表示されること() {
    Student student = new Student();
    student.setId(1L);
    student.setApplicationStatus("仮申し込み");
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.registerStudentAndStudentCourse(studentDetail);
    Assertions.assertEquals("×", studentCourse.getPaymentStatus());
  }

  @Test
  void 本申込みのときマルと入金状況に表示されること() {
    Student student = new Student();
    student.setId(1L);
    student.setApplicationStatus("本申込み");
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.registerStudentAndStudentCourse(studentDetail);
    Assertions.assertEquals("〇", studentCourse.getPaymentStatus());
  }

  @Test
  void 受講中のときマルと入金状況に表示されること() {
    Student student = new Student();
    student.setId(1L);
    student.setApplicationStatus("受講中");
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.registerStudentAndStudentCourse(studentDetail);
    Assertions.assertEquals("〇", studentCourse.getPaymentStatus());
  }

  @Test
  void 受講終了のときマルと入金状況に表示されること() {
    Student student = new Student();
    student.setId(1L);
    student.setApplicationStatus("受講終了");
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.registerStudentAndStudentCourse(studentDetail);
    Assertions.assertEquals("〇", studentCourse.getPaymentStatus());
  }
}