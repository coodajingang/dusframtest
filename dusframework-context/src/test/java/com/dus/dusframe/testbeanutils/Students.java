package com.dus.dusframe.testbeanutils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Students {

	private List<String> struts = new ArrayList<String>();
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private BigDecimal bd = new BigDecimal(100);

	public List<String> getStruts() {
		return struts;
	}

	public void setStruts(List<String> struts) {
		this.struts = struts;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public BigDecimal getBd() {
		return bd;
	}

	public void setBd(BigDecimal bd) {
		this.bd = bd;
	}
	
	
}
