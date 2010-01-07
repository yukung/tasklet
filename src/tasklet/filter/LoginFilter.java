/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tasklet.entity.User;

/**
 * <p>セッション中にログインユーザのUserオブジェクトが存在するかどうかをチェックするフィルタです。</p>
 * <p>未使用。使用する場合はweb.xmlのコメントを解除してください。</p>
 *
 * @author Y.Ikeda
 */
public class LoginFilter implements Filter {

	private static final String LOGIN_PATH = "/index.do";

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// do nothing
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		if (!isLogin(session)) {
			// すべて/index.doに飛ばしてしまうと、ユーザ登録画面などもトップページに飛ばされてしまうため、
			// Strutsのモジュール化機能を使ってログイン済と未ログインの画面を分けてフィルタを適用する
			// 必要がある?
			request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// do nothing
	}

	/**
	 * セッション中にUserオブジェクトがあるかどうかを調べます。
	 *
	 * @param session
	 * @return ログインしているかどうか
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

}
