package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();


  @Select("SELECT * FROM students WHERE id=#{id}")
  Student searchStudent(Long id);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  @Select("SELECT * FROM students_courses WHERE studentID=#{studentId}")
  List<StudentsCourses> searchStudentsCourses(Long studentID);

  @Insert("INSERT INTO students(name,hurigana,nickname,address,area,years,gender,remark,isDeleted) "
      + "VALUES(#{name}, #{hurigana}, #{nickname}, #{address}, #{area}, #{years}, #{gender}, #{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);


  @Insert("INSERT INTO students_courses(studentID,courses,start,end)" +
      "VALUES(#{studentID},#{courses},#{start},#{end})")
  void registerStudentsCourses(StudentsCourses studentsCourses);


  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(Long id);

  @Select("SELECT * FROM students_courses WHERE studentID = #{id}")
  List<StudentsCourses> findCoursesByStudentId(Long id);

  @Update("UPDATE students SET name = #{name}, hurigana = #{hurigana}, nickname = #{nickname}, "
      + "address = #{address}, area = #{area}, years = #{years}, gender = #{gender}, "
      + "remark = #{remark} WHERE id = #{id}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET courses = #{courses} "
      + " WHERE studentID = #{studentId}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
