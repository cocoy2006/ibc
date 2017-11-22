package molab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import molab.properties.WxProperties;
import weixin.popular.support.TokenManager;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private WxProperties wp;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		TokenManager.init(wp.getId(), wp.getSecret());
	}

}
