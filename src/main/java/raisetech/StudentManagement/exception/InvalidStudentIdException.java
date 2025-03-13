package raisetech.StudentManagement.exception;

import lombok.Getter;

@Getter
public class InvalidStudentIdException extends RuntimeException {

  private String errorCode;

  public InvalidStudentIdException() {
    super();
  }

  public InvalidStudentIdException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public InvalidStudentIdException(String message, Throwable cause, String errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public InvalidStudentIdException(Throwable cause) {
    super(cause);
    this.errorCode = null;
  }
}
