package raisetech.StudentManagement.controller.view;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.dto.SearchStudentForm;
import raisetech.StudentManagement.dto.StudentCourseForm;
import raisetech.StudentManagement.dto.StudentForm;
import raisetech.StudentManagement.dto.StudentRegisterForm;
import raisetech.StudentManagement.repository.StudentRepository;
import raisetech.StudentManagement.service.StudentService;
import raisetech.StudentManagement.service.StudentViewService;

/**
 * 受講生の新規登録・検索・一覧表示を行うControllerです。
 */
@AllArgsConstructor
@Controller
public class StudentViewController {

  @Autowired
  private StudentService service;

  @Autowired
  private StudentViewService viewService;

  /**
   * ホーム画面に移動します。
   *
   * @return ホーム画面
   */
  @GetMapping("/home")
  public String homeScreen() {
    return "home";
  }

  /**
   * 受講生の一覧表示を行います。
   *
   * @param model オブジェクトとしての受講生一覧
   * @return 受講生一覧(ビュー表示)
   */
  @GetMapping("/list")
  public String listScreen(Model model) {
    List<StudentDetail> studentList = service.searchStudentList();
    model.addAttribute("studentList", studentList);
    return "list";
  }

  /**
   * 受講生新規登録のフォームを表示します。
   *
   * @param model フォームをビューに渡すmodel
   * @return 受講生新規登録フォーム
   */
  @GetMapping("/registerStudentForm")
  public String registerStudentForm(Model model) {
    StudentRegisterForm studentRegisterForm = new StudentRegisterForm();
    model.addAttribute("studentRegisterForm", studentRegisterForm);
    return "registerStudentForm";
  }

  /**
   * 受講生新規登録が成功したときの画面を表示します。
   *
   * @return 受講生新規登録の成功を伝える画面
   */
  @GetMapping("registerStudentView")
  public String studentRegisteredScreen(Model model) {
    return "registerCompleteScreen";
  }

  /**
   * 受講生新規登録の処理を行います。
   *
   * @param studentRegisterForm 受講生新規登録フォーム
   * @return 受講生新規登録の成功を伝える画面
   */
  @PostMapping(value = "/registerStudentView", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String registerStudentView(@ModelAttribute StudentRegisterForm studentRegisterForm) {
    StudentForm studentForm = studentRegisterForm.getStudent();
    StudentCourseForm studentCourseForm = studentRegisterForm.getStudentCourse();
    StudentDetail studentDetail = new StudentDetail(studentForm.getStudent(),
        studentCourseForm.getStudentCourseList());
    viewService.registerStudentAndStudentCourse(studentDetail);
    return "redirect:/registerStudentView";
  }

  /**
   * 受講生検索フォーム表示を行います。
   *
   * @return 受講生検索フォーム
   */
  @GetMapping("/searchStudentForm")
  public String searchStudentForm(Model model) {
    model.addAttribute("searchStudentForm", new SearchStudentForm());
    return "searchStudentForm";
  }

  /**
   * 受講生検索を行います。
   *
   * @param searchStudentForm 受講生検索フォームに入力された値
   * @return 入力値に該当する検索結果
   */
  @PostMapping("/searchResult")
  public String searchStudent(@ModelAttribute SearchStudentForm searchStudentForm, Model model) {
    List<StudentDetail> result = viewService.findByCondition(searchStudentForm);
    model.addAttribute("studentList", result);
    return "searchResult";
  }
}
