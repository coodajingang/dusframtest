package com.dus.dusframework.web.service;

public interface IDomainRuleService {

	/**
	 * 字段规则表 编码类； 代码类； 指示器类； 文本类； 金额类； 数值类； 百分比类； 日期类； 时间类； 日期时间类。<br>
	 * 
	 * a = 英文字符 
	 * n = 数字 
	 * c = 中文字符 
	 * ！= 定长  
	 * ..不定长
	 * （） 小数点后的位数
	 * yyyyMMdd  日期格式  
	 * 
	 * <br>
	 * 例子： 
	 * 	3!n   ==  3位数字定长  
	 *  3！an  == 3位字符数字定长
	 *  anc..20 == 20位字符中文数字不定长
	 *  18n(2)  == 18位数字  ， 小数后2位精度， 整数部分16位 
	 *  
	 */

	/**
	 * 检查域组下的域的数据格式是否合法；
	 * @param dataFormat
	 * @param domainGroup
	 * @return
	 */
	boolean checkDomainDataFormat(String dataFormat, String domainGroup);
	/**
	 * 检查字段值是否符合域规则
	 */
	boolean checkDomainRule(String value, String dataType);
}
