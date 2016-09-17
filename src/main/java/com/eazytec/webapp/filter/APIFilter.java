package com.eazytec.webapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * Filter to interrupt the API url and do the authentication of BPM API
 * @author revathi
 *
 */
public class APIFilter extends OncePerRequestFilter {

	@SuppressWarnings("deprecation")
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get the username and password form URL
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		  if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			  response.setStatus(401, "No Username and/or Password Provided.");
		    }else{
		    	//Authentication
		    	UsernamePasswordAuthenticationToken authCredentical = new UsernamePasswordAuthenticationToken(userName, password);
		    	
		    	ApplicationContext ctx = WebApplicationContextUtils
						.getRequiredWebApplicationContext(getServletContext());
		    	CustomAuthenticationProvider auth = (CustomAuthenticationProvider) ctx
						.getBean("customAuthenticationProvider");
		    	try{
		    		if(auth.authenticate(authCredentical).isAuthenticated()){
		    			//if authorized user URL action will be perform
		    			request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
		    			
		    		}else{
		    			response.setStatus(401, "Invalid UserName and Password");
		    	}
		    }catch(Exception e){
		    	response.setStatus(401, "Invalid UserName and Password");
    		}
		    	
		  }
		
	}

}
