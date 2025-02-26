package raisetech.StudentManagement.data;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentsCourses {

  private String courseID;
  private Long studentId;
  private String courses;
  private String start;
  private String end;
}
