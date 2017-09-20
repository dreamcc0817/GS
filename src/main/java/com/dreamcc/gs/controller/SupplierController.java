package com.dreamcc.gs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dreamcc.gs.bean.Supplier;
import com.dreamcc.gs.exception.ServiceException;
import com.dreamcc.gs.model.SupplierModel;
import com.dreamcc.gs.serviceimpl.SupplierService;
import com.dreamcc.gs.util.HtmlUtil;
import com.dreamcc.gs.util.JSONUtil;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

	private final static Logger log = Logger.getLogger(SupplierController.class);

	@Autowired
	private SupplierService<Supplier> supplierService;

	/**
	 * 设置页面数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(SupplierModel model, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		List<Supplier> dataList = supplierService.queryByList(model);
		context.put("dataList", JSONUtil.toJSONString(dataList));
		return forword("scm/supplier", context);
	}

	/**
	 * 列表页面
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(SupplierModel model, HttpServletResponse response) throws Exception {
		List<Supplier> dataList = supplierService.queryByList(model);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	/**
	 * 保存修改数据
	 * @param bean
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(Supplier bean, HttpServletResponse response) throws Exception {
		if(bean.getId() == null){
			supplierService.add(bean);
		}else{
			supplierService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		Supplier bean  = supplierService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		supplierService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
