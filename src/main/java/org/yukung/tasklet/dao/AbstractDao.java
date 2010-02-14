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

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

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

	/** SQLプロパティファイルのsuffix文字列 */
	private static final String PROPERTY_KEY_SQL = "sqltable.";

	/** QueryRunnerのインスタンス */
	protected QueryRunner run;

	/**
	 * <p>
	 * デフォルトコンストラクタ。<br>
	 * クエリ発行メソッドを呼ぶ際，Connectionをその都度渡す場合はこちらを呼び出してください。
	 * </p>
	 */
	protected AbstractDao() {
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
		this.run = new QueryRunner(ds);
	}

	protected String getSQLFromPropertyFile(String suffix) {
		String key = new StringBuffer(PROPERTY_KEY_SQL).append(suffix)
				.toString();
		// TODO PropertyFileUtilsが出来たら実装を書く

	}
}
