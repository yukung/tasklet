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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.CategoryDao;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * カテゴリ情報DAOインタフェースの実装クラスです。
 * <p>
 * 
 * @author yukung
 * 
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
	 * @see
	 * org.yukung.tasklet.dao.CategoryDao#addDefaultCategory(java.sql.Connection
	 * , int)
	 */
	@Override
	public void addDefaultCategory(Connection conn, int userId)
			throws SQLException {
		String sql = getSQLFromPropertyFile("addDefaultCategory");
		runner.update(conn, sql, Integer.valueOf(userId));
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.CategoryDao#findCategoryByActivityId(int)
	 */
	@Override
	public Category findCategoryByActivityId(int activityId) {
		String sql = getSQLFromPropertyFile("findCategoryByActivityId");
		ResultSetHandler<Category> rsh = new BeanHandler<Category>(
				Category.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(activityId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.CategoryDao#getDefaultCategory(int)
	 */
	@Override
	public Category getDefaultCategory(int userId) {
		String sql = getSQLFromPropertyFile("getDefaultCategory");
		ResultSetHandler<Category> rsh = new BeanHandler<Category>(
				Category.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}

	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.CategoryDao#add(java.sql.Connection,
	 * org.yukung.tasklet.entity.Category)
	 */
	@Override
	public void add(Connection conn, Category category) throws TaskletException {
		String sql = getSQLFromPropertyFile("addCategory");
		Object[] params = { Integer.valueOf(category.getUserId()),
				Integer.valueOf(category.getSeq()), category.getName() };
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.CategoryDao#findCategoriesByUserId(int)
	 */
	@Override
	public List<Map<String, Object>> findCategoriesByUserId(int userId) {
		String sql = getSQLFromPropertyFile("findCategoriesByUserId");
		ResultSetHandler<List<Map<String, Object>>> rsh = new MapListHandler();
		Integer param = Integer.valueOf(userId);
		try {
			return runner.query(sql, rsh, Integer.valueOf(userId), param);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dao.CategoryDao#getMaxSeqOfCategories(java.sql.Connection
	 * , int)
	 */
	@Override
	public Integer getMaxSeqOfCategories(int userId) {
		String sql = getSQLFromPropertyFile("getMaxSeqOfCategories");
		ResultSetHandler<Object> rsh = new ScalarHandler(1);
		try {
			return (Integer) runner.query(sql, rsh, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

}
