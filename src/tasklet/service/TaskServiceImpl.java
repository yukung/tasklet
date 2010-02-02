/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.ArrayList;
import java.util.List;

import tasklet.DataAccessException;
import tasklet.TaskletException;
import tasklet.dao.TaskDao;
import tasklet.entity.Memo;
import tasklet.entity.Task;
import tasklet.factory.DaoFactory;

/**
 * タスク関連のビジネスロジックを実行するServiceの実装クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class TaskServiceImpl implements TaskService {

	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/* (非 Javadoc)
	 * @see tasklet.service.TaskService#show(int)
	 */
	public List<Task> show(int activityId) {

		List<Task> tasks = taskDao.findTasksByActivityId(activityId);

		// タスクが1件も登録されていない場合、空のListを返す
		if (tasks == null) {
			tasks = new ArrayList<Task>();
		}
		return tasks;
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#getActivityTitle(int)
	 */
	public String getActivityTitle(int activityId) {
		return taskDao.getActivityTitle(activityId);
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#add(tasklet.entity.Task)
	 */
	public void add(Task task) throws TaskletException {
		// タスク追加処理
		try {
			taskDao.addTasks(task);
		} catch (DataAccessException e) {
			// DB登録エラー
			throw new TaskletException("errors.insert", e);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#getTask(int)
	 */
	public Task getTask(int taskId) {

		return taskDao.getTaskDetailAndMemos(taskId);

	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#update(tasklet.entity.Task, tasklet.entity.Memo)
	 */
	public void update(Task task, Memo memo) throws TaskletException {

		try {
			// タスク実績時間の加算
			double accumulatedTime = taskDao.getActualTimeByTaskId(task.getId());
			accumulatedTime += task.getActualTime();
			task.setActualTime(accumulatedTime);

			// タスク更新処理
			taskDao.updateTask(task);

			// メモ欄の入力があった場合はmemosテーブルに登録処理
			if (!memo.getContents().equals("")) {
				// ソート順の取得
				Integer seq = taskDao.getMaxSequenceOfMemos(task.getId());
				if (seq == null) {
					seq = Integer.valueOf(0);
				} else {
					seq = seq.intValue() + 1;
				}
				memo.setSeq(seq);

				taskDao.addMemos(memo);
			}
		} catch (DataAccessException e) {
			// DB更新エラー
			throw new TaskletException("errors.insert", e);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#getActivityId(int)
	 */
	public int getActivityId(int taskId) {
		return taskDao.getActivityIdByTaskId(taskId);
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#modify(tasklet.entity.Task)
	 */
	public void modify(Task task) throws TaskletException {
		// タスク修正処理
		try {
			taskDao.modifyTask(task);
		} catch (DataAccessException e) {
			// DB登録エラー
			throw new TaskletException("errors.update", e);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#getMemos(int)
	 */
	public List<Memo> getMemos(int taskId) {
		// TODO 自動生成されたメソッド・スタブ
		return taskDao.getMemosByTaskId(taskId);
	}

}
