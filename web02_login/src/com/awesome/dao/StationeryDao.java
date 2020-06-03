package com.awesome.dao;

import java.util.List;

import com.awesome.domain.Stationery;

/**
* @author  吳awesome	
* @version 創建時間:2020年5月29日 下午2:57:24
* 類說明
*/
public interface StationeryDao {
	
	/**
	 * 按照商品編號進行查詢
	 * @param id
	 * @return
	 */
	public Stationery getStationery(Integer stationeryid);
	
	/**
	 * 保存使用者的購物清單
	 * @param stationery
	 */
	public void savaCart(Stationery stationery);
	
}
