package molab.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
public class SpringProperties {

	@Value("resources.static-locations")
	private String uploadPath;

	/**
	 * @return the uploadPath
	 */
	public String getUploadPath() {
		return uploadPath;
	}

	/**
	 * @param uploadPath
	 *            the uploadPath to set
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

}
