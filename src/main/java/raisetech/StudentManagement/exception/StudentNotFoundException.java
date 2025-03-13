package raisetech.StudentManagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
/**
 * アプリケーション全体で使用できる共通の例外クラスです。
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

  private String errorCode;

  /**
   * メッセージなしでの例外処理を行います。
   */
  public StudentNotFoundException() {
    super();
  }

  /**
   * エラーメッセージを指定した場合の例外処理を行います。
   *
   * @param message 　エラー詳細メッセージ
   */
  public StudentNotFoundException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  /**
   * @param message   エラーメッセージ
   * @param errorCode 　エラーコード
   */
  public StudentNotFoundException(String message, Throwable cause, String errorCode) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  /**
   * エラー原因のみを指定して例外処理を行います。
   *
   * @param cause 　エラー原因
   */
  public StudentNotFoundException(Throwable cause) {
    super(cause);
  }

  /**
   * 詳細な処理を行います。（非推奨）
   *
   * @param message            　エラーメッセージ
   * @param cause              　エラー原因
   * @param enableSuppression  　trueの場合、例外発生時の、それ自体の保存だけでなく、その例外に関連した例外の保存もします。
   * @param writableStackTrace 　trueの場合、実行時のプロセスの履歴を表示します。
   */
  protected StudentNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
