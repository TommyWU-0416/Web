package com.awesome.service;
/**
* @author  吳awesome	
* @version 創建時間:2020年6月7日 下午4:29:23
* 類說明
*/
public interface FavoriteService {

	/**
	 * 透過 sid 以及 uid 將購物車內容添加到數據庫
	 * @param rid
	 * @param uid
	 */
	public void addFavorite(String sid, int uid);

	
}
