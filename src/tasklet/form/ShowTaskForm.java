/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.form;

import org.apache.struts.action.ActionForm;
import tasklet.common.Priority;

/**
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class ShowTaskForm extends ActionForm {

	/** タスクに紐づくアクティビティID */
	private String activityId;

	/** アクティビティ一覧から引き継いだタスク名 */
	private String title;

	/** 初期選択されるプルダウンの要素 */
	private Priority priority;

	/** タスク追加画面の優先度プルダウンの要素 */
	private Priority[] priorityList;

	/**
	 * タスクに紐づくアクティビティIDを取得します。
	 * @return タスクに紐づくアクティビティID
	 */
	public String getActivityId() {
	    return activityId;
	}

	/**
	 * タスクに紐づくアクティビティIDを設定します。
	 * @param activityId タスクに紐づくアクティビティID
	 */
	public void setActivityId(String activityId) {
	    this.activityId = activityId;
	}

	/**
	 * アクティビティ一覧から引き継いだタスク名を取得します。
	 * @return アクティビティ一覧から引き継いだタスク名
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * アクティビティ一覧から引き継いだタスク名を設定します。
	 * @param title アクティビティ一覧から引き継いだタスク名
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 初期選択されるプルダウンの要素を取得します。
	 * @return 初期選択されるプルダウンの要素
	 */
	public Priority getPriority() {
	    return priority;
	}

	/**
	 * 初期選択されるプルダウンの要素を設定します。
	 * @param priority 初期選択されるプルダウンの要素
	 */
	public void setPriority(Priority priority) {
	    this.priority = priority;
	}

	/**
	 * タスク追加画面の優先度プルダウンの要素を取得します。
	 * @return タスク追加画面の優先度プルダウンの要素
	 */
	public Priority[] getPriorityList() {
	    return priorityList;
	}

	/**
	 * タスク追加画面の優先度プルダウンの要素を設定します。
	 * @param priorityList タスク追加画面の優先度プルダウンの要素
	 */
	public void setPriorityList(Priority[] priorityList) {
	    this.priorityList = priorityList;
	}

}
