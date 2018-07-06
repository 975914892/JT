package wc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import wc.entity.TbSystemUser;

public class LoginFilterService implements Filter{

	
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		
		HttpSession session=request.getSession();
		TbSystemUser user=(TbSystemUser) session.getAttribute("uesr");
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
