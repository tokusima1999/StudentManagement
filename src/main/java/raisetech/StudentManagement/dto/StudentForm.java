package raisetech.StudentManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;

/**
 * 受講生情報の専用フォームです。 ここを経由して各クラスに利用されます。
 */
@Getter
@Setter
public class StudentForm {

  @NotBlank
  @Size(min = 2, message = "2文字以上を入力してください。")
  private String name;
  @NotBlank
  @Size(min = 2, message = "2文字以上を入力してください。")
  private String hurigana;
  @NotBlank(message = "必須項目です。ひらがな、カタカナ問いません。")
  private String nickname;
  @NotBlank
  @Email
  private String address;
  @NotBlank
  private String area;
  @NotNull
  @Min(0)
  @Max(99)
  int years;

  @NotBlank
  private String gender;
  private String remark;
  @Pattern(regexp = "仮申し込み|本申し込み|受講中|受講終了", message = "申し込み状況は「仮申し込み」、「本申し込み」、「受講中」、「受講終了」のどれかでお願いいたします。")
  private String applicationStatus;

  /**
   * 受講生情報のインスタンスを生成します。IDは自動生成されるので省いています。
   *
   * @return 反映された受講生情報
   */
  public Student getStudent() {
    Student student = new Student();
    student.setName(this.name);
    student.setHurigana(this.hurigana);
    student.setNickname(this.nickname);
    student.setAddress(this.address);
    student.setArea(this.area);
    student.setYears(this.years);
    student.setGender(this.gender);
    student.setRemark(this.remark);
    student.setApplicationStatus(this.applicationStatus);
    return student;
  }
}