package molab;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import molab.interceptor.AdminSigninInterceptor;
import molab.interceptor.AgentSigninInterceptor;
import molab.interceptor.OwnerSigninInterceptor;

@Configuration
public class IbcWebAppConfiguration extends WebMvcConfigurerAdapter {

	@Override 
	public void addInterceptors(InterceptorRegistry registry) { 
		// 多个拦截器组成一个拦截器链 
		// addPathPatterns 用于添加拦截规则 
		// excludePathPatterns 用户排除拦截 
		registry.addInterceptor(new AdminSigninInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/signin*"); 
		registry.addInterceptor(new AgentSigninInterceptor()).addPathPatterns("/agent/**").excludePathPatterns("/agent/signin*");
		registry.addInterceptor(new OwnerSigninInterceptor()).addPathPatterns("/owner/**").excludePathPatterns("/owner/signin*");
		super.addInterceptors(registry); 
	}
	
}
