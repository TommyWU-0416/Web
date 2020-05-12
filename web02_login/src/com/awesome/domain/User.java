package com.awesome.domain;

/**
 * 用於封裝數據的JavaBean
 * 
 * @author 吳思億
 * @version 創建時間：2020年1月21日 下午5:48:08
 * @since JDK8.0
 */
public class User {
	private Integer uid;
	private String username;
	private String password;
	private String nickname;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
