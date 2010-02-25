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

import junit.framework.TestCase;

/**
 * @author yukung
 * 
 */
public class PasswordUtilTest extends TestCase {

	/**
	 * {@link org.yukung.tasklet.util.PasswordUtil#encrypt(java.lang.String)}
	 * のためのテスト・メソッド。
	 * 
	 */
	public void testEncrypt() {
		assertEquals("", PasswordUtil.encrypt(null));
		assertEquals("", PasswordUtil.encrypt(""));
		assertTrue(PasswordUtil.encrypt("1").length() == 32); // 1文字でも32バイトになる
		assertEquals("329435E5E66BE809A656AF105F42401E", PasswordUtil
				.encrypt("hogehoge"));
	}

}
