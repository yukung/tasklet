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
package org.yukung.tasklet.logic;

import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * DB更新のトランザクション単位を規定するインタフェースです。<br>
 * 1メソッドが1トランザクションを表現します。
 * </p>
 * 
 * @author yukung
 * 
 * @param <E>
 *            Entityオブジェクトの型
 */
public interface TransactionLogic<E> {

	/**
	 * <p>
	 * パラメータのEntityをDBへ格納します。
	 * </p>
	 * 
	 * @param entity
	 * @throws TaskletException
	 *             DB登録エラー
	 */
	public void store(E entity) throws TaskletException;

	/**
	 * <p>
	 * パラメータのEntity(複数）をDBへ格納します。
	 * </p>
	 * 
	 * @param entities
	 * @throws TaskletException
	 *             DB登録エラー
	 */
	public void store(Object... entities) throws TaskletException;
}
