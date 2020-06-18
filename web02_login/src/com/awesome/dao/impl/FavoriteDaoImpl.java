package com.awesome.dao.impl;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import com.awesome.dao.FavoriteDao;
import com.awesome.util.JDBCUtils;

/**
 * @author 吳awesome
 * @version 創建時間:2020年6月7日 下午5:30:11 類說明
 */
public class FavoriteDaoImpl implements FavoriteDao {
	
	private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

	@Override
	public void addFavorite(int sid, int uid, int number) {
		String sql = "insert into cart values(?, ?, ?)";
		try {
			for (int i = 0; i < number; i++) {
				queryRunner.update(sql, sid, new Date(), uid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("can't find");
		}
	}

}
