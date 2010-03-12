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
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.MemoDao;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * メモ情報DAOインタフェースの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class MemoDaoImpl extends AbstractDao implements MemoDao {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public MemoDaoImpl() {
		super();
	}

	/**
	 * <p>
	 * コンストラクタ。DataSource経由での接続はこちらを使用します。
	 * </p>
	 * 
	 * @param ds
	 */
	public MemoDaoImpl(DataSource ds) {
		super(ds);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.MemoDao#getMemosByTaskId(int)
	 */
	@Override
	public List<Memo> getMemosByTaskId(int taskId) {
		String sql = getSQLFromPropertyFile("getMemosByTaskId");
		ResultSetHandler<List<Memo>> rsh = new BeanListHandler<Memo>(Memo.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(taskId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}
}
