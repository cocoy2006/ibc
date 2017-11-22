package molab.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Repository
public class AdminSigninInterceptor implements HandlerInterceptor {

	private final String PREFIX = "admin";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		Object obj = request.getSession().getAttribute(PREFIX);
		if (null == obj) {
			String url = request.getRequestURL().toString();
			String newUrl = url.substring(0, url.indexOf(PREFIX) + 5);
			response.sendRedirect(newUrl + "/signin");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {}

}
