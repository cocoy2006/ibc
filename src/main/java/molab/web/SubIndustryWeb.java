package molab.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import molab.db.entity.TSubIndustry;
import molab.service.SubIndustryService;

@Controller
@RequestMapping(value = "/subIndustry")
public class SubIndustryWeb {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SubIndustryService sis;
	
	@ResponseBody
	@RequestMapping(value = "/{industryId}")
	public String findAll(Model model, @PathVariable Integer industryId) {
		List<TSubIndustry> subIndustryList = sis.findAll(industryId);
		if(subIndustryList != null && subIndustryList.size() > 0) {
			return JSON.toJSONString(subIndustryList);
		}
		return "";
	}
	
}
