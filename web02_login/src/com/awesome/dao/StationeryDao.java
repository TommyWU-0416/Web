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
	public Stationery getStationery(int sid);
	
	/**
	 * 透過 uid 抓出數據庫的所有文具清單
	 * @param uid
	 * @return
	 */
	public List<Stationery> getStationeryList(int uid);
	
}
