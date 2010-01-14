/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class AddTaskForm extends ValidatorForm {

	/** タスクに紐づくアクティビティId */
	private String activityId;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private String priority;

	/** 期限 */
	private String period;

	/** 見積時間 */
	private String estimateTime;

	/**
	 * タスクに紐づくアクティビティIdを取得します。
	 * @return タスクに紐づくアクティビティId
	 */
	public String getActivityId() {
	    return activityId;
	}

	/**
	 * タスクに紐づくアクティビティIdを設定します。
	 * @param activityId タスクに紐づくアクティビティId
	 */
	public void setActivityId(String activityId) {
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
	public String getPriority() {
	    return priority;
	}

	/**
	 * 優先度を設定します。
	 * @param priority 優先度
	 */
	public void setPriority(String priority) {
	    this.priority = priority;
	}

	/**
	 * 期限を取得します。
	 * @return 期限
	 */
	public String getPeriod() {
	    return period;
	}

	/**
	 * 期限を設定します。
	 * @param period 期限
	 */
	public void setPeriod(String period) {
	    this.period = period;
	}

	/**
	 * 見積時間を取得します。
	 * @return 見積時間
	 */
	public String getEstimateTime() {
	    return estimateTime;
	}

	/**
	 * 見積時間を設定します。
	 * @param estimateTime 見積時間
	 */
	public void setEstimateTime(String estimateTime) {
	    this.estimateTime = estimateTime;
	}

}
