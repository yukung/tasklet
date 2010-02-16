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
package org.yukung.tasklet.dao;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * Daoの基底クラスです。<br>
 * Daoを作成する場合は、このクラスを継承して作成してください。
 * </p>
 * 
 * @author yukung
 * 
 */
public abstract class AbstractDao {

	private static final String SQL_PROPERTY_PATH = "/WEB-INF/classes/sql.properties";

	/** SQLプロパティファイルのsuffix文字列 */
	private static final String PROPERTY_KEY_SQL = "sqltable.";

	/** QueryRunnerのインスタンス */
	protected QueryRunner run;

	/** プロパティファイルのキーとSQL文が紐づいたMapオブジェクト */
	protected static Map<String, String> prop;

	/**
	 * <p>
	 * デフォルトコンストラクタ。<br>
	 * クエリ発行メソッドを呼ぶ際、Connectionをその都度渡す場合はこちらを呼び出してください。
	 * </p>
	 */
	protected AbstractDao() {
		initialize();
		this.run = new QueryRunner();
	}

	/**
	 * <p>
	 * コンストラクタ。<br>
	 * Connectionパラメータを使用しないメソッドでは、このDataSourceから接続を取り出します。
	 * 
	 * @param ds
	 *            接続を取り出すためのデータソース
	 */
	protected AbstractDao(DataSource ds) {
		initialize();
		this.run = new QueryRunner(ds);
	}

	/**
	 * <p>
	 * SQLプロパティファイルの内容を、Mapオブジェクトにマッピングします。
	 * </p>
	 * 
	 */
	private void initialize() {
		try {
			prop = QueryLoader.instance().load(SQL_PROPERTY_PATH);
		} catch (IOException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * SQLプロパティのキーを元に、SQL文を取得します。
	 * </p>
	 * 
	 * @param suffix
	 * @return SQLプロパティのキーに紐づいたSQL文
	 */
	protected String getSQLFromPropertyFile(String suffix) {
		String key = new StringBuffer(PROPERTY_KEY_SQL).append(suffix)
				.toString();
		return prop.get(key);
	}
}
