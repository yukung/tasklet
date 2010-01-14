/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.common;

/**
 * タスクの優先度を定義する列挙型です。
 *
 * @author Y.Ikeda
 *
 */
public enum Priority {

	HIGH(1,"高"),
	NORMAL(2,"普通"),
	LOW(3,"低"),
	NOTHING(9,"なし");

	Priority priority;
	int code;
	String priorityName;

	private Priority(int code, String priorityName) {
		this.code = code;
		this.priorityName = priorityName;
	}

	public Priority getPriority() {
		return this.priority;
	}

	public int getCode() {
		return code;
	}

	public String getPriorityName() {
		return priorityName;
	}

}
