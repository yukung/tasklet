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
package org.yukung.tasklet.factory;

import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.DetailDto;
import org.yukung.tasklet.dto.MemoDto;
import org.yukung.tasklet.dto.SortableDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.dto.UserDto;
import org.yukung.tasklet.dto.converter.ActivityConverter;
import org.yukung.tasklet.dto.converter.DetailConverter;
import org.yukung.tasklet.dto.converter.DtoConverter;
import org.yukung.tasklet.dto.converter.MemoConverter;
import org.yukung.tasklet.dto.converter.SortableConverter;
import org.yukung.tasklet.dto.converter.TaskConverter;
import org.yukung.tasklet.dto.converter.UserConverter;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * DtoConverterの実装クラスの生成を行い、具象クラスの生成及びコンバート処理は<br>
 * DtoConverterインタフェースとして提供します。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ConverterFactory {

	@SuppressWarnings("unchecked")
	public static <E, D> DtoConverter<E, D> createDtoConverter(Class<D> entity) {
		DtoConverter<E, D> converter = null;

		if (entity == ActivityDto.class) {
			converter = (DtoConverter<E, D>) new ActivityConverter();
		} else if (entity == TaskDto.class) {
			converter = (DtoConverter<E, D>) new TaskConverter();
		} else if (entity == DetailDto.class) {
			converter = (DtoConverter<E, D>) new DetailConverter();
		} else if (entity == MemoDto.class) {
			converter = (DtoConverter<E, D>) new MemoConverter();
		} else if (entity == SortableDto.class) {
			converter = (DtoConverter<E, D>) new SortableConverter();
		} else if (entity == UserDto.class) {
			converter = (DtoConverter<E, D>) new UserConverter();
		} else {
			throw new DataAccessException("errors.general");
		}
		return converter;
	}
}
