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
import java.util.Map;

import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.dao.MemoDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.DetailDto;
import org.yukung.tasklet.dto.MemoDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.dto.converter.DtoConverter;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.ConverterFactory;
import org.yukung.tasklet.factory.DaoFactory;
import org.yukung.tasklet.factory.TaskFactory;
import org.yukung.tasklet.logic.TaskTxLogic;
import org.yukung.tasklet.service.TaskService;

/**
 * <p>
 * タスク関連のビジネスロジックを実行するServiceの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskServiceImpl implements TaskService {

	/** アクティビティ情報DAO */
	private ActivityDao activityDao = DaoFactory.getInstance()
			.createActivityDao();

	/** タスク情報DAO */
	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/** メモ情報DAO */
	private MemoDao memoDao = DaoFactory.getInstance().createMemoDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#show(int)
	 */
	@Override
	public List<TaskDto> show(int activityId) {
		TaskFactory taskFactory = new TaskFactory();
		List<Task> tasks = taskFactory.getTasksByActivityId(activityId);

		// タスクが1件も登録されていない場合、空のListを返す
		if (tasks == null) {
			return new ArrayList<TaskDto>();
		}
		DtoConverter<Task, TaskDto> converter = ConverterFactory
				.createDtoConverter(TaskDto.class);
		return converter.convert(tasks);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#getActivityInfo(int)
	 */
	@Override
	public ActivityDto getActivityInfo(int activityId, int userId)
			throws DataAccessException {
		Activity activity = activityDao.getActivityInfo(activityId);

		if (activity == null) {
			throw new DataAccessException("errors.general");
		}
		DtoConverter<Activity, ActivityDto> converter = ConverterFactory
				.createDtoConverter(ActivityDto.class);
		ActivityDto activityDto = converter.convert(activity);
		List<Map<String, Object>> origin = activityDao
				.getMoreActivities(userId);
		// 現在表示しているアクティビティは除く
		List<Map<String, Object>> dest = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : origin) {
			if (!map.containsValue(Integer.valueOf(activityId))) {
				dest.add(map);
			}
		}
		activityDto.setMoreActivities(dest);
		return activityDto;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.TaskService#add(org.yukung.tasklet.entity.
	 * Task)
	 */
	@Override
	public void add(Task task) throws TaskletException {
		taskDao.addTask(task);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#getTask(int)
	 */
	@Override
	public DetailDto getTask(int taskId) {
		TaskFactory taskFactory = new TaskFactory();
		Task task = taskFactory.getTask(taskId);

		if (task == null) {
			throw new DataAccessException("errors.general");
		}
		DtoConverter<Task, DetailDto> converter = ConverterFactory
				.createDtoConverter(DetailDto.class);
		return converter.convert(task);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#getActivityId(int)
	 */
	@Override
	public int getActivityId(int taskId) {
		return (taskDao.getActivityIdByTaskId(taskId)).intValue();
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.TaskService#update(org.yukung.tasklet.entity
	 * .Task, org.yukung.tasklet.entity.Memo)
	 */
	@Override
	public void update(Task task, Memo memo) throws TaskletException {
		double accumulatedTime = calculateActualTime(task);
		task.setActualTime(accumulatedTime);

		// タスク更新処理
		TaskTxLogic tx = new TaskTxLogic(taskDao, memoDao);
		tx.update(task, memo);

	}

	/**
	 * @param task
	 * @return
	 */
	private double calculateActualTime(Task task) {
		// タスク実績時間の加算
		double accumulatedTime = taskDao.getActualTimeByTaskId(task.getId())
				.doubleValue();
		accumulatedTime += task.getActualTime();
		return accumulatedTime;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#getMemos(int)
	 */
	@Override
	public List<MemoDto> getMemos(int taskId) {
		List<Memo> memos = memoDao.getMemosByTaskId(taskId);

		// メモが1件も登録されていない場合、空のListを返す
		if (memos == null) {
			return new ArrayList<MemoDto>();
		}

		DtoConverter<Memo, MemoDto> converter = ConverterFactory
				.createDtoConverter(MemoDto.class);
		return converter.convert(memos);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.TaskService#add(org.yukung.tasklet.entity.
	 * Memo)
	 */
	@Override
	public void add(Memo memo) throws TaskletException {
		// メモのソート順の取得
		int seq = getSeq(memo.getTaskId());
		memo.setSeq(seq);

		memoDao.addMemoToMemos(memo);
	}

	/**
	 * <p>
	 * タスクIDに紐づくメモの再浸潤を取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return メモのソート順最大値
	 */
	private int getSeq(int taskId) {
		Integer seq = memoDao.getMaxSeqOfMemos(taskId);
		if (seq == null) {
			return 0;
		}
		return seq.intValue() + 1;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.TaskService#modify(org.yukung.tasklet.entity
	 * .Task)
	 */
	@Override
	public void modify(Task task) throws TaskletException {
		taskDao.modifyTask(task);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#complete(int,
	 * java.lang.String[])
	 */
	@Override
	public void complete(int activityId, String[] checked)
			throws TaskletException {
		TaskTxLogic tx = new TaskTxLogic(activityDao, taskDao);
		tx.complete(activityId, checked);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#remove(int,
	 * java.lang.String[])
	 */
	@Override
	public void remove(String[] checked) throws TaskletException {
		TaskTxLogic tx = new TaskTxLogic(taskDao, memoDao);
		tx.remove(checked);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#deleteAll(int)
	 */
	@Override
	public void deleteAll(int activityId) throws TaskletException {
		TaskTxLogic tx = new TaskTxLogic(activityDao, taskDao, memoDao);
		tx.delete(activityId);
	}
}
