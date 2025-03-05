package raisetech.StudentManagement.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;
/**
 *受講性の検索・登録・更新などを行うREST APIとして実行されるController
 */
@RestController
public class StudentController {

  private StudentService service;


  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行いますが、条件指定はしません。
   * @return　受講生一覧
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(){
    return service.searchStudentList();
  }

  /**
   * 受講生検索です。
   * 受講生IDに紐づく受講生情報を取得します。
   * @param id　受講生ID
   * @return 該当する受講生情報
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable Long id){
    return service.searchStudent(id);
  }

  /**
   * 受講生の新規登録を実行します。
   * @param studentDetail　受講生詳細
   * @return　登録された受講生情報
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
   StudentDetail responseStudentDetail= service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生の更新を実行します。
   * @param studentDetail　受講生詳細
   * @return　更新処理が成功です。
   */
  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功です。");
  }
}