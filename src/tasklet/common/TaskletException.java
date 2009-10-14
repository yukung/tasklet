/**
 *
 */
package tasklet.common;

/**
 * Taskletアプリケーション共通の例外クラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class TaskletException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskletException() {
		super();
	}

	public TaskletException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskletException(String message) {
		super(message);
	}

	public TaskletException(Throwable cause) {
		super(cause);
	}

}
