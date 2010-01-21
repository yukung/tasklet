/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.common;

/**
 * アプリ共通の定数クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class Constants {

	public static final String PROPERTY_KEY_SQL = "sqltable.";

	public static final int ACTIVITIES_MAX_LIMIT = 10;

	// タスク一覧はページングしない
//	public static final int TASKS_MAX_LIMIT = 10;

	/** 1日の秒数 : 1000(ms) * 60(s) * 60(m) * 24(h) = 86400000 */
	public static final long SECONDS_OF_DAY = 86400000;

}
