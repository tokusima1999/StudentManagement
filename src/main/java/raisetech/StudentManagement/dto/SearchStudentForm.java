package raisetech.StudentManagement.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 受講生検索用のフォームです。新たに検索項目を増やしたい時はここに項目を追加してこのクラスを経由して処理を行うコードを作成してください。
 */
@Getter
@Setter
public class SearchStudentForm {

  private String id;
  private String name;
  private String applicationStatus;
  private String gender;

}
