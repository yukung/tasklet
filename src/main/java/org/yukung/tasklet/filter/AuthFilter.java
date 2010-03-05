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
package org.yukung.tasklet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.yukung.tasklet.entity.User;

/**
 * <p>
 * セッション中にログインユーザのUserオブジェクトが存在するかどうかをチェックするフィルタです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class AuthFilter implements Filter {

	private static final String LOGIN_PATH = "/index.do";

	/*
	 * (非 Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// do nothing
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		if (!isLogin(session)) {
			request.getRequestDispatcher(LOGIN_PATH).forward(httpRequest,
					response);
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * <p>
	 * セッション中にUserオブジェクトがあるかどうかを調べます。
	 * </p>
	 * 
	 * @param session
	 * @return ログインしていればtrue,ログインしていなければfalse
	 */
	private boolean isLogin(HttpSession session) {
		if (session == null) {
			return false;
		}
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		return true;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing

	}

}
