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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.yukung.tasklet.dto.MemoDto;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * メモEntityから、メモ一覧画面DTOへの変換を行います。
 * </p>
 * 
 * @author yukung
 * 
 */
public class MemoConverter implements DtoConverter<Memo, MemoDto> {

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dto.converter.DtoConverter#convert(java.lang.Object)
	 */
	@Override
	public MemoDto convert(Memo entity) {
		MemoDto dto = new MemoDto();
		// メモ情報のコンバート
		try {
			BeanUtils.copyProperties(dto, entity);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
		return dto;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dto.converter.DtoConverter#convert(java.util.List)
	 */
	@Override
	public List<MemoDto> convert(List<Memo> entities) {
		ArrayList<MemoDto> list = new ArrayList<MemoDto>();
		for (Memo entity : entities) {
			list.add(convert(entity));
		}
		return list;
	}

}
