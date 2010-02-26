/**
 * Copyright 2009-2010 Yusuke Ikeda. (@yukung) <yukung.i@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.yukung.tasklet.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * アクティビティ情報DAOインタフェースの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityDaoImpl extends AbstractDao implements ActivityDao {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public ActivityDaoImpl() {
		super();
	}

	/**
	 * <p>
	 * コンストラクタ。DataSource経由での接続はこちらを使用します。
	 * </p>
	 * 
	 * @param ds
	 *            データソース
	 */
	public ActivityDaoImpl(DataSource ds) {
		super(ds);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getCountByUserId(int)
	 */
	@Override
	public long getCountByUserId(int userId) {
		String sql = getSQLFromPropertyFile("getCountByUserId");
		ResultSetHandler<Object> rsh = new ScalarHandler(1);
		try {
			return ((Long) runner.query(sql, rsh, Integer.valueOf(userId)))
					.longValue();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#findActivitiesByUserId(int, int,
	 * int)
	 */
	@Override
	public List<Activity> findActivitiesByUserId(int userId, int offset,
			int limit) {
		String sql = getSQLFromPropertyFile("findActivitiesByUserId");
		ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();
		return runner.query(sql, rsh, params, param);
	}
}
