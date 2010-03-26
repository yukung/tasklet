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
package org.yukung.tasklet.util;

import java.sql.Date;

import org.apache.commons.beanutils.Converter;

/**
 * <p>
 * ActionFormからEntityへの変換を行う際、Date型の項目の文字列 → Date型への<br>
 * コンバートを行うユーティリティクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class DateConverter implements Converter {

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Class type, Object value) {
		if (value.toString().equals("")) {
			return null;
		} else {
			return Date.valueOf(value.toString().replace("/", "-"));
		}
	}

}
