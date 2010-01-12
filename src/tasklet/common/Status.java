/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.common;

/**
 * タスクの状態を定義する列挙型です。
 *
 * @author Y.Ikeda
 *
 */
public enum Status {

	NOT_STARTED(0,"未着手"),
	PROCEED(1,"着手"),
	FINISH(2,"完了");

	int code;
	String statusName;

	private Status(int code, String statusName) {
		this.code = code;
		this.statusName = statusName;
	}

	public int getCode() {
		return code;
	}

	public String getStatusName() {
		return statusName;
	}
}
