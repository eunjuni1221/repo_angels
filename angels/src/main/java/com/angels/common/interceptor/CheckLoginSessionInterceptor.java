package com.angels.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		관리자용
		if(request.getRequestURI().contains("Xdm")) {
			if (request.getSession().getAttribute("sessXdmSeq") != null) {
				// by pass
			} else {
				response.sendRedirect("/login/Login");
		        return false;
			}
		} else {
//			by pass
		}
		
//		사용자용
//		if(request.getRequestURI().contains(Constants.ABBREVIATION_USER)) {
//			if (request.getSession().getAttribute(Constants.SESSION_SEQ_NAME_USR) != null) {
//				// by pass
//			} else {
//				response.sendRedirect(Constants.URL_LOGINUSRFORM);
//				return false;
//			}
//		} else {
//			by pass
//		}
//		
//		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}

