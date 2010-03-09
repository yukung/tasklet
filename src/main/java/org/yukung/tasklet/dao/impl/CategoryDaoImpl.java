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

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.CategoryDao;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * カテゴリ情報DAOインタフェースの実装クラスです。
 * <p>
 * 
 * @author yukung
 * 
 */
public class CategoryDaoImpl extends AbstractDao implements CategoryDao {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public CategoryDaoImpl() {
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
	public CategoryDaoImpl(DataSource ds) {
		super(ds);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.CategoryDao#addDefaultCategory(int)
	 */
	@Override
	public void addDefaultCategory(int userId) {
		String sql = getSQLFromPropertyFile("addDefaultCategory");
		try {
			runner.update(sql, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.CategoryDao#findCategoryByActivityId(int)
	 */
	@Override
	public Category findCategoryByActivityId(int activityId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = getSQLFromPropertyFile("findCategoryByActivityId");
		ResultSetHandler<Category> rsh = new BeanHandler<Category>(
				Category.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(activityId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

}
