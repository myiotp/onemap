/**
 * 
 */
package com.onemap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.APIResponseBaseObject;
import com.onemap.domain.UploadImage;
import com.onemap.service.BaseService;
import com.onemap.service.UploadImageService;

/**
 * @author
 * 
 */
@Controller
@RequestMapping("/api/uploadimage")
public class UploadImageAPIController extends BaseController<UploadImage, Integer> {
	@Autowired
	private UploadImageService service;

	@Override
	BaseService<UploadImage, Integer> getBaseService() {
		return service;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "{username}/type/{type}", method = RequestMethod.GET)
	@ResponseBody
	public APIResponseBaseObject getByUsernameAndType(@PathVariable("username") String username, @PathVariable("type") String type) {
		APIResponseBaseObject result = new APIResponseBaseObject();
		try {
			List<UploadImage> auths = this.service.getByUsernameAndType(username, type);
			System.out.println(auths);
			if (auths.size() > 0) {
				result.setData(auths.get(0));
			}
			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public APIResponseBaseObject post(@RequestBody UploadImage t) {
		APIResponseBaseObject result = new APIResponseBaseObject();

		
		try {
			if (t.getId() == null) {
				this.getBaseService().save(t);
			} else {
				this.getBaseService().update(t);
			}

			result.setInfo("OK");
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setInfo(e.getMessage());
			result.setStatus(0);
		}
		return result;
	}
}
