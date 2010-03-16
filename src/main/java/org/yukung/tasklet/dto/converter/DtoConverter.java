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
package org.yukung.tasklet.dto.converter;

import java.util.List;

/**
 * <p>
 * EntityとDTOのコンバート処理を提供するインタフェースです。<br>
 * コンバート処理はこのインタフェースが提供するメソッドを使用して実行されます。
 * </p>
 * 
 * @author yukung
 * 
 * @param <E>
 *            変換元のEntityの型
 * @param <D>
 *            変換先のDTOの型
 */
public interface DtoConverter<E, D> {

	/**
	 * <p>
	 * Entityから、DTOへの変換を行います。
	 * </p>
	 * 
	 * @param entity
	 * @return 変換したDTO
	 */
	public D convert(E entity);

	/**
	 * <p>
	 * Entityのリスト → DTOのListへの変換を行います。
	 * </p>
	 * 
	 * @param entities
	 *            変換元のEntityのList
	 * @return 変換先のDTOのList
	 */
	public List<D> convert(List<E> entities);
}
