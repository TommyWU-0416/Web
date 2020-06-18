package com.awesome.service.impl;



import org.apache.commons.dbutils.QueryRunner;

import com.awesome.dao.FavoriteDao;
import com.awesome.dao.impl.FavoriteDaoImpl;
import com.awesome.service.FavoriteService;
import com.awesome.util.JDBCUtils;

/**
* @author  吳awesome	
* @version 創建時間:2020年6月7日 下午4:30:44
* 類說明
*/
public class FavoriteServiceImpl implements FavoriteService {

	private FavoriteDao favoriteDao = new FavoriteDaoImpl();
	
	@Override
	public void addFavorite(String sid, int uid, int number) {
		favoriteDao.addFavorite(Integer.parseInt(sid), uid, number);
		
	}
	

}
