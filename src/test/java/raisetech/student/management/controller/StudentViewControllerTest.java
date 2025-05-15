package raisetech.student.management.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlTemplate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentManagement.controller.view.StudentViewController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.dto.SearchStudentForm;
import raisetech.StudentManagement.dto.StudentCourseForm;
import raisetech.StudentManagement.dto.StudentForm;
import raisetech.StudentManagement.dto.StudentRegisterForm;
import raisetech.StudentManagement.service.StudentService;
import raisetech.StudentManagement.service.StudentViewService;

@WebMvcTest(StudentViewController.class)
@ContextConfiguration(classes = StudentViewController.class)
class StudentViewControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  @MockBean
  private StudentViewService viewService;

  @Test
  void ホーム画面遷移が成功すること() throws Exception {
    mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(view().name("home"));
  }

  @Test
  void ビュー表示での受講生一覧画面表示が成功すること() throws Exception {
    mockMvc.perform(get("/list")).andExpect(status().isOk()).andExpect(view().name("list"));
  }

  @Test
  void 受講生新規登録フォームの表示が成功すること() throws Exception {
    mockMvc.perform(get("/registerStudentForm")).andExpect(status().isOk())
        .andExpect(view().name("registerStudentForm"));
  }

  @Test
  void 受講生新規登録成功の画面の表示が成功すること() throws Exception {
    mockMvc.perform(get("/registerStudentView")).andExpect(status().isOk())
        .andExpect(view().name("registerCompleteScreen"));
  }

  @Test
  void 受講生新規登録の処理が成功すること() throws Exception {
    mockMvc.perform(post("/registerStudentView").param("student.name", "田中太郎")
            .param("student.hurigana", "たなかたろう").param("student.nickname", "たろう")
            .param("student.address", "tarou.tanaka@example.com")
            .param("student.area", "徳島県阿南市").param("student.years", "22")
            .param("student.gender", "男性").param("studentCourse.courses", "JAVA")
            .param("studentCourse.course_fee", "50000").param("student.remark", "特になし")
            .param("student.applicationStatus", "受講中")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/registerStudentView"));
    verify(viewService, times(1)).registerStudentAndStudentCourse(any(StudentDetail.class));
  }

  @Test
  void 受講生検索フォームの表示が成功すること() throws Exception {
    mockMvc.perform(get("/searchStudentForm")).andExpect(status().isOk())
        .andExpect(view().name("searchStudentForm"));
  }

  @Test
  void 受講生検索が成功すること() throws Exception {
    Student student = new Student();
    student.setName("田中太郎");
    student.setHurigana("たなかたろう");
    student.setNickname("たろう");
    student.setAddress("tarou.tanaka@example.com");
    student.setArea("徳島県阿南市");
    student.setYears(22);
    student.setGender("男性");
    student.setApplicationStatus("受講中");
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    List<StudentDetail> studentDetailList = List.of(studentDetail);
    when(viewService.findByCondition(any(SearchStudentForm.class))).thenReturn(studentDetailList);
    mockMvc.perform(post("/searchResult").param("student.name", "田中太郎")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)).andExpect(status().isOk())
        .andExpect(view().name("searchResult"))
        .andExpect(model().attribute("studentList", studentDetailList));
  }
}