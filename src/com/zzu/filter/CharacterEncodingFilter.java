package com.zzu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/*protected String encoding = null;
	protected FilterConfig filterConfig = null;

	// 过滤器的初始方法
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;// 初始化过滤器配置对象
		this.encoding = filterConfig.getInitParameter(encoding);// 获取配置过滤器的编码格式的参数
	}

	// 过滤器的接口方法,用于执行过滤任务
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding == null) {
			request.setCharacterEncoding(encoding);// 设置请求对象的编码格式
			response.setContentType("text/html;charset=" + encoding);// 设置应答对象的内容格式
		}
		chain.doFilter(request, response);// 继续过滤器的执行
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}*/
}

//shihu