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
	 * 圖書的查詢方法
	 * @param sql
	 * @param arr
	 * @return
	 */
	public List<Stationery> select(String sql, Object[] arr);
	
	/**
	 * 按照圖書編號進行查詢
	 * @param id
	 * @return
	 */
	public Stationery getStationery(Integer stationeryid);
}
