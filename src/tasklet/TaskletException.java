/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet;

/**
 * Taskletアプリケーションの共通例外クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class TaskletException extends Exception {

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
