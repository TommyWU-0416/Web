package com.awesome.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.awesome.dao.StationeryDao;
import com.awesome.dao.impl.StationeryDaoImpl;
import com.awesome.domain.Stationery;
import com.awesome.service.StationeryService;





/**
* @author  吳awesome	
* @version 創建時間:2020年5月29日 下午3:11:13
* 類說明
*/
public class StationeryServiceImpl implements StationeryService {
	
	private StationeryDao dao=new StationeryDaoImpl();

	/**
	 * 使用 stationeryId 去存取 stationery
	 */
	@Override
	public Stationery getStationery(Stationery stationery) {
		if(stationery.getStationeryId() != null && stationery.getStationeryId() != 0) {
			return dao.getStationery(stationery.getStationeryId());
		}
		return null;
	}

}
