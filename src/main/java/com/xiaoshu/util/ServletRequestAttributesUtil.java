package com.xiaoshu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xiaoshu.exception.ControllerMethodInterceptor;

public class ServletRequestAttributesUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerMethodInterceptor.class);
	
	private volatile static String servletContextPath;
	
	public static HttpServletRequest getCurrentRequest(){
        ServletRequestAttributes requestAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttrs == null) {
        	logger.debug("当前线程中不存在 Request 上下文");
        	return null;
        }else{
        	return requestAttrs.getRequest();
        }
        
    }
	
	public static HttpServletResponse getCurrentResponse(){
		ServletRequestAttributes requestAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		 if (requestAttrs == null) {
        	logger.debug("当前线程中不存在 Request 上下文");
        	return null;
        }else{
        	return requestAttrs.getResponse();
        }
    }
	
	public static String getServletContextPath(){
		if (servletContextPath == null) {
			synchronized (ServletRequestAttributesUtil.class){
				if (servletContextPath == null) {
					HttpServletRequest request = ServletRequestAttributesUtil.getCurrentRequest();
		        	servletContextPath = request.getSession().getServletContext().getRealPath("/");
	        	}
			}
		}
		return servletContextPath;
	}
	
}
