package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

/**
 * 受講生テーブル情報と受講生コース情報テーブルに紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を実行します。
   * @return　全件の受講生一覧
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * 受講生検索を実行します。
   * @param id 　受講生ID
   * @return　受講生情報
   */
  @Select("SELECT * FROM students WHERE id=#{id}")
  Student searchStudent(Long id);

  /**
   * 受講生のコース情報の全件検索を実行します。
   * @return　全件の受講生のコース情報
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  /**
   * 受講生IDに紐づく受講生のコース情報の検索を実行します。
   * @param studentID 　受講生ID
   * @return　受講生IDに紐づく受講生のコース情報
   */
  @Select("SELECT * FROM students_courses WHERE studentID=#{studentId}")
  List<StudentsCourses> searchStudentsCourses(Long studentID);

  /**
   * 受講生の新規登録を実行します。 受講生IDは1,2,3,4と自動的に設定されるようになっています。
   * @param student 　受講生テーブル
   */
  @Insert("INSERT INTO students(name,hurigana,nickname,address,area,years,gender,remark,isDeleted) "
      + "VALUES(#{name}, #{hurigana}, #{nickname}, #{address}, #{area}, #{years}, #{gender}, #{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  /**
   * 受講生コース情報の新規登録を実行します。 受講生IDに紐づくようにstudentIDを設定しています。
   * コースIDはA1,B1,C1と自動的に挿入されるようにMySQLで設定済みです。
   * @param studentsCourses 　受講生コース情報
   */
  @Insert("INSERT INTO students_courses(studentId,courses,start,end)" +
      "VALUES(#{studentId},#{courses},#{start},#{end})")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  /**
   * 受講生IDによる受講生検索を実行します。
   * @param id 　受講生ID
   * @return 受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(Long id);

  /**
   * 受講生IDによる受講生コース情報の検索を実行します。
   * @param id 　受講生ID
   * @return　受講生情報
   */
  @Select("SELECT * FROM students_courses WHERE studentID = #{id}")
  List<StudentsCourses> findCoursesByStudentId(Long id);

  /**
   * 受講生IDによる受講生検索を行い、該当受講生の更新処理を実行します。
   * @param student 　受講生テーブル
   */
  @Update("UPDATE students SET name = #{name}, hurigana = #{hurigana}, nickname = #{nickname}, "
      + "address = #{address}, area = #{area}, years = #{years}, gender = #{gender}, "
      + "remark = #{remark} WHERE id = #{id}")
  void updateStudent(Student student);

  /**
   * 受講生IDによるコース検索を実行し、該当コース情報の更新処理を実行します。
   * @param studentsCourses 　受講生コース情報テーブル
   */
  @Update("UPDATE students_courses SET courses = #{courses} "
      + " WHERE studentID = #{studentId}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
