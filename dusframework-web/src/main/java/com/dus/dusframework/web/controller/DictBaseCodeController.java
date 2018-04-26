package com.dus.dusframework.web.controller;

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

import com.dus.dusframework.common.util.ComUtils;
import com.dus.dusframework.context.RunContextUtil;
import com.dus.dusframework.web.IDictManagerConstants;
import com.dus.dusframework.web.dao.DusDictBaseCodeDao;
import com.dus.dusframework.web.dao.DusDictBaseDomainDao;
import com.dus.dusframework.web.domain.DusDictBaseCodeDo;
import com.dus.dusframework.web.domain.DusDictBaseDomainDo;
import com.dus.dusframework.web.service.IDomainRuleService;

/**
 * 处理字典代码值的 新增 、修改、 删除  及翻译处理
 * @author ThinkPad
 *
 */
@Controller
public class DictBaseCodeController {

	private static Logger log = LoggerFactory.getLogger(DictBaseCodeController.class);
	
	@Autowired
	private DusDictBaseCodeDao dao;
	
	@Autowired
	private DusDictBaseDomainDao domainDao;
	
	@Autowired
	private IDomainRuleService domainRule;
	
	/**
	 * 接收  处理代码值的增删改查信息  返回  json格式数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/saveBaseCodeField.json")
	@ResponseBody
	public Map<String,Object> saveBaseField(DusDictBaseCodeDo inbean) {
		Map<String,Object> result = new HashMap<String,Object>();
		int count = 0;
		List<DusDictBaseCodeDo> checkList = null;
		
		
		log.debug("================== /saveBaseCodeField.json" ); 
		
		log.info(inbean.toString());
		
		// 检查输入操作类型要素  
		if (inbean == null || ComUtils.isEmpty(inbean.getOperator())) {
			log.error("输入操作类型不得为空 ！",inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "输入操作类型不得为空");
			return result;
		}
		
		// 若操作类型为01-新增  02-域新增  03-更新 04-删除 校验  
		
		if (ComUtils.equals(inbean.getOperator(), "01") || ComUtils.equals(inbean.getOperator(), "02")) {
			log.info("代码值新增处理。。。");
			// 检查各种非空 
			if (ComUtils.isEmpty(inbean.getDomainName()) || ComUtils.isEmpty(inbean.getDomainSeqno()) 
					|| ComUtils.isEmpty(inbean.getCode()) || ComUtils.isEmpty(inbean.getCodeName())) {
				log.error("输入操作类型为新增时，必输字段为空  ！", inbean.toString());
				
				result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
				result.put("resinfo", "操作类型新增时，必输字段不得为空");
				return result;
			}
			
			// 检查domain 有效  
			DusDictBaseDomainDo domain = this.checkDomainDefine(inbean.getDomainName(), inbean.getDomainSeqno());
			
			if (domain == null) {
				log.error("代码值域组未定义！" , inbean.toString());
				result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
				result.put("resinfo", "代码值域组未定义！");
				return result;
			}
			
			// 检查 code 符合 domain 定义的规则  
			if (!this.domainRule.checkDomainRule(inbean.getCode(), domain.getDataType())) {
				log.error("代码值不符合规则定义！" , inbean.toString());
				result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
				result.put("resinfo", "代码值不符合域组格式！" + domain.getDataType());
				return result;
			}
			
			// 判重 
			// 新增之   （根据主键判重）  
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
			
		} else if (ComUtils.equals(inbean.getOperator(), "03")) {
			// gengxing  域名称 、编号、代码值、 原代码值 不能为空  
			log.info("代码值更新处理。。。");
			// 检查各种非空 
			if (ComUtils.isEmpty(inbean.getDomainName()) || ComUtils.isEmpty(inbean.getDomainSeqno()) 
					|| ComUtils.isEmpty(inbean.getCode()) || ComUtils.isEmpty(inbean.getCodeName())
					|| ComUtils.isEmpty(inbean.getOriCode())) {
				log.error("输入操作类型为更新时，必输字段为空  ！", inbean.toString());
				
				result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
				result.put("resinfo", "操作类型更新时，必输字段不得为空");
				return result;
			}
			
			// 若更新了代码值 ， 则 检查代码值是否符合规则  
			if (!ComUtils.equals(inbean.getCode(), inbean.getOriCode())) {
				// 检查domain 有效  
				DusDictBaseDomainDo domain = this.checkDomainDefine(inbean.getDomainName(), inbean.getDomainSeqno());
				
				if (domain == null) {
					log.error("代码值域组未定义！" , inbean.toString());
					result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
					result.put("resinfo", "代码值域组未定义！");
					return result;
				}
				
				// 检查 code 符合 domain 定义的规则  
				if (!this.domainRule.checkDomainRule(inbean.getCode(), domain.getDataType())) {
					log.error("代码值不符合规则定义！" , inbean.toString());
					result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
					result.put("resinfo", "代码值不符合域组格式！" + domain.getDataType());
					return result;
				}
			}
			
			try {
				count = this.dao.updateCodeInfo(inbean);
			} catch (Exception exp) {
				log.error("更新出现异常 ！" , exp);
				
				result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
				result.put("resinfo", "保存时异常！"  + exp.getMessage());
				
				return result;
			}
			result.put("code", IDictManagerConstants.WEB_SUCCESS_CODE);
			result.put("resinfo", "success!" + count );
			
			return result;
		} else if (ComUtils.equals(inbean.getOperator(), "04")) {
			// gengxing  域名称 、编号、代码值、 原代码值 不能为空  
			log.info("代码值删除处理。。。");
			// 检查各种非空  , 若不输入代码值， 则删除域下所有的代码信息 ；
			// 若输入有代码值， 则删除特定的代码值  
			if (ComUtils.isEmpty(inbean.getDomainName()) || ComUtils.isEmpty(inbean.getDomainSeqno()) ) {
				log.error("输入操作类型为删除时，必输字段为空  ！", inbean.toString());
				
				result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
				result.put("resinfo", "操作类型删除时，必输字段不得为空");
				return result;
			}
			
			try {
				count = this.dao.deleteByDomainCode(inbean);
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
		
		result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
		result.put("resinfo", "未定义的操作类型！" + inbean.getOperator() );
		
		return result;
	}
	
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
	 * 接收 并 返回  json格式数据  .  取查代码值  ; 查询页面搜索使用 
	 * @param bean
	 * @return
	 */
	@RequestMapping("/searchBaseCodes.json")
	@ResponseBody
	public Map<String,Object> searchBaseCodes(HttpServletRequest request) {
		String field = null;
		int startPage = 0;
		int pageSize = 0;
		int draw = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		System.out.println("================== /searchBaseCodes.json" ); 

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
		
		List<DusDictBaseCodeDo> res = this.dao.searchLikeByPage(field);
		
		if (res == null) {
			res = new ArrayList<DusDictBaseCodeDo>();
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
	
	@RequestMapping("/baseCodes")
	public String query() {
		log.debug("In baseCodes ");

		return "baseCodes";
	}
	

}
