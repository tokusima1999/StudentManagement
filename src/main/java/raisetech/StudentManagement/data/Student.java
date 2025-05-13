package raisetech.StudentManagement.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
  private Boolean isDeleted;
  @Pattern(regexp = "仮申し込み|本申し込み|受講中|受講終了", message = "申し込み状況は「仮申し込み」、「本申し込み」、「受講中」、「受講終了」のどれかでお願いいたします。")
  private String applicationStatus;
}
