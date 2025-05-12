package raisetech.StudentManagement.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.StudentCourse;

/**
 * 受講生コース情報の専用フォームです。 入力された受講コース情報はここを通して各クラスで利用可能になります。
 */
@Getter
@Setter
public class StudentCourseForm {

  List<StudentCourse> studentCourseList;
  @NotBlank
  private String courses;

  private LocalDateTime start;
  private LocalDateTime endDate;
  @NotNull
  @Digits(integer = 7, fraction = 0)
  private BigDecimal course_fee;
  @Pattern(regexp = "[〇×]", message = "支払い状況は〇か×でお願い致します。")
  private String payment_status;

  /**
   * 入力された情報から 受講生コース情報のインスタンスを生成します。
   *
   * @return 受講生コース情報
   */
  public StudentCourse getStudentCourse() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourses(this.courses);
    studentCourse.setStart(this.start);
    studentCourse.setEndDate(this.endDate);
    studentCourse.setCourse_fee(this.course_fee);
    studentCourse.setPayment_status(this.payment_status);
    return studentCourse;
  }

  /**
   * 入力された情報を基に単一の受講生コース情報のインスタンスを生成します。
   *
   * @return リスト形式の受講生コース情報
   */
  public List<StudentCourse> getStudentCourseList() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourses(this.courses);
    studentCourse.setStart(this.start);
    studentCourse.setEndDate(this.endDate);
    studentCourse.setCourse_fee(this.course_fee);
    studentCourse.setPayment_status(this.payment_status);
    return List.of(studentCourse);
  }
}
