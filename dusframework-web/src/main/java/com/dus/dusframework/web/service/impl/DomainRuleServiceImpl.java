package com.dus.dusframework.web.service.impl;

import org.springframework.stereotype.Component;

import com.dus.dusframework.web.service.IDomainRuleService;

/**
 * 提供服务 ， 检查字段规则 与 字段是否匹配  
 * @author ThinkPad
 *
 */
@Component
public class DomainRuleServiceImpl implements IDomainRuleService{

	
	
	@Override
	public boolean checkDomainRule(String code, String dataType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean checkDomainDataFormat(String dataFormat, String domainGroup) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}