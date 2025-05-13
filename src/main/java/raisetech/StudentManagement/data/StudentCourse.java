package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

  private String courseID;
  private Long studentId;
  @NotBlank
  private String courses;

  private LocalDateTime start;
  private LocalDateTime endDate;
  @NotNull
  @Digits(integer = 7, fraction = 0)
  private BigDecimal course_fee;
  @Pattern(regexp = "[〇×]", message = "支払い状況は〇か×でお願い致します。")
  private String payment_status;
}
