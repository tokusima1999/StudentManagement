package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.dto.SearchStudentForm;

/**
 * 検索と新規登録の際のテーブルと連携するリポジトリです。
 */
@Mapper
public interface StudentViewRepository {

  /**
   * 受講生検索を行います。
   *
   * @param searchStudentForm 　受講生検索専用フォーム
   * @return 検索結果
   */
  List<StudentDetail> findByCondition(SearchStudentForm searchStudentForm);

  /**
   * 受講生の新規登録を行います。
   *
   * @param studentCourse 　受講生コース情報
   */
  void registerStudentCourseAutoCourseId(StudentCourse studentCourse);

  /**
   * コースIDを用いた受講生コース情報の新規登録を行います。このコードはテスト用のコードです。
   *
   * @param studentCourse 受講生コース情報
   */
  void registerStudentCourseWithCourseId(StudentCourse studentCourse);

  /**
   * 受講生IDに基づくコース情報の検索を行います。
   *
   * @param studentId 受講生ID
   * @return 該当生の受講コース情報
   */
  List<StudentCourse> searchStudentCourse(Long studentId);
}
