package com.dus.dusframework.web.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dus.dusframework.common.util.ComUtils;
import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.web.IDictManagerConstants;
import com.dus.dusframework.web.dao.DusDictBaseDomainDao;
import com.dus.dusframework.web.dao.DusDictBaseDomainGroupDao;
import com.dus.dusframework.web.dao.DusDictFieldDao;
import com.dus.dusframework.web.domain.DusDictBaseCodeDo;
import com.dus.dusframework.web.domain.DusDictBaseDomainDo;
import com.dus.dusframework.web.domain.DusDictBaseDomainGroupDo;
import com.dus.dusframework.web.domain.DusDictFieldDo;
import com.dus.dusframework.web.service.IDomainRuleService;

/**
 * 处理字典 的 新增 、修改、 删除  及翻译处理
 * @author ThinkPad
 *
 */
@Controller
public class DictFieldController {

	private static Logger log = LoggerFactory.getLogger(DictFieldController.class);
	
	@Autowired
	private DusDictFieldDao dao;
	
	@Autowired
	private DusDictBaseDomainDao domainDao;
	
	@Autowired
	private IDomainRuleService domainRule;
	
	@Autowired
	private DusDictBaseDomainGroupDao groupDao;
	
	/**
	 * 查询域组对应的域组定义 ，没有则返回空 
	 * @param domainName
	 * @param domainSeqno
	 * @return
	 */
	private DusDictBaseDomainDo checkDomainDefine(String domainName, long domainSeqno) {
		DusDictBaseDomainDo querydomain = new DusDictBaseDomainDo();
		querydomain.setDomainSeqno(domainSeqno);
		querydomain.setDomainName(domainName);
		
		// 检查domain 有效  
		DusDictBaseDomainDo domain = this.domainDao.selectByPk(querydomain);
		
		return domain;
	}
	
	/**
	 * 接收 并 返回  json格式数据  .  删除字典数据 
	 * @param bean
	 * @return
	 */
	@RequestMapping("/deleteDictField.json")
	@ResponseBody
	public Map<String,Object> deleteDictField(DusDictFieldDo inbean) {
		Map<String,Object> result = new HashMap<String,Object>();
		int count = 0;
		
		log.debug("================== /deleteDictField.json" ); 
		
		log.info(inbean.toString());

		// 检查各种非空 
		if (ComUtils.isEmpty(inbean.getSeqNo()) || ComUtils.isEmpty(inbean.getEngName()) 
				|| ComUtils.isEmpty(inbean.getChnName()) ) {
			log.error("删除时，必输字段为空  ！", inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "删除时，必输字段为空  ！");
			return result;
		}
		
		try {
			count = this.dao.deleteBySeqAndName(inbean);
		} catch (Exception exp) {
			log.error("删除出现异常 ！" , exp);
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "删除时异常！"  + exp.getMessage());
			
			return result;
		}
		result.put("code", IDictManagerConstants.WEB_SUCCESS_CODE);
		result.put("resinfo", "success!" + count );
		
