package com.onemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemap.domain.InOrder;
import com.onemap.domain.InOrderDetail;
import com.onemap.service.BaseService;
import com.onemap.service.InOrderDetailService;
import com.onemap.service.InOrderService;

@Controller
@RequestMapping("/inorderdetail")
public class InOrderDetailController extends BaseController<InOrderDetail, Integer> {

	@Autowired
	private InOrderService inOrderService;
	@Autowired
	private InOrderDetailService inOrderDetailService;

	@Override
	BaseService<InOrderDetail, Integer> getBaseService() {
		return inOrderDetailService;
	}

	@Override
	public void addDataForEditView(ModelAndView modelAndView) throws Exception {
	}

	@RequestMapping("edit")
	public ModelAndView addOrUpdate(InOrderDetail inOrderDetail) throws Exception {
		if(inOrderDetail.getInOrderId()==null){
			throw new RuntimeException("入库单ID错误!");
		}
		ModelAndView modelAndView = new ModelAndView();
		InOrder inOrder = this.inOrderService.get(inOrderDetail.getInOrderId());
		modelAndView.addObject("inOrder", inOrder);
		if (inOrderDetail.getId() == null) {
			return modelAndView;

		}
		InOrderDetail inOrderDetailInDb = this.inOrderDetailService.get(inOrderDetail.getId());
		modelAndView.addObject("currentObj", inOrderDetailInDb);
		return modelAndView;
	}

	@Override
	@RequestMapping("save")
	public String save(InOrderDetail inOrderDetail) throws Exception {
		if (inOrderDetail.getId() == null) {
			inOrderDetailService.save(inOrderDetail);
		} else {
			inOrderDetailService.update(inOrderDetail);
		}
		return "redirect:../inorder/edit?id=" + inOrderDetail.getInOrderId();

	}

}
