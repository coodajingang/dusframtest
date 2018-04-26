package com.dus.dusframework.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dus.dusframework.common.util.ComUtils;
import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.web.IDictManagerConstants;
import com.dus.dusframework.web.dao.DusDictBaseDomainDao;
import com.dus.dusframework.web.dao.DusDictBaseDomainGroupDao;
import com.dus.dusframework.web.domain.DusDictBaseCodeDo;
import com.dus.dusframework.web.domain.DusDictBaseDomainDo;
import com.dus.dusframework.web.domain.DusDictBaseDomainGroupDo;
import com.dus.dusframework.web.service.IDomainRuleService;

@Controller
public class DictBaseDomainController {

	private static Logger log = LoggerFactory.getLogger(DictBaseDomainController.class);
	
	@Autowired
	private DusDictBaseDomainGroupDao groupDao;
	
	@Autowired
	private DusDictBaseDomainDao dao;
	
	@Autowired
	IDomainRuleService ruleService;
	
	
	/**
	 * 接收  处理yu的增删改查信息  返回  json格式数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/saveBaseDomain.json")
	@ResponseBody
	public Map<String,Object> saveBaseDomain(DusDictBaseDomainDo inbean) {
		Map<String,Object> result = new HashMap<String,Object>();
		int count = 0;
		List<DusDictBaseDomainDo> checkList = null;
		
		
		log.debug("================== /saveBaseDomain.json" ); 
		
		log.info(inbean.toString());
		
		// 检查输入操作类型要素  
		if (inbean == null || ComUtils.isEmpty(inbean.getDomainGroup()) 
				|| ComUtils.isEmpty(inbean.getDomainChnName()) || ComUtils.isEmpty(inbean.getDomainName())
				|| ComUtils.isEmpty(inbean.getDataType()) || ComUtils.isEmpty(inbean.getDataFormat()) ) {
			log.error("必输字段不得为空 ！",inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "必输字段不得为空");
			return result;
		}
		
		// 根据域组 和域名称 查询是否已经存在  
		List<DusDictBaseDomainDo> domainList = this.dao.selectByDomainGroup(inbean);
		
		if (domainList != null && !domainList.isEmpty()) {
			log.error("已经存在的域组和域名称 ！", inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "已经存在的域组和域名称 ");
			return result;
		}
		
		// 检查数据格式定义是否合法 
		if (!this.ruleService.checkDomainDataFormat(inbean.getDataFormat(), inbean.getDomainGroup())) {
			log.error("数据格式不合法 ！", inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "数据格式不合法 ");
			return result;
		}
		
		// 插入 
		try {
			count = this.dao.insert(inbean);
		} catch (Exception exp) {
			log.error("插入出现异常 ！" , exp);
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "保存时异常！"  + exp.getMessage());
			
			return result;
		}
		result.put("code", IDictManagerConstants.WEB_SUCCESS_CODE);
		result.put("resinfo", "success!" + count);
		
		return result;
	}
	
	/**
	 * 接收 并 返回  json格式数据  .  取查代码值  ; 查询页面搜索使用 
	 * @param bean
	 * @return
	 */
	@RequestMapping("/searchBaseDomains.json")
	@ResponseBody
	public Map<String,Object> searchBaseDomains(HttpServletRequest request) {
		String field = null;
		int startPage = 0;
		int pageSize = 0;
		int draw = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		System.out.println("================== /searchBaseDomains.json" ); 

		Map<String ,String[]> map = request.getParameterMap();
		
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		if (map.containsKey("start")) {
			startPage = Integer.parseInt(map.get("start")[0]);
		} else {
			startPage = 0;
		}
		
		if (map.containsKey("length")) {
			pageSize = Integer.parseInt(map.get("length")[0]);
		} else {
			pageSize = 10;
		}
		
		if (map.containsKey("draw")) {
			draw = Integer.parseInt(map.get("draw")[0]);
		} else {
			draw = -1;
		}
		
		if (map.containsKey("search[value]")) {
			field = map.get("search[value]")[0];
		}
		
		if (field == null || field.isEmpty()) {
			field = null;
		}
		
		// datatable 传入的参数是开始条数   ， 转换为开始页  
		
		startPage = startPage / pageSize + 1;
		
		if (pageSize <= 0) {
			pageSize = 28;
		}
		
		// 初始化 context  用于 分页  
		RunContextUtil.getCurrentRunContext();
		
		RunContextUtil.setPageInfo(startPage, pageSize);
		
		log.debug("分页信息： " + RunContextUtil.getPageInfo().toString());
		log.debug("搜索字段：" + field + " draw=" + draw);
		
		List<DusDictBaseDomainDo> res = this.dao.searchLikeByPage(field);
		
		if (res == null) {
			res = new ArrayList<DusDictBaseDomainDo>();
		}
		
		// "recordsTotal": 57, //数据总行数  
		// "recordsFiltered": 57, //数据总行数 
		result.put("data", res);
		result.put("draw", draw);
		result.put("recordsTotal", RunContextUtil.getPageInfo().getTotalRecords());
		result.put("recordsFiltered", RunContextUtil.getPageInfo().getTotalRecords());
		//result.put("", value)
		
		return result; 
	}
	
	/**
	 * 获取 baseDomains.html 
	 * @return
	 */
	@RequestMapping("baseDomains")
	public ModelAndView getDomainManager() {
		ModelAndView mv = new ModelAndView();
		
		// 返回domainGroupList  
		
		List<DusDictBaseDomainGroupDo> list = this.groupDao.selectAllGroup();
		
		log.debug("baseDomains模板，查询域组group列表：" + list.size());
		mv.addObject("domainGroupList", list);
		
		mv.setViewName("baseDomains");
		
		return mv;
	}
}
