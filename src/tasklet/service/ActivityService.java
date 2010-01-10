/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.List;


import tasklet.common.TaskletException;
import tasklet.entity.Activity;

/**
 * アクティビティ関連のビジネスロジックを規定するインタフェースです。
 *
 * @author Y.Ikeda
 *
 */
public interface ActivityService {

	/**
	 * ユーザIDに紐づいたアクティビティ一覧を取得します。
	 *
	 * @param userId
	 * @param offset 取得開始位置
	 * @param limit 最大取得件数
	 * @return アクティビティ一覧を格納したList
	 */
	public List<Activity> show(int userId, int offset, int limit);

	/**
	 * 新規アクティビティ追加時のパラメータをセットします。
	 *
	 * @param activity
	 * @param userId
	 * @return 新規アクティビティ追加時のパラメータをセットしたActivityオブジェクト
	 */
	public Activity setAcitivity(Activity activity, int userId);

	/**
	 * 新規アクティビティを追加します。
	 *
	 * @param activity
	 * @param userId
	 * @throws TaskletException アクティビティ登録時のエラー
	 */
	public void add(Activity activity, int userId) throws TaskletException;

	/**
	 * ユーザIDに紐づいたアクティビティの件数を取得します。
	 *
	 * @param userId
	 * @return そのユーザが持つアクティビティの件数
	 */
	public long getCount(int userId);

}
