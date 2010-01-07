/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.util.List;

import tasklet.common.TaskletException;
import tasklet.entity.Activity;

/**
 * アクティビティ情報DAOインタフェースです。
 *
 * @author Y.Ikeda
 *
 */
public interface ActivityDao {

	/**
	 * ユーザIDをキーにアクティビティ情報エンティティを取得します。
	 *
	 * @param userId
	 * @return アクティビティ一覧を格納した配列
	 */
	public List<Activity> findActivitiesByUserId(int userId);

	/**
	 * ユーザIDをキーにアクティビティの最新順を取得します。
	 *
	 * @param userId
	 * @return ユーザIDに紐づいたアクティビティの最新順を取得します。
	 */
	public Integer getMaxSequenceOfActivities(int userId);

	/**
	 * 画面から入力されたアクティビティ情報を元にアクティビティテーブルへ追加を行います。
	 *
	 * @param activity
	 */
	public void addActivities(Activity activity);

	/**
	 * 画面から入力されたアクティビティ情報を元にインデックステーブルへ追加を行います。
	 * @param userId
	 * @param categoryId
	 * @param activityId
	 */
	public void addIndexes(int userId, int categoryId, int activityId);

	/**
	 * アクティビティIDの最大値を取得します。
	 * @param activity
	 * @return アクティビティIDの最大値
	 * @throws TaskletException データベース整合性エラー
	 */
	public int getLastActivityId(Activity activity) throws TaskletException;

	/**
	 * 引数のユーザIDに紐づくカテゴリIDの最大値を取得します。
	 * @param userId
	 * @return カテゴリIDの最大値
	 * @throws TaskletException データベース整合性エラー
	 */
	public int getLastCategoryId(int userId) throws TaskletException;

}
