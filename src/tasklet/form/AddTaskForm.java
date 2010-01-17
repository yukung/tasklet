/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.form;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import tasklet.common.Priority;
import tasklet.util.DateConverter;
import tasklet.util.EnumConverter;

/**
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class AddTaskForm extends ValidatorForm {

	/** タスクに紐づくアクティビティId */
	private String activityId;

	/** タスクに紐づくアクティビティ名 */
	private String activityTitle;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** 期限 */
	private String period;

	/** 見積時間 */
	private String estimatedTime;

	/**
	 * 文字列 → 列挙型へのコンバータを登録
	 */
	static {
		EnumConverter enumConverter = new EnumConverter();
		ConvertUtils.register(enumConverter, Priority.class);
	}

	/**
	 * 文字列 → Date型へのコンバータを登録
	 */
	static {
		DateConverter dateConverter = new DateConverter();
		ConvertUtils.register(dateConverter, Date.class);
	}

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
	 * タスクに紐づくアクティビティ名を取得します。
	 * @return タスクに紐づくアクティビティ名
	 */
	public String getActivityTitle() {
	    return activityTitle;
	}

	/**
	 * タスクに紐づくアクティビティ名を設定します。
	 * @param activityTitle タスクに紐づくアクティビティ名
	 */
	public void setActivityTitle(String activityTitle) {
	    this.activityTitle = activityTitle;
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
	 * タスク追加画面の優先度プルダウンの要素を取得します。
	 * @return タスク追加画面の優先度プルダウンの要素
	 */
	public Priority[] getPriorities() {
		return Priority.values();
	}

	/**
	 * 優先度を設定します。
	 * @param priority 優先度
	 */
	public void setPriority(Priority priority) {
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
	public String getEstimatedTime() {
	    return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 * @param estimatedTime 見積時間
	 */
	public void setEstimatedTime(String estimatedTime) {
	    this.estimatedTime = estimatedTime;
	}

	/*
	 * (非 Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setPriority(Priority.NOTHING); // 優先度を「なし」に設定
	}

}
