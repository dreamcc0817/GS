package com.dreamcc.gs.controller;

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

import com.dreamcc.gs.bean.Param;
import com.dreamcc.gs.bean.Param;
import com.dreamcc.gs.model.ParamModel;
import com.dreamcc.gs.serviceimpl.ParamService;
import com.dreamcc.gs.util.HtmlUtil;
import com.dreamcc.gs.util.JSONUtil;

@Controller
@RequestMapping("/param")
public class ParamController extends BaseController {
	private final static Logger log = Logger.getLogger(ParamController.class);

	@Autowired
	private ParamService<Param> paramService;

	/**
	 * 设置页面数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(ParamModel model, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		List<Param> dataList = paramService.queryByList(model);
		context.put("dataList", JSONUtil.toJSONString(dataList));
		return forword("scm/Param", context);
	}

	/**
	 * 列表页面
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(ParamModel model, HttpServletResponse response) throws Exception {
		List<Param> dataList = paramService.queryByList(model);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 保存修改数据
	 * 
	 * @param bean
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(Param bean, HttpServletResponse response) throws Exception {
		if (bean.getId() == null) {
			paramService.add(bean);
		} else {
			paramService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(Integer id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();
		Param bean = paramService.queryById(id);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}

	@RequestMapping("/delete")
	public void delete(Integer[] id, HttpServletResponse response) throws Exception {
		paramService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
