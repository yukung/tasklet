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
package org.yukung.tasklet.service.impl;

import org.yukung.tasklet.dao.UserDao;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.DaoFactory;
import org.yukung.tasklet.service.AccountService;

/**
 * <p>
 * ユーザアカウント関連のビジネスロジックを実行するServiceの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class AccountServiceImpl implements AccountService {

	/** ユーザ情報DAO */
	private UserDao userDao = DaoFactory.getInstance().createUserDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.service.AccountService#register(org.yukung.tasklet
	 * .entity.User)
	 */
	@Override
	public void register(User user) throws TaskletException {
		// TODO 自動生成されたメソッド・スタブ
		checkForDuplicate(user.getUserName());

	}

	/**
	 * <p>
	 * ユーザ名が重複していないかチェックします。
	 * </p>
	 * 
	 * @param userName
	 */
	private void checkForDuplicate(String userName) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
