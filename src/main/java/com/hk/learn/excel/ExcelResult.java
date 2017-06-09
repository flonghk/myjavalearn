package com.hk.learn.excel;
//未使用
public class ExcelResult {
	
	private String casetitle = "";
	private String casenumber = "";
	private String producerType = "";
	private String orderId = "";
	private String firstCount = "";
	private String nextCount = "";
	
	public String getCaseTitle()
	{
		return casetitle;
	}
	public void setCaseTitle(String casetitle)
	{
		this.casetitle = casetitle;
	}
	
	public String getCaseNumber()
	{
		return casenumber;
	}
	public void setCaseNumber(String casenumber)
	{
		System.out.println("Start:");
		this.casenumber = casenumber;
	}
	
	public String getProducerType()
	{
		return producerType;
	}
	public void setProducerType(String producerType)
	{
		this.producerType = producerType;
	}
	
	public String getOrderId()
	{
		return orderId;
	}
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	
	public String getFirstCount()
	{
		return firstCount;
	}
	public void setFirstCount(String firstCount)
	{
		this.firstCount = firstCount;
	}
	
	public String getNextCount()
	{
		return nextCount;
	}
	public void setNextCount(String nextCount)
	{
		this.nextCount = nextCount;
	}
}
