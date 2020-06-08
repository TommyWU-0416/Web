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
	
	private StationeryDao stationeryDao=new StationeryDaoImpl();

	/**
	 * 使用 Sid 去存取 stationery
	 */
	@Override
	public Stationery getStationery(Stationery stationery) {
		if(stationery.getSid() != null && stationery.getSid() != 0) {
			return stationeryDao.getStationery(stationery.getSid());
		}
		return null;
	}

	@Override
	public List<Stationery> getStationeryList(int uid) {
		List<Stationery> stationeryList = stationeryDao.getStationeryList(uid);
		return stationeryList;
	}
}
