/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.List;


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
	 * @return アクティビティ一覧を格納したList
	 */
	public List<Activity> show(int userId);

	/**
	 * 新規アクティビティ追加時のパラメータをセットします。
	 *
	 * @param activity
	 * @param userId
	 * @return 新規アクティビティ追加時のパラメータをセットしたActivityオブジェクト
	 */
	public Activity setAcitivity(Activity activity, String userId);

	/**
	 * 新規アクティビティを追加します。
	 *
	 * @param activity
	 */
	public void add(Activity activity);

}
