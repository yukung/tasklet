/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

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
	 * @return アクティビティ一覧を格納した配列
	 */
	public Activity[] show(int userId);

}
