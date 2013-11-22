package org.tothought;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class RewriteFilter
 */
public class RewriteFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(RewriteFilter.class);
    /**
     * Default constructor. 
     */
    public RewriteFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			
			String url = httpServletRequest.getRequestURL().toString();
			String queryString = httpServletRequest.getQueryString();
			String fullUrl = url + ((queryString != null) ? ("?" + queryString):"");
			String domainAndContext = getURLWithContextPath(httpServletRequest);
			
			System.out.println(domainAndContext);
			System.out.println(fullUrl);
		  
			
			String newUrl = fullUrl.replace(domainAndContext, "http://blog-tothought.rhcloud.com/");
			System.out.println(newUrl);
			
			httpServletResponse.setStatus(301);
			httpServletResponse.setHeader("Location", newUrl);
			httpServletResponse.setHeader("Connection", "close");
		}
		chain.doFilter(request, response);
	}

	public static String getURLWithContextPath(HttpServletRequest request) {
		   return request.getScheme() + "://" + request.getServerName() /*+ ":" + request.getServerPort()*/ + request.getContextPath();
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
