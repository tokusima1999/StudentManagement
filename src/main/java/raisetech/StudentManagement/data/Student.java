package raisetech.StudentManagement.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Schema(description = "受講生情報")
@Getter
@Setter
@Validated
public class Student {

  private Long id;
  @NotBlank
  @Size(min = 2, message = "2文字以上を入力してください。")
  private String name;
  @NotBlank
  @Size(min = 2, message = "2文字以上を入力してください。")
  private String hurigana;
  @NotBlank(message = "必須項目です。ひらがな、カタカナ問いません。")
  private String nickname;
  @NotBlank
  private String address;
  @NotBlank
  private String area;

  int years;

  @NotBlank
  private String gender;
  private String remark;
  private Boolean isDeleted;

}
