package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

/**
 * 受講生テーブル情報と受講生コース情報テーブルに紐づくRepositoryです。
 */

@Mapper
public interface StudentRepository {


  List<Student> search();


  Student searchStudent(Long id);


  List<StudentCourse> searchStudentCourseList();


  List<StudentCourse> searchStudentCourse(Long studentID);


  void registerStudent(Student student);


  void registerStudentCourse(StudentCourse studentCourse);


  void updateStudent(Student student);


  void updateStudentCourse(StudentCourse studentCourse);
}
