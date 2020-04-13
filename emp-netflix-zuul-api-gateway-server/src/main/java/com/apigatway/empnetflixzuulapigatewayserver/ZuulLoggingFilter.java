package com.apigatway.empnetflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {
	private Logger logger=LoggerFactory.getLogger(this.getClass());  
	//shouldFilter(): The shouldFilter() method checks the request and decides whether filter to be executed or not.
	@Override
	public boolean shouldFilter() {
		return true; //executing filter for every request  
	}
	// The run() method invokes, if both !isFilterDisabled() and shouldFilter() methods returns true

	@Override
	public Object run() throws ZuulException {
		//getting the current HTTP request that is to be handle  
		HttpServletRequest request=RequestContext.getCurrentContext().getRequest();  
		//printing the detail of the request  
		logger.info("request -> {} request uri-> {}", request, request.getRequestURI());  
		return null;
	}

	/*
	 * The filterType() method classify a filter by type. There are four types of
	 * standard filters in Zuul: 
	 * pre for pre-routing filtering, 
	 * route for routing to an origin, 
	 * post for post-routing filters, 
	 * and error for error handling. Zuul
	 * also supports a static type for static responses. Any filter type can be
	 * created or added and run by calling the method runFilters(type).
	 */
	@Override
	public String filterType() {
		return "pre"; //intercept all the request before execution
	}
	//The filter order must be defined for a filter. Filters may have the same filter order if the precedence is not important for the filters. The filter order does not need to be sequential

	@Override
	public int filterOrder() {
		return 1;//setting filter order to 1
	}

}
