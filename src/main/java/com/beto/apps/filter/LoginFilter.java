package com.beto.apps.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class LoginFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("Entro");
		String requestUrl=req.getRequestURI();
			if (requestUrl.contains("/restapi")) {
				if(request.getParameter("auth").equals("c8ab5ec6be2e1f2729ab1553475383494d8f2efe")){

					chain.doFilter(request, response);	
				}
				else {
					res.sendError(405);
				}
			} else {
				System.out.println("No entro");
				String authenticate = (String) req.getSession().getAttribute("auth");
				if (authenticate != null && authenticate.equals("auth")) {
					chain.doFilter(request, response);
				} else
					res.sendRedirect("/f2g/login");
			}
		
	}
}