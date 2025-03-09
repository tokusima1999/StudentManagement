package raisetech.StudentManagement.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class Student {


  private Long id;
  private String name;
  private String hurigana;
  @NotBlank(message = "必須項目です。ひらがな、カタカナ問いません。")
  private String nickname;
  private String address;
  private String area;
  private int years;
  private String gender;
  private String remark;
  private Boolean isDeleted;

}
