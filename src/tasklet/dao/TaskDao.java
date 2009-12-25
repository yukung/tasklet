/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.util.List;

import tasklet.entity.Task;

/**
 * タスク情報DAOインタフェースです。
 * 
 * @author Y.Ikeda
 * 
 */
public interface TaskDao {

	/**
	 * アクティビティIDをキーにタスク情報エンティティを取得します。
	 * 
	 * @param activityId
	 * @return タスク情報を格納したList
	 */
	public List<Task> findTasksByActivityId(int activityId);
}
