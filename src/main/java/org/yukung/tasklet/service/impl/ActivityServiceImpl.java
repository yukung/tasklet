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
package org.yukung.tasklet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.dao.CategoryDao;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.SortableDto;
import org.yukung.tasklet.dto.converter.DtoConverter;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.ActivityFactory;
import org.yukung.tasklet.factory.ConverterFactory;
import org.yukung.tasklet.factory.DaoFactory;
import org.yukung.tasklet.logic.ActivityTxLogic;
import org.yukung.tasklet.service.ActivityService;

/**
 * <p>
 * アクティビティ関連のビジネスロジックを実行するServiceの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityServiceImpl implements ActivityService {

	/** アクティビティ情報DAO */
	private ActivityDao activityDao = DaoFactory.getInstance()
			.createActivityDao();

	/** カテゴリ情報DAO */
	private CategoryDao categoryDao = DaoFactory.getInstance()
			.createCategoryDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#getCount(int)
	 */
	@Override
	public long getCount(int userId) {
		return activityDao.getCountByUserId(userId).longValue();
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#show(int, int, int)
	 */
	@Override
	public List<ActivityDto> show(int userId, int limit, int offset) {
		ActivityFactory activityFactory = new ActivityFactory();
		List<Activity> activities = activityFactory.getActivitiesByUserId(
				userId, limit, offset);

		// アクティビティが1件も登録されていない場合、空のListを返す
		if (activities == null) {
			return new ArrayList<ActivityDto>();
		}
		DtoConverter<Activity, ActivityDto> converter = ConverterFactory
				.createDtoConverter(ActivityDto.class);
		return converter.convert(activities);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.ActivityService#add(org.yukung.tasklet.entity
	 * .Activity, int)
	 */
	@Override
	public void add(Activity activity, int userId) throws TaskletException {
		// アクティビティのソート順を取得
		int seq = getSeq(userId);
		activity.setSeq(seq);
		// デフォルトカテゴリの取得
		Category category = categoryDao.getDefaultCategory(userId);
		activity.setCategory(category);
		// アクティビティ追加
		ActivityTxLogic tx = new ActivityTxLogic(activityDao);
		tx.add(activity, userId);
	}

	/**
	 * <p>
	 * ユーザIDに紐づくアクティビティの最新順を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @return アクティビティのソート順最大値
	 */
	private int getSeq(int userId) {
		Integer count = activityDao.getMaxSeqOfActivities(userId);
		if (count == null) {
			return 0;
		}
		return count.intValue() + 1;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#ascend(int)
	 */
	@Override
	public void ascend(int userId) throws TaskletException {
		ActivityTxLogic tx = new ActivityTxLogic(activityDao);
		tx.ascend(userId);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#descend(int)
	 */
	@Override
	public void descend(int userId) throws TaskletException {
		ActivityTxLogic tx = new ActivityTxLogic(activityDao);
		tx.descend(userId);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#getSortableInfo(int)
	 */
	@Override
	public List<SortableDto> getSortableInfo(int userId) {
		List<Activity> activities = activityDao.getSortableInfo(userId);

		if (activities == null) {
			return new ArrayList<SortableDto>();
		}
		DtoConverter<Activity, SortableDto> converter = ConverterFactory
				.createDtoConverter(SortableDto.class);
		return converter.convert(activities);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#sort(java.util.List, int)
	 */
	@Override
	public void sort(List<Activity> sortedActivities, int userId)
			throws TaskletException {
		long count = activityDao.getCountByUserId(userId).longValue();
		// 渡されてきたアクティビティEntity（アクティビティID）の数と、
		// 実際にDBに格納されているアクティビティの数が一致しなかった場合はアプリ例外
		if (count != sortedActivities.size()) {
			throw new TaskletException("errors.mismatch");
		}
		ActivityTxLogic tx = new ActivityTxLogic(activityDao);
		tx.sort(sortedActivities);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.ActivityService#addCategory(org.yukung.tasklet
	 * .entity.Category)
	 */
	@Override
	public void addCategory(Category category) throws TaskletException {
		categoryDao.add(category);
	}
}
