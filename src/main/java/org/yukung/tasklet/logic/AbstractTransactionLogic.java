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
package org.yukung.tasklet.logic;

import org.apache.commons.dbutils.QueryRunner;
import org.yukung.tasklet.factory.DaoFactory;

/**
 * <p>
 * DB更新のトランザクション処理の基底クラスです。<br>
 * トランザクション処理を行うクラスを作成する場合は、このクラスを継承して作成してください。
 * </p>
 * 
 * @author yukung
 * 
 */
public abstract class AbstractTransactionLogic {

	/** QueryRunnerのインスタンス */
	protected QueryRunner runner = new QueryRunner(DaoFactory.getInstance()
			.getDataSource());
}
