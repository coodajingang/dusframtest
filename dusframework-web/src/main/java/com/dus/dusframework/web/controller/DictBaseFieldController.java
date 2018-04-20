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
import com.dus.dusframework.web.dao.DusDictBaseFieldDao;
import com.dus.dusframework.web.domain.DusDictBaseFieldDo;

/**
 * 处理字典基本字段的 新增 、修改、 删除  及翻译处理
 * @author ThinkPad
 *
 */
@Controller
public class DictBaseFieldController {
	private static Logger log = LoggerFactory.getLogger(DictBaseFieldController.class);
	
	@Autowired
	private DusDictBaseFieldDao dao;
	
	@RequestMapping("/dictBaseSearch")
	public List<DusDictBaseFieldDo> search(String field) {
		List<DusDictBaseFieldDo> res = this.dao.searchLike(field);
		return res;
	}
	/**
	 * 接收 并 返回  json格式数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/searchBaseField.json")
	@ResponseBody
	public Map<String,Object> datatable(HttpServletRequest request) {
		String field = null;
		int startPage = 0;
		int pageSize = 0;
		int draw = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		System.out.println("================== /searchBaseField.json" ); 
		
		System.out.println(request.getRequestURL());

//		for (Enumeration<String> name = request.getAttributeNames();name.hasMoreElements(); ) {
//			String key = name.nextElement();
//			System.out.println(key + " =  " + request.getAttribute(key));
//		}
		
		Map<String ,String[]> map = request.getParameterMap();
		
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		// search[value]	a 
		// start	0
		// length	10
		// draw	3 
		
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
		
		List<DusDictBaseFieldDo> res = this.dao.searchLikeByPage(field);
		
		if (res == null) {
			res = new ArrayList<DusDictBaseFieldDo>();
		}
		
		//   "recordsTotal": 57, //数据总行数  
		// "recordsFiltered": 57, //数据总行数 
		result.put("data", res);
		result.put("draw", draw);
		result.put("recordsTotal", RunContextUtil.getPageInfo().getTotalRecords());
		result.put("recordsFiltered", RunContextUtil.getPageInfo().getTotalRecords());
		//result.put("", value)
		
		return result; 
	}
	
	/**
	 * 接收  并 新增字典基本字段数据  返回  json格式数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/saveBaseField.json")
	@ResponseBody
	public Map<String,Object> saveBaseField(DusDictBaseFieldDo inbean) {
		Map<String,Object> result = new HashMap<String,Object>();
		int insertcount = 0;
		List<DusDictBaseFieldDo> checkList = null;
		
		
		log.debug("================== /searchBaseField.json" ); 
		
		log.info(inbean.toString());
		
		// 检查输入要素  
		if (inbean == null || ComUtils.isEmpty(inbean.getChnName()) || 
				 ComUtils.isEmpty(inbean.getEngName()) ||
				 ComUtils.isEmpty(inbean.getAbbreviate()) ) {
			log.error("输入三要素不得为空 ！",inbean.toString());
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "存在必输项为空！");
			return result;
		}
		
		// 检查是否已经存在 
		try {
			checkList = this.dao.checkRepeat(inbean);
		} catch (Exception exp) {
			log.error("保存时检查查询出现异常 ！" , exp);
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "保存时检查异常！"  + exp.getMessage());
			
			return result;
		}
		
		if (checkList != null && !checkList.isEmpty()) {
			log.info("保存基本字段信息时， 存在重复字段信息，请检查");
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "保存时检查到重复字段信息，请检查！");
			
			return result;
		}
		
		// 插入  
		try {
			insertcount = this.dao.insert(inbean);
		} catch (Exception exp) {
			log.error("插入出现异常 ！" , exp);
			
			result.put("code", IDictManagerConstants.WEB_FAILE_CODE);
			result.put("resinfo", "保存时异常！"  + exp.getMessage());
			
			return result;
		}
		
		result.put("code", IDictManagerConstants.WEB_SUCCESS_CODE);
		result.put("resinfo", "success!" + insertcount);
		
		return result;
	}
	
	@RequestMapping("/baseFields")
	public String query() {
		log.debug("In BaseFields ");

		return "baseFields";
	}
}
