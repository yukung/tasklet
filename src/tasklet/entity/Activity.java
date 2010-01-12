/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tasklet.common.Status;

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
		double complete = 0.0;
		int amount = tasks.size();
		for (Task task : tasks) {
			if (task.getStatus() == Status.FINISH) {
				complete++;
			}
		}
		double ratio = (complete / (double)amount);
		if (Double.isNaN(ratio)) {
			ratio = 0.0;
		}
		NumberFormat percentInstance = NumberFormat.getPercentInstance();
		return percentInstance.format(ratio);
	}

	/**
	 * タスク残数を取得します。
	 * @return タスク残数
	 */
	public String getRemaining() {
		int complete = 0;
		int amount = tasks.size();
		for (Task task : tasks) {
			if (task.getStatus() == Status.FINISH) {
				complete++;
			}
		}
		return Integer.toString(amount - complete);
	}

	/**
	 * 期限超過タスク数を取得します。
	 * @return 期限超過タスク数
	 */
	public String getOverdue() {
		int amount = 0;
		Calendar today = Calendar.getInstance();
		for (Task task : tasks) {
			// 期限設定なしのタスクはカウントしない
			Date dateOfPeriod = task.getPeriod();
			if (dateOfPeriod == null) {
				continue;
			}
			// 完了タスクはカウントしない
			if (task.getStatus() == Status.FINISH) {
				continue;
			}
			Calendar period = Calendar.getInstance();
			period.setTime(dateOfPeriod);

			// Calendar#compareTo(anotherCalendar)メソッドは、ミリ秒単位の比較を行うため、
			// 期日と今日の日付が同じ日の場合は期日オーバーと判定されてしまう。
			// （期日はその日の午前0時であるため）
			// これを防ぐため、期限を1日ずらすことで今日が期限のタスクを期限オーバーに含めないようにする。
			period.add(Calendar.DAY_OF_MONTH, 1);

			int result = today.compareTo(period);
			if (result == 1) {
				// 戻り値が -1,0 は期限内なので除外
				amount++;
			}
		}
	    return Integer.toString(amount);
	}

	/**
	 * 予実比を取得します。
	 * @return 予実比
	 */
	public String getRatioOfEstimateAndActual() {
		double estimateTime = 0.0;
		double actualTime = 0.0;
		for (Task task : tasks) {
			estimateTime += task.getEstimatedTime();
			actualTime += task.getActualTime();
		}
		double ratio = actualTime / estimateTime;
		if (Double.isNaN(ratio)) {
			ratio = 0.0;
		}
		NumberFormat percentInstance = NumberFormat.getPercentInstance();
	    return percentInstance.format(ratio);
	}

	/**
	 * 見積時間合計を取得します。
	 * @return 見積時間合計
	 */
	public String getEstimatedTimeTotal() {
	    double estimatedTimeTotal = 0.0;
	    for (Task task : tasks) {
			estimatedTimeTotal += task.getEstimatedTime();
		}
	    return Double.toString(estimatedTimeTotal);
	}

	/**
	 * 実績時間合計を取得します。
	 * @return 実績時間合計
	 */
	public String getActualTimeTotal() {
		double actualTimeTotal = 0.0;
		for (Task task : tasks) {
			actualTimeTotal += task.getActualTime();
		}
		return Double.toString(actualTimeTotal);
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