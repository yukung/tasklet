/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.util.List;

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

}
