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
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.util.CalculateUtil;

/**
 * <p>
 * アクティビティEntityから、アクティビティ一覧画面DTOへの変換を行います。
 * </p>
 * <p>
 * アクティビティEntityには、タスクEntityとカテゴリEntityの参照を持たせておく必要があります。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityConverter implements DtoConverter<Activity, ActivityDto> {

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dto.converter.DtoConverter#convert(java.lang.Object)
	 */
	@Override
	public ActivityDto convert(Activity entity) {
		ActivityDto dto = new ActivityDto();
		// アクティビティ情報のコンバート
		try {
			BeanUtils.copyProperties(dto, entity);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		Category category = entity.getCategory();
		List<Task> tasks = entity.getTasks();
		// カテゴリ情報とタスク情報が取得できない場合は、アクティビティ情報のみコンバートして返す
		if (category == null || tasks == null) {
			return dto;
		}

		// カテゴリ情報のコンバート
		dto.setCategoryName(category.getName());

		// 以下、導出プロパティの取得
		dto.setAchievementRatio(CalculateUtil.calcAchievementRatio(tasks));
		dto.setRemainingAmount(CalculateUtil.calcRemainingAmount(tasks));
		dto.setOverdue(CalculateUtil.calcOverdue(tasks));
		dto.setRatioOfEstimateAndActual(CalculateUtil
				.calcRatioOfEstimateAndActual(tasks));
		dto.setEstimatedTimeTotal(CalculateUtil.calcEstimatedTimeTotal(tasks));
		dto.setActualTimeTotal(CalculateUtil.calcActualTimeTotal(tasks));

		return dto;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dto.converter.DtoConverter#convert(java.util.List)
	 */
	@Override
	public List<ActivityDto> convert(List<Activity> entities) {
		List<ActivityDto> list = new ArrayList<ActivityDto>();
		for (Activity entity : entities) {
			list.add(convert(entity));
		}
		return list;
	}
}
