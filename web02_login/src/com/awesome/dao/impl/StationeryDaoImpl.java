package com.awesome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.awesome.dao.StationeryDao;
import com.awesome.domain.Stationery;
import com.awesome.util.BaseDao;
import com.awesome.util.JDBCUtils;


/**
* @author  吳awesome	
* @version 創建時間:2020年5月29日 下午2:59:41
* 類說明
*/
public class StationeryDaoImpl implements StationeryDao {

	QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
	Stationery stationery = new Stationery();
	/**
	 * 按照商品編號進行查詢
	 */
	@Override
	public Stationery getStationery(Integer StationeryId) {
		try {
			String sql = "select * from stationery where StationeryId = ?";
			stationery = queryRunner.query(sql, new BeanHandler<Stationery>(Stationery.class), StationeryId);
			
			System.out.println(stationery.toString());
			return stationery;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存使用者的購物清單
	 */
	@Override
	public void savaCart(Stationery stationery) {
	}
}
