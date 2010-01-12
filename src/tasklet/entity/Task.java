/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

import java.util.Date;
import java.util.EnumSet;

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

	/** 作成タイムスタンプ */
	private Date createdOn;

	/** 更新タイムスタンプ */
	private Date updatedOn;

	/**
	 * IDを取得します。
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * IDを設定します。
	 * @param id ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 紐づくアクティビティIDを取得します。
	 * @return 紐づくアクティビティID
	 */
	public int getActivityId() {
		return activityId;
	}

	/**
	 * 紐づくアクティビティIDを設定します。
	 * @param activityId 紐づくアクティビティID
	 */
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/**
	 * タスク名を取得します。
	 * @return タスク名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タスク名を設定します。
	 * @param title タスク名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 優先度を取得します。
	 * @return 優先度
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * 優先度を設定します。
	 * @param priority 優先度
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * 優先度コードを取得します。
	 * @return 優先度コード
	 */
	public int getPriorityCode() {
		return priority.getCode();
	}

	/**
	 * 優先度コードから優先度を設定します。
	 * @param code 優先度コード
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
	 * @return ステータス
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * ステータスを設定します。
	 * @param status ステータス
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * ステータスコードを取得します。
	 * @return ステータスコード
	 */
	public int getStatusCode() {
		return status.getCode();
	}

	/**
	 * ステータスコードからステータスを設定します。
	 * @param code ステータスコード
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
	 * @return 期限
	 */
	public Date getPeriod() {
		return period;
	}

	/**
	 * 期限を設定します。
	 * @param period 期限
	 */
	public void setPeriod(Date period) {
		this.period = period;
	}

	/**
	 * 終了日を取得します。
	 * @return 終了日
	 */
	public Date getFinishedOn() {
		return finishedOn;
	}

	/**
	 * 終了日を設定します。
	 * @param finishedOn 終了日
	 */
	public void setFinishedOn(Date finishedOn) {
		this.finishedOn = finishedOn;
	}

	/**
	 * 見積時間を取得します。
	 * @return 見積時間
	 */
	public double getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 * @param estimatedTime 見積時間
	 */
	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * 実績時間を取得します。
	 * @return 実績時間
	 */
	public double getActualTime() {
		return actualTime;
	}

	/**
	 * 実績時間を設定します。
	 * @param actualTime 実績時間
	 */
	public void setActualTime(double actualTime) {
		this.actualTime = actualTime;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 * @return 作成タイムスタンプ
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 * @param createdOn 作成タイムスタンプ
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 * @return 更新タイムスタンプ
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 * @param updatedOn 更新タイムスタンプ
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
