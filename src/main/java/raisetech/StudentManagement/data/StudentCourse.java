package raisetech.StudentManagement.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

  private String courseID;
  private Long studentId;
  private String courses;
  private LocalDateTime start;
  private LocalDateTime end;
}