		return result;
	}
	
	/**
	 * 接收 并 返回  json格式数据  .  更新字典数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/updateDictField.json")
	@ResponseBody
	public Map<String,Object> updateDictField(DusDictFieldDo inbean) {
		Map<String,Object> result = new HashMap<String,Object>();
		int count = 0;
		
		log.debug("================== /updateDictField.json" ); 
		
		log.info(inbean.toString());

		// 检查各种非空 
		if (ComUtils.isEmpty(inbean.getSeqNo()) ) {
			log.error("更新时，必输字段序号为空  ！", inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "更新时，必输字段序号为空  ！");
			return result;
		}
		
		// 检查domain 有效  
		DusDictBaseDomainDo domain = this.checkDomainDefine(inbean.getDomainName(), inbean.getDomainSeqno());
			
		if (domain == null) {
			log.error("字典域组未定义！", inbean.toString());
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "字典域组未定义！");
			return result;
		}

		if (!ComUtils.equals(domain.getDomainGroup(), inbean.getDomainGroup())) {
			log.error("字典域组输入与定义不同！", domain.getDomainGroup(), inbean.getDomainGroup());
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "字典域组输入与定义不同！");
			return result;
		}

		if (!ComUtils.equals(domain.getDataFormat(), inbean.getDataFormat())) {
			log.error("字典数据格式输入与定义不同！", domain.getDataFormat(), inbean.getDataFormat());
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "字典数据格式输入与定义不同！");
			return result;
		}

		try {
			count = this.dao.updateDictFieldInfo(inbean);
		} catch (Exception exp) {
			log.error("更新出现异常 ！" , exp);
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "保存时异常！"  + exp.getMessage());
			
			return result;
		}
		result.put("code", IDictManagerConstants.WEB_SUCCESS_CODE);
		result.put("resinfo", "success!" + count );
		
		return result;
	}
	
	/**
	 * 接收 并 返回  json格式数据  .  新增  字典数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/saveDictField.json")
	@ResponseBody
	public Map<String,Object> saveDictField(DusDictFieldDo inbean) {
		Map<String,Object> result = new HashMap<String,Object>();
		int count = 0;
		
		log.debug("================== /saveDictField.json" ); 
		
		log.info(inbean.toString());

		// 检查各种非空 
		if (ComUtils.isEmpty(inbean.getEngName()) || ComUtils.isEmpty(inbean.getChnName())  
				 || ComUtils.isEmpty(inbean.getDataType())  || ComUtils.isEmpty(inbean.getDataFormat()) 
				 || ComUtils.isEmpty(inbean.getDomainGroup())  || ComUtils.isEmpty(inbean.getDomainSeqno()) 
				 || ComUtils.isEmpty(inbean.getDomainName()) ) {
			log.error("更新时，必输字段为空  ！", inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "更新时，必输字段为空  ！");
			return result;
		}
		
		// 检查domain 有效  
		DusDictBaseDomainDo domain = this.checkDomainDefine(inbean.getDomainName(), inbean.getDomainSeqno());
			
		if (domain == null) {
			log.error("字典域组未定义！", inbean.toString());
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "字典域组未定义！");
			return result;
		}

		if (!ComUtils.equals(domain.getDomainGroup(), inbean.getDomainGroup())) {
			log.error("字典域组输入与定义不同！", domain.getDomainGroup(), inbean.getDomainGroup());
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "字典域组输入与定义不同！");
			return result;
		}

		if (!ComUtils.equals(domain.getDataFormat(), inbean.getDataFormat())) {
			log.error("字典数据格式输入与定义不同！", domain.getDataFormat(), inbean.getDataFormat());
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "字典数据格式输入与定义不同！");
			return result;
		}

		try {
			count = this.dao.insert(inbean);
		} catch (Exception exp) {
			log.error("插入出现异常 ！" , exp);
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "保存时异常！"  + exp.getMessage());
			
			return result;
		}
		result.put("code", IDictManagerConstants.WEB_SUCCESS_CODE);
		result.put("resinfo", "success!" + count );
		
		return result;
	}
	
	/**
	 * 接收 并 返回  json格式数据  .  取查代码值  ; 查询页面搜索使用 
	 * @param bean
	 * @return
	 */
	@RequestMapping("/searchDictFields.json")
	@ResponseBody
	public Map<String,Object> searchDictFields(HttpServletRequest request) {
		String field = null;
		int startPage = 0;
		int pageSize = 0;
		int draw = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		System.out.println("================== /searchDictFields.json" ); 

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
		
		List<DusDictFieldDo> res = this.dao.searchLikeByPage(field);
		
		if (res == null) {
			res = new ArrayList<DusDictFieldDo>();
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
	
	@RequestMapping("/dictManager")
	public String query() {
		log.debug("In dictManager ");

		return "dictManager";
	}
	
	/**
	 * 字典新增 模板 
	 * @return
	 */
	@RequestMapping("/dictManagerAdd")
	public ModelAndView dictManagerAdd() {
		ModelAndView mv = new ModelAndView();
		log.debug("In dictManagerAdd ");
		List<DusDictBaseDomainGroupDo> list = this.groupDao.selectAllGroup();
		
		log.debug("baseDomains模板，查询域组group列表：" + list.size());
		
		List<String> sourceList = new ArrayList<String>();
		List<String> jdbcTypeList = new ArrayList<String>();
		List<String> javaTypeList = new ArrayList<String>();

		javaTypeList.add("String");
		javaTypeList.add("Integer");
		javaTypeList.add("Long");
		javaTypeList.add("Double");
		javaTypeList.add("Float");
		javaTypeList.add("BigDecimal");
		javaTypeList.add("Short");
		javaTypeList.add("BigInteger");
		javaTypeList.add("Date");
		javaTypeList.add("Time");
		javaTypeList.add("Timestamp");
		
		
		jdbcTypeList.add("VARCHAR2");
		jdbcTypeList.add("CHAR");
		jdbcTypeList.add("NUMBER");
		jdbcTypeList.add("TIMESTAMP");
		jdbcTypeList.add("CLOB");
		jdbcTypeList.add("BLOB");
		jdbcTypeList.add("VARCHAR");
		
		mv.addObject("javaTypeList", javaTypeList);
		mv.addObject("jdbcTypeList", jdbcTypeList);
		mv.addObject("domainGroupList", list);
		
		mv.setViewName("dictManagerAdd");
		return mv;
	}

}
