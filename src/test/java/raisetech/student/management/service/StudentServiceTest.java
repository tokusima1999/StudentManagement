package raisetech.student.management.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.InvalidStudentIdException;
import raisetech.StudentManagement.exception.StudentNotFoundException;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.service.StudentService;

@Transactional
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細情報を全件検索し全件取得が動作すること_リポジトリとコンバーターの処理が適切に実行されていること() {
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);
    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生検索ができること＿リポジトリの処理が実行されていること() {
    Long id = 1L;
    Student student = new Student();
    student.setId(id);
    StudentDetail expected = new StudentDetail(student, new ArrayList<>());
    //挙動を明示的にして、分かりやすくした。
    when(repository.searchStudent(id)).thenReturn(student);
    when(repository.searchStudentCourseList()).thenReturn(new ArrayList<>());

    StudentDetail actual = sut.searchStudent(id);

    verify(repository, times(1)).searchStudent(id);
    verify(repository, times(1)).searchStudentCourseList();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void IDが負の場合の例外処理が適切に実行されていること() {
    Exception exception = Assertions.assertThrows(InvalidStudentIdException.class, () -> {
      sut.searchStudent(0L);
    });
    Assertions.assertEquals("受講生IDは1以上でお願いします。", exception.getMessage());
  }

  @Test
  void 登録されていないIDが入力された場合の例外処理が適切に実行されていること() {
    Long id = 999L;
    Exception exception = Assertions.assertThrows(StudentNotFoundException.class, () -> {
      sut.searchStudent(id);
    });
    Assertions.assertEquals("受講生ID" + id + "は登録されていません。", exception.getMessage());
  }

  @Test
  void 受講生の新規登録ができること＿リポジトリが適切に実行されていること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生の更新処理ができること＿リポジトリが適切に実行されていること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    sut.updateStudent(studentDetail);
    verify(repository, times(1)).updateStudent(studentDetail.getStudent());
    verify(repository, times(1)).updateStudentCourse(studentCourse);
  }

  @Test
  void 受講生の初期値設定ができること＿ローカルデートタイムが適切に処理されていること() {
    Long id = 999L;
    Student student = new Student();
    student.setId(id);
    StudentCourse studentCourse = new StudentCourse();
    sut.initStudentsCourse(studentCourse, student);
    Assertions.assertEquals(999L, studentCourse.getStudentId());
    LocalDateTime expectedStart = LocalDateTime.now().withNano(0);
    LocalDateTime actualStart = studentCourse.getStart().withNano(0);

    Assertions.assertEquals(expectedStart.toLocalDate(), actualStart.toLocalDate());
    Assertions.assertEquals(expectedStart.getHour(), actualStart.getHour());
    Assertions.assertEquals(expectedStart.getMinute(), actualStart.getMinute());
    Assertions.assertEquals(expectedStart.getSecond(), actualStart.getSecond());
    Assertions.assertEquals(actualStart.plusYears(1).withNano(0),
        studentCourse.getEndDate().withNano(0));
  }
}
