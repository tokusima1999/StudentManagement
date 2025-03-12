package raisetech.StudentManagement.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleStudentNotFoundException(
      StudentNotFoundException ex) {
    //エラーレスポンス
    Map<String, String> errorResponse = new HashMap<>();
    //エラーメッセージ
    errorResponse.put("message", ex.getMessage());
    //エラーコード
    errorResponse.put("errorCode", ex.getErrorCode());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidStudentIdException.class)
  public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
      InvalidStudentIdException ex) {
    //エラーレスポンス
    Map<String, String> errorResponse = new HashMap<>();
    //エラーメッセージ
    errorResponse.put("message", ex.getMessage());
    //エラーコード
    errorResponse.put("errorCode", ex.getErrorCode());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
