package com.cts.capstone.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			if (!method.getName().equals("ok") && !handlerMethod.getBean().getClass().getName().equals("class com.cts.capstone.controller.Controller")) {
				LOGGER.info("{} - {} - {} - method '{}' on controller '{}' - status {}",
						request.getMethod(),
						request.getRequestURI(),
						request.getRemoteAddr(),
						method.getName(),
						handlerMethod.getBean().getClass(),
						response.getStatus());
			}
		}

	}
}