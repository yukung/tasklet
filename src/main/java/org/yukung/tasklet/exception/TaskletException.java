/**
 * Copyright 2009-2010 Yusuke Ikeda. (@yukung) <yukung.i@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.yukung.tasklet.exception;

/**
 * <p>
 * Taskletアプリケーションの共通例外クラスです。<br>
 * アプリケーション例外を表現します。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskletException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public TaskletException() {
		super();
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param message
	 * @param cause
	 */
	public TaskletException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param message
	 */
	public TaskletException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param cause
	 */
	public TaskletException(Throwable cause) {
		super(cause);
	}
}
