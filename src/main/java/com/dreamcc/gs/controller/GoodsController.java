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

import com.dreamcc.gs.bean.Goods;
import com.dreamcc.gs.bean.Goods;
import com.dreamcc.gs.model.GoodsModel;
import com.dreamcc.gs.serviceimpl.GoodsService;
import com.dreamcc.gs.util.HtmlUtil;
import com.dreamcc.gs.util.JSONUtil;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
	
	private final static Logger log = Logger.getLogger(GoodsController.class);
	@Autowired
	private GoodsService<Goods> goodsService;

	/**
	 * 设置页面数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(GoodsModel model, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		List<Goods> dataList = goodsService.queryByList(model);
		context.put("dataList", JSONUtil.toJSONString(dataList));
		return forword("scm/goods", context);
	}

	/**
	 * 列表页面
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void dataList(GoodsModel model, HttpServletResponse response) throws Exception {
		List<Goods> dataList = goodsService.queryByList(model);

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
	public void save(Goods bean, HttpServletResponse response) throws Exception {
		if(bean.getId() == null){
			goodsService.add(bean);
		}else{
			goodsService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		Goods bean  = goodsService.queryById(id);
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
		goodsService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
}
