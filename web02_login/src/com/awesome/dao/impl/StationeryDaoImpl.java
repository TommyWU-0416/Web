package com.awesome.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.awesome.dao.StationeryDao;
import com.awesome.domain.Stationery;
import com.awesome.util.JDBCUtils;

/**
 * @author 吳awesome
 * @version 創建時間:2020年5月29日 下午2:59:41 類說明
 */
public class StationeryDaoImpl implements StationeryDao {

	QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
	Stationery stationery = new Stationery();

	/**
	 * 按照商品編號進行查詢
	 */
	@Override
	public Stationery getStationery(int sid) {
		try {
			String sql = "select * from stationery where sid = ?";
			stationery = queryRunner.query(sql, new BeanHandler<Stationery>(Stationery.class), sid);
			return stationery;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Stationery> getStationeryList(int uid) {
		List<Stationery> stationeryList = null;

		String sql = "select * from cart where uid = ?";
		List<Stationery> list = null;
		try {
			list = queryRunner.query(sql, new BeanListHandler<Stationery>(Stationery.class), uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Stationery> list2 = new ArrayList(list.size());
		
		for (int i = 0; i < list.size(); i++) {
			
			list2.add(i, getStationery(list.get(i).getSid()));
		}

		return list2;
	}
}
