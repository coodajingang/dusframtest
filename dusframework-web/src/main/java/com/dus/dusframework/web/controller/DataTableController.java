package com.dus.dusframework.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataTableController {

	/**
	 * 使用thyemleaf 模板 ，   学习datatable   
	 * @return
	 */
	@RequestMapping("/datatablestudy")
	public String datatablestudy() {
		System.out.println("================== /datatablestudy" );  

		System.out.println("返回 thymeleaf 目标模板");
		return "datatablestudy"; 
	}
	
	/**
	 * 使用thyemleaf 模板 ， 返回 datatablehtml.html  验证json 
	 * @return
	 */
	@RequestMapping("/datatablehtml")
	public String datatablehtml() {
		System.out.println("================== /datatablehtml" );  

		System.out.println("返回 thymeleaf 目标模板");
		return "datatablehtml"; 
	}
	
	/**
	 * 接收 并 返回  json格式数据  
	 * @param bean
	 * @return
	 */
	@RequestMapping("/datatable")
	@ResponseBody
	public List<DataTableBean> datatable(@RequestBody(required= false) DataTableBean bean) {
		System.out.println("================== /datatable" );  
		
		System.out.println(bean.toString());
		
		List<DataTableBean> rlist = new ArrayList<DataTableBean>(); 
		
		for (int i = 0 ; i < 26; i++) {
			rlist.add(DataTableBean.genRandom());
		}
		
		return rlist; 
	}
}
