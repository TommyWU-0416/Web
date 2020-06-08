package com.awesome.service;

import java.util.List;

import com.awesome.domain.Stationery;



/**
* @author  吳awesome	
* @version 創建時間:2020年5月29日 下午3:09:34
* 類說明
*/
public interface StationeryService {
	
	/**
	 * 根據id進行查詢
	 * @param Stationery
	 * @return
	 */
	public Stationery getStationery(Stationery stationery);

	/**
	 * 透過使用者 uid 獲取存取的 stationeryList
	 * @param uid
	 * @return
	 */
	public List<Stationery> getStationeryList(int uid);
	
}
