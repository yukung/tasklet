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
 * Taskletアプリケーションのデータアクセス例外クラスです。<br>
 * システム例外を表現します。
 * </p>
 * 
 * @author yukung
 * 
 */
public class DataAccessException extends RuntimeException {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public DataAccessException() {
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
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
