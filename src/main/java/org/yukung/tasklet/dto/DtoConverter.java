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
package org.yukung.tasklet.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.util.CalculateUtil;

/**
 * Entityを画面表示用DTOに変換する処理を行うクラスです。
 * 
 * @author yukung
 * 
 */
public class DtoConverter {

	/**
	 * <p>
	 * アクティビティEntityから，アクティビティ一覧画面DTOへの変換を行います。
	 * </p>
	 * <p>
	 * アクティビティEntityには，タスクEntityとカテゴリEntityの参照を持たせておく必要があります。
	 * </p>
	 * 
	 * @param activity
	 * @return アクティビティ一覧画面DTO
	 */
	public static ActivityDto convert(Activity activity) {
		ActivityDto dto = new ActivityDto();
		// アクティビティ情報のコンバート
		try {
			BeanUtils.copyProperties(dto, activity);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		Category category = activity.getCategory();
		List<Task> tasks = activity.getTasks();
		// カテゴリ情報とタスク情報が取得できない場合は、アクティビティ情報のみコンバートして返す
		if (category == null || tasks == null) {
			return dto;
		}

		// カテゴリ情報のコンバート
		dto.setCategoryName(category.getName());

		// 以下，導出プロパティの取得
		dto.setAchievementRatio(CalculateUtil.calcAchievementRatio(tasks));
		dto.setRemainingAmount(CalculateUtil.calcRemainingAmount(tasks));
		dto.setOverdue(CalculateUtil.calcOverdue(tasks));
		dto.setRatioOfEstimateAndActual(CalculateUtil
				.calcRatioOfEstimateAndActual(tasks));
		dto.setEstimatedTimeTotal(CalculateUtil.calcEstimatedTimeTotal(tasks));
		dto.setActualTimeTotal(CalculateUtil.calcActualTimeTotal(tasks));

		// タスク一覧画面へ受け渡すパラメータの編集
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("activityId", Integer.toString(activity.getId()));
		params.put("title", activity.getTitle());
		dto.setParams(params);

		return dto;
	}

	/**
	 * <p>
	 * Entityのリスト → DTOのListへの変換を行います。
	 * </p>
	 * 
	 * @param <S>
	 *            変換元のEntityの型 (src)
	 * @param <D>
	 *            変換先のDTOの型 (dest)
	 * @param src
	 *            変換元のEntityのList
	 * @return 変換先のDTOのList
	 */
	public static <S, D> List<D> convert(List<S> src) {
		List<D> list = new ArrayList<D>();
		for (S s : src) {
			list.add(convert(s));
		}
		return list;
	}
}
