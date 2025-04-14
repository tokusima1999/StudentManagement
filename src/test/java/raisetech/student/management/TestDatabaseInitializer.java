package raisetech.student.management;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@TestConfiguration
public class TestDatabaseInitializer {

  @Autowired
  JdbcTemplate jdbcTemplate;

  private char currentCourseIdChar = 'A';

  @PostConstruct
  public void init() {

  }

  @BeforeEach
  public void setUp() {

  }

  public String generateNextCourseId() {
    String courseId = currentCourseIdChar + "1";
    currentCourseIdChar = (char) (currentCourseIdChar + 1);
    return courseId;
  }
}

