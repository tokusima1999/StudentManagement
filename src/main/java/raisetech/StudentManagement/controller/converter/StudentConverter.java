package raisetech.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;

/**
 * 受講生詳細を受講生コース情報・受講生情報に、もしくは受講生コース情報・受講生情報を受講生詳細に変換するConverterです。
 */
@Component
public class StudentConverter {

  /**
   * 受講生IDに紐づく受講生のコース情報をマッピングします。 受講生のコース情報は受講生に対し複数存在。
   * ループを回し、受講生詳細情報の組み立てを実行します。
   * @param students        　受講生一覧
   * @param studentsCourses 　リスト形式の受講生のコース情報
   * @return　リストでの受講生詳細情報
   */
  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentsCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : students) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentsCourses = studentsCourses.stream()
          .filter(studentCourse -> student.getId().equals(studentCourse.getStudentId())).collect(
              Collectors.toList());

      studentDetail.setStudentsCourses(convertStudentsCourses);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }
}
