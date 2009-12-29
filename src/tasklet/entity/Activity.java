/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tasklet.Status;

/**
 * アクティビティ情報を表すBeanクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class Activity {

	/** ID */
	private int id;

	/** タイトル */
	private String title;

	/** 説明 */
	private String description;

	/** ソート順 */
	private int seq;

	/** カテゴリ */
	private String category;

	/** 未完了フラグ */
	private boolean isIncomplete;

	/** タスク一覧 */
	private List<Task> tasks;

	/** タスク残数 */
	private String remaining;

	/** 期限超過タスク数 */
	private String excess;

	/** 予実比 */
	private String ratioOfEstimateAndActual;

	/** 見積時間合計 */
	private String estimatedTimeTotal;

	/** 実績時間合計 */
	private String actualTimeTotal;

	/** 作成タイムスタンプ */
	private Date createdOn;

	/** 更新タイムスタンプ */
	private Date updatedOn;

	/**
	 * IDを取得します。
	 *
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 *
	 */
	public Activity() {
		tasks = new ArrayList<Task>();
	}

	/**
	 * IDを設定します。
	 *
	 * @param id
	 *            ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * タイトルを取得します。
	 *
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトルを設定します。
	 *
	 * @param title
	 *            タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 説明を取得します。
	 *
	 * @return 説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明を設定します。
	 *
	 * @param description
	 *            説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ソート順を取得します。
	 *
	 * @return ソート順
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * ソート順を設定します。
	 *
	 * @param seq
	 *            ソート順
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * カテゴリを取得します。
	 *
	 * @return カテゴリ
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * カテゴリを設定します。
	 *
	 * @param category
	 *            カテゴリ
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 未完了フラグを取得します。
	 *
	 * @return 未完了フラグ
	 */
	public boolean isIncomplete() {
		return isIncomplete;
	}

	/**
	 * 未完了フラグを設定します。
	 *
	 * @param isIncomplete
	 *            未完了フラグ
	 */
	public void setIncomplete(boolean isIncomplete) {
		this.isIncomplete = isIncomplete;
	}

	/**
	 * タスク一覧をListで取得します。
	 *
	 * @return タスク一覧（List)
	 */
	public List<Task> getAllTasks() {
		return tasks;
	}

	/**
	 * タスク一覧を配列で取得します。
	 *
	 * @return タスク一覧（配列）
	 */
	public Task[] getAllTasksArray() {
		Task[] allTasks = new Task[tasks.size()];
		tasks.toArray(allTasks);

		return allTasks;
	}

	/**
	 * タスクIDを指定してタスクを取得します。
	 *
	 * @param taskId
	 * @return タスクIDが一致したタスクを返す。<br>
	 *         タスクIDが一致しない場合はnullを返す。
	 */
	public Task getTask(int taskId) {
		Task task = null;
		for (int i = 0; i < tasks.size(); i++) {
			task = tasks.get(i);
			if (id == task.getId()) {
				return task;
			}
		}
		return null;
	}

	/**
	 * タスク一覧を設定します。
	 *
	 * @param tasks
	 *            タスク一覧
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * 達成率を取得します。
	 * @return 達成率
	 */
	public String getAchievementRatio() {
		double complete = 0;
		int amount = tasks.size();
		for (Task task : tasks) {
			if (task.getStatus() == Status.FINISH) {
				complete++;
			}
		}
		double ratio = (complete / (double)amount) * 100;
		return Double.toString(ratio);
	}

	/**
	 * タスク残数を取得します。
	 * @return タスク残数
	 */
	public String getRemaining() {
	    return remaining;
	}

	/**
	 * 期限超過タスク数を取得します。
	 * @return 期限超過タスク数
	 */
	public String getExcess() {
	    return excess;
	}

	/**
	 * 予実比を取得します。
	 * @return 予実比
	 */
	public String getRatioOfEstimateAndActual() {
	    return ratioOfEstimateAndActual;
	}

	/**
	 * 見積時間合計を取得します。
	 * @return 見積時間合計
	 */
	public String getEstimatedTimeTotal() {
	    return estimatedTimeTotal;
	}

	/**
	 * 実績時間合計を取得します。
	 * @return 実績時間合計
	 */
	public String getActualTimeTotal() {
	    return actualTimeTotal;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 *
	 * @return 作成タイムスタンプ
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 *
	 * @param createdOn
	 *            作成タイムスタンプ
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 *
	 * @return 更新タイムスタンプ
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 *
	 * @param updatedOn
	 *            更新タイムスタンプ
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}