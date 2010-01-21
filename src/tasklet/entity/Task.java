/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

import static tasklet.common.Constants.SECONDS_OF_DAY;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;

import tasklet.common.Priority;
import tasklet.common.Status;

/**
 * タスク情報を表すBeanクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class Task {

	/** ID */
	private int id;

	/** 紐づくアクティビティID */
	private int activityId;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** ステータス */
	private Status status;

	/** 期限 */
	private Date period;

	/** 終了日 */
	private Date finishedOn;

	/** 見積時間 */
	private double estimatedTime;

	/** 実績時間 */
	private double actualTime;

	/** タスクメモの数 */
	private int memoCount;

	/** メモ一覧 */
	private List<Memo> memos;

	/** 作成タイムスタンプ */
	private Timestamp createdOn;

	/** 更新タイムスタンプ */
	private Timestamp updatedOn;


	/**
	 * IDを取得します。
	 *
	 * @return ID
	 */
	public int getId() {
		return id;
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
	 * 紐づくアクティビティIDを取得します。
	 *
	 * @return 紐づくアクティビティID
	 */
	public int getActivityId() {
		return activityId;
	}

	/**
	 * 紐づくアクティビティIDを設定します。
	 *
	 * @param activityId
	 *            紐づくアクティビティID
	 */
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/**
	 * タスク名を取得します。
	 *
	 * @return タスク名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タスク名を設定します。
	 *
	 * @param title
	 *            タスク名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 優先度を取得します。
	 *
	 * @return 優先度
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * 優先度を設定します。
	 *
	 * @param priority
	 *            優先度
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * 優先度コードから優先度を設定します。
	 *
	 * @param code
	 *            優先度コード
	 */
	public void setPriorityFromCode(int code) {
		for (Priority priority : EnumSet.allOf(Priority.class)) {
			if (priority.getCode() == code) {
				this.priority = priority;
				return;
			}
		}
		priority = null;
	}

	/**
	 * ステータスを取得します。
	 *
	 * @return ステータス
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * ステータスを設定します。
	 *
	 * @param status
	 *            ステータス
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * ステータスコードを取得します。
	 *
	 * @return ステータスコード
	 */
	public int getStatusCode() {
		return status.getCode();
	}

	/**
	 * ステータスコードからステータスを設定します。
	 *
	 * @param code
	 *            ステータスコード
	 */
	public void setStatusFromCode(int code) {
		for (Status status : EnumSet.allOf(Status.class)) {
			if (status.getCode() == code) {
				this.status = status;
				return;
			}
		}
		status = null;
	}

	/**
	 * 期限を取得します。
	 *
	 * @return 期限
	 */
	public Date getPeriod() {
		return period;
	}

	/**
	 * 期限を設定します。
	 *
	 * @param period
	 *            期限
	 */
	public void setPeriod(Date period) {
		this.period = period;
	}

	/**
	 * 期限オーバーかどうかを返します。
	 *
	 * @return 期限が今日よりも前の場合は true<br>
	 *         期限が今日を含めて後の場合は false
	 */
	public boolean isOverdue() {
		if (getPeriod() == null) {
			return false;
		}
		Calendar today = Calendar.getInstance();
		Calendar period = Calendar.getInstance();
		period.setTime(getPeriod());

		// Calendar#compareTo(anotherCalendar)メソッドは、ミリ秒単位の比較を行うため、
		// 期日と今日の日付が同じ日の場合は期日オーバーと判定されてしまう。
		// （期日はその日の午前0時で表されるため）
		// これを防ぐため、期限を1日ずらすことで今日が期限のタスクを期限オーバーに含めないようにする。
		period.add(Calendar.DAY_OF_MONTH, 1);

		int result = today.compareTo(period);
		// 戻り値が -1,0 は期限内
		return result == 1 ? true : false;
	}

	/**
	 * タスク期限までの残り日数を返します。
	 *
	 * @return 残り日数<br>
	 * 期限が設定されていない場合は0を返す
	 */
	public long getDaysRemaining() {
		if (getPeriod() == null) {
			return 0;
		}
		Calendar today = Calendar.getInstance();
		Calendar period = Calendar.getInstance();
		period.setTime(getPeriod());
		long days = (period.getTimeInMillis() - today.getTimeInMillis())
				/ SECONDS_OF_DAY;
		days += 1; // 締め切り当日も猶予日とするため、1日足す

		return days;

	}

	/**
	 * 終了日を取得します。
	 *
	 * @return 終了日
	 */
	public Date getFinishedOn() {
		return finishedOn;
	}

	/**
	 * 終了日を設定します。
	 *
	 * @param finishedOn
	 *            終了日
	 */
	public void setFinishedOn(Date finishedOn) {
		this.finishedOn = finishedOn;
	}

	/**
	 * 見積時間を取得します。
	 *
	 * @return 見積時間
	 */
	public double getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 *
	 * @param estimatedTime
	 *            見積時間
	 */
	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * 実績時間を取得します。
	 *
	 * @return 実績時間
	 */
	public double getActualTime() {
		return actualTime;
	}

	/**
	 * 実績時間を設定します。
	 *
	 * @param actualTime
	 *            実績時間
	 */
	public void setActualTime(double actualTime) {
		this.actualTime = actualTime;
	}

	/**
	 * タスクメモの数を取得します。
	 *
	 * @return タスクメモの数
	 */
	public int getMemoCount() {
		return memoCount;
	}

	/**
	 * タスクメモの数を設定します。
	 *
	 * @param memoCount
	 *            タスクメモの数
	 */
	public void setMemoCount(int memoCount) {
		this.memoCount = memoCount;
	}

	/**
	 * メモ一覧をListで取得します。
	 *
	 * @return メモ一覧（List）
	 */
	public List<Memo> getAllMemos() {
	    return memos;
	}

	/**
	 * メモ一覧を配列で取得します。
	 *
	 * @return メモ一覧（配列）
	 */
	public Memo[] getAllMemosArray() {
		Memo[] allMemos = new Memo[memos.size()];
		memos.toArray(allMemos);

		return allMemos;
	}

	/**
	 * メモIDを指定してメモを取得します。
	 *
	 * @param memoId
	 * @return メモIDが一致したメモを返す。<br>
	 *         メモIDが一致しない場合はnullを返す。
	 */
	public Memo getMemo(int memoId) {
		Memo memo = null;
		for (int i = 0; i < memos.size(); i++) {
			memo = memos.get(i);
			if (id == memo.getId()) {
				return memo;
			}
		}
		return null;
	}

	/**
	 * メモ一覧を設定します。
	 * @param memos メモ一覧
	 */
	public void setMemos(List<Memo> memos) {
	    this.memos = memos;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 *
	 * @return 作成タイムスタンプ
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 *
	 * @param createdOn
	 *            作成タイムスタンプ
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 *
	 * @return 更新タイムスタンプ
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 *
	 * @param updatedOn
	 *            更新タイムスタンプ
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
}
