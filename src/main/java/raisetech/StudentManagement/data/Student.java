package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {


  private Long id;
  private String name;
  private String hurigana;
  private String nickname;
  private String address;
  private String area;
  private int years;
  private String gender;
  private String remark;
  private Boolean isDeleted;

}
