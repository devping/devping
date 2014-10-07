package org.jbug.devping.vo;

public class DataVo {
	private String func;
	private String date;
	
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "DataVo [func=" + func + ", date=" + date + "]";
	}
}
