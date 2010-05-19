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
package org.yukung.tasklet.service;

import java.util.List;
import java.util.Map;

import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.SortableDto;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * アクティビティ関連のビジネスロジックを規定するインタフェースです。
 * </p>
 * 
 * @author yukung
 * 
 */
public interface ActivityService {

	/**
	 * <p>
	 * ユーザIDに紐づいたアクティビティの件数を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @return ユーザが持つアクティビティの件数
	 */
	public long getCount(int userId);

	/**
	 * <p>
	 * ユーザIDに紐づいたアクティビティ一覧を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @param limit
	 *            取得開始ページ
	 * @param offset
	 *            最大取得件数
	 * @return アクティビティ画面表示DTOを格納したList
	 */
	public List<ActivityDto> show(int userId, int limit, int offset);

	/**
	 * <p>
	 * 新規アクティビティを追加します。
	 * </p>
	 * 
	 * @param activity
	 *            アクティビティ情報Entity
	 * @param userId
	 *            ユーザID
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void add(Activity activity, int userId) throws TaskletException;

	/**
	 * <p>
	 * アクティビティを昇順に並び替えます。
	 * </p>
	 * 
	 * @param userId
	 *            ユーザID
	 * @exception TaskletException
	 *                DB更新時のエラー
	 */
	public void ascend(int userId) throws TaskletException;

	/**
	 * <p>
	 * アクティビティを降順に並び替えます。
	 * </p>
	 * 
	 * @param userId
	 *            ユーザID
	 * @exception TaskletException
	 *                DB更新時のエラー
	 */
	public void descend(int userId) throws TaskletException;

	/**
	 * <p>
	 * アクティビティを並び替えるための情報を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
	public List<SortableDto> getSortableInfo(int userId);

	/**
	 * <p>
	 * 指定された順番でアクティビティをソートします。
	 * </p>
	 * 
	 * @param sortedActivities
	 *            ソート後のアクティビティEntityを格納したList
	 * @param userId
	 *            ユーザID
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void sort(List<Activity> sortedActivities, int userId)
			throws TaskletException;

	/**
	 * <p>
	 * カテゴリを追加します。
	 * </p>
	 * 
	 * @param category
	 *            カテゴリ情報Entity
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void addCategory(Category category) throws TaskletException;

	/**
	 * <p>
	 * カテゴリ一覧を取得します。
	 * </p>
	 * 
	 * @param userId
	 *            ユーザID
	 * @return カテゴリ一覧情報を持ったMap
	 */
	public Map<String, String> getCategories(int userId);

	/**
	 * <p>
	 * カテゴリを更新します。
	 * </p>
	 * 
	 * @param category
	 *            カテゴリ情報Entity
	 * @exception TaskletException
	 *                DB更新時のエラー
	 */
	public void updateCategory(Category category) throws TaskletException;

	/**
	 * <p>
	 * アクティビティIDに紐づいたカテゴリ情報を取得します。
	 * </p>
	 * 
	 * @param activityId
	 *            アクティビティID
	 * @return カテゴリ情報Entity
	 */
	public Category getCategory(int activityId);

	/**
	 * <p>
	 * カテゴリ「未分類」を含めてカテゴリ一覧を取得します。
	 * </p>
	 * 
	 * @param userId
	 *            ユーザID
	 * @return カテゴリ一覧情報を持ったMap
	 */
	public Map<String, String> getCategoriesWithUncategorized(int userId);

	/**
	 * <p>
	 * アクティビティ情報を修正します。
	 * </p>
	 * 
	 * @param activity
	 *            アクティビティ情報Entity
	 * @param category
	 *            カテゴリ情報Entity
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void modify(Activity activity, Category category)
			throws TaskletException;

	/**
	 * <p>
	 * カテゴリを削除します。
	 * </p>
	 * 
	 * @param category
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void deleteCategory(Category category) throws TaskletException;

}
