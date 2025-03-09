package raisetech.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

/**
 * 受講生詳細を受講生コース情報・受講生情報に、もしくは受講生コース情報・受講生情報を受講生詳細に変換するConverterです。
 */
@Component
public class StudentConverter {

  /**
   * 受講生IDに紐づく受講生のコース情報をマッピングします。 受講生のコース情報は受講生に対し複数存在。 ループを回し、受講生詳細情報の組み立てを実行します。
   *
   * @param studentList       　受講生一覧
   * @param studentCourseList 　リスト形式の受講生のコース情報
   * @return　リストでの受講生詳細情報
   */
  public List<StudentDetail> convertStudentDetails(List<Student> studentList,
      List<StudentCourse> studentCourseList) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : studentList) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentsCourseList = studentCourseList.stream()
          .filter(studentCourse -> student.getId().equals(studentCourse.getStudentId())).collect(
              Collectors.toList());

      studentDetail.setStudentCourseList(convertStudentsCourseList);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }
}
