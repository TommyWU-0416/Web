package com.awesome.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awesome.dao.StationeryDao;
import com.awesome.domain.Stationery;
import com.awesome.util.BaseDao;


/**
* @author  吳awesome	
* @version 創建時間:2020年5月29日 下午2:59:41
* 類說明
*/
public class StationeryDaoImpl implements StationeryDao {

	@Override
	public List<Stationery> select(String sql, Object[] arr) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = BaseDao.getCon();
			ps = con.prepareStatement(sql);
			if( arr != null) {
				for (int i = 0; i < arr.length; i++) {
					ps.setObject(i+1, arr[i]);
				}
			}
			rs = ps.executeQuery();
			List<Stationery> list = new ArrayList<Stationery>();
			while(rs.next()) {
				Stationery stationery = new Stationery();
				stationery.setStationeryId(rs.getInt("Stationeryid"));
				stationery.setStationeryName(rs.getString("Stationeryname"));
				stationery.setPrice(rs.getDouble("price"));
				stationery.setAuthor(rs.getString("author"));
				stationery.setPic(rs.getString("pic"));
				stationery.setPublish(rs.getString("publish"));
				
				list.add(stationery);
			}
			
			return list;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(con, ps, rs);
		}
		return null;
	}

	@Override
	public Stationery getStationery(Integer id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = BaseDao.getCon();
			String sql = "select * from stationery where StationeryId = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Stationery stationerys = new Stationery();
				stationerys.setStationeryId(rs.getInt("StationeryId"));
				stationerys.setStationeryName(rs.getString("StationeryName"));
				stationerys.setPrice(rs.getDouble("price"));
				stationerys.setAuthor(rs.getString("author"));
				stationerys.setPic(rs.getString("pic"));
				stationerys.setPublish(rs.getString("publish"));
				
				return stationerys;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.close(con, ps, rs);
		}
		
		return null;
	}
}
