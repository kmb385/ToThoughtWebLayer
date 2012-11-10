package org.tothought.controllers.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tothought.controllers.annotations.PageableRequestMapping;

public class PageableRequestHandlerInterceptor extends
		HandlerInterceptorAdapter {

	Logger logger = LoggerFactory
			.getLogger(PageableRequestHandlerInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
				
		HandlerMethod method = (HandlerMethod) handler;
		
		if(null != method.getMethodAnnotation(org.tothought.controllers.annotations.PageableRequestMapping.class)){
			logger.info("Handling method:" + request.getRequestURL());
			
			PageableRequestMapping pageableRequestMapping = method.getMethodAnnotation(PageableRequestMapping.class);
			int pageNumber = parsePageNumber(request.getRequestURL(), pageableRequestMapping);
			
			logger.info("Page Number is: " + pageNumber);
			modelAndView.addObject("prevPage", pageNumber - 1);
			modelAndView.addObject("nextPage", pageNumber + 1);
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}

	private int parsePageNumber(StringBuffer requestUrl,
			PageableRequestMapping pageableRequestMapping) {
		
		String pathVariable = pageableRequestMapping.pathVariable();
		String defaultPathVariable = this.getDefaultPathVariable(pageableRequestMapping);
		
		if(!pathVariable.equalsIgnoreCase(defaultPathVariable)){
			String[] tokens = requestUrl.toString().split("/");
			
			for(int x = 0; x < tokens.length; x++){	
				if(tokens[x].equalsIgnoreCase(pathVariable) && NumberUtils.isNumber(tokens[x+1])){
					return Integer.parseInt(tokens[x+1]);
				}				
			}
		}else{
			String pageNumber = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);
			return (NumberUtils.isNumber(pageNumber)) ? Integer.parseInt(pageNumber) : 0;
		}
		
		return 0;
	}

	private String getDefaultPathVariable(PageableRequestMapping pageableRequestMapping) {
		try {
			Method method = PageableRequestMapping.class.getMethod("pathVariable");
			logger.info("Default Method Path is:" + (String) method.getDefaultValue());
			return (String) method.getDefaultValue();
		} catch (Exception e) {
			return "/";
		}		
	}
}
