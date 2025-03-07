package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentCourse {

  private String courseID;
  private Long studentId;
  private String courses;
  private String start;
  private String end;
}
