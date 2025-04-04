package raisetech.student.management.converter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

class StudentConverterTest {

  @Test
  void 受講生のIDと受講生コース情報の受講生IDが正しくマッピングできているか() {
    Student student = new Student();
    Long id = 1L;
    student.setId(id);
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId(id);
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    List<StudentCourse> studentCourseList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    studentList.add(student);
    studentCourseList.add(studentCourse);
    StudentConverter studentConverter = new StudentConverter();
    List<StudentDetail> result = studentConverter.convertStudentDetails(studentList,
        studentCourseList);

    assertThat(result).hasSize(1);
    studentDetail = result.get(0);
    assertThat(studentDetail.getStudent().getId()).isEqualTo(id);
    assertThat(studentCourse.getStudentId()).isEqualTo(id);
  }
}