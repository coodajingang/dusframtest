package com.dus.dusframework.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/baseFields")
	public String query(String field, Model model) {
		log.debug(field);
		
		List<DusDictBaseFieldDo> res = this.dao.searchLike(field);
		return "baseFields";
	}
}
