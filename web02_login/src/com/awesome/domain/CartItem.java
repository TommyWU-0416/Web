package com.awesome.domain;
/**
* @author  吳awesome	
* @version 創建時間:2020年5月29日 下午3:37:30
* 類說明
*/
public class CartItem {
	private Stationery stationery;
	private Integer number;
	
	public Stationery getStationery() {
		return stationery;
	}
	
	public void setStationery(Stationery stationery) {
		this.stationery = stationery;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "CartItem [stationery=" + stationery + ", number=" + number + "]";
	}
	
}
