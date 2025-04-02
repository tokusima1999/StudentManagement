package raisetech.student.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentManagement.controller.StudentController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@ContextConfiguration(classes = StudentController.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生一覧検索ができ空のリストが返ること() throws Exception {
    mockMvc.perform(get("/studentList")).andExpect(status().isOk());
    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生検索ができること_空のリストが返ること() throws Exception {
    Long id = 1L;
    Student student = new Student();
    student.setId(id);
    mockMvc.perform(get("/student/{id}", id)).andExpect(status().isOk());
    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生の新規登録ができること_仮のデータが登録されること() throws Exception {
    Student student = new Student();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
                "student" : {
                    "name" : "弥生佳代",
                    "hurigana" : "やよいかよ",
                    "nickname" : "かよ",
                    "address" : "kayo.yayoi@example.com",
                    "area" : "石川県小松市",
                    "years" : 47,
                    "gender" : "女性",
                    "remark" : ""
                },
                "studentCourseList" : [
                    {
                        "courses" : "C言語コース"
                    }
                ]
            }
            """)).andExpect(status().isOk());

    verify(service, times(1)).registerStudent(ArgumentMatchers.any());
  }

  @Test
  void 受講生情報の更新ができること_仮のデータが更新されること() throws Exception {
    Student student = new Student();
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
        """
             {
                "student" : {
                    "name" : "弥生佳代",
                    "hurigana" : "やよいかよ",
                    "nickname" : "かよ",
                    "address" : "kayo.yayoi@example.com",
                    "area" : "石川県小松市",
                    "years" : 42,
                    "gender" : "女性",
                    "remark" : ""
                },
                "studentCourseList" : [
                    {
                        "courses" : "C言語コース"
                    }
                ]
            }
            """
    ));
    verify(service, times(1)).updateStudent(ArgumentMatchers.any());
  }

  @Test
  void ニックネームの入力チェックが成功すること() {
    Student student = new Student();
    student.setNickname("テスト");
    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertEquals(5, violations.size());
  }

  @Test
  void 名前が2文字以下だと入力チェックエラーが出ること() {
    Student student = new Student();
    student.setName("あ");
    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertThat(violations.size()).isEqualTo(6);
  }
}