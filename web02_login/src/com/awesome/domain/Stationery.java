package com.awesome.domain;

import java.io.Serializable;

/**
 * @author 吳awesome *2020年5月29日下午2:54:59
 */
public class Stationery implements Serializable {

	// 實體類實現序列化，避免後面出現異常
	private static final long serialVersionUID = 1L;
	private Integer stationeryId;
	private String stationeryName;
	private Double price;
	private String author;
	private String pic;
	private String publish;

	public Stationery() {
		super();
	}

	public Stationery(Integer stationeryId, String stationeryName, Double price, String author, String pic,
			String publish) {
		super();
		this.stationeryId = stationeryId;
		this.stationeryName = stationeryName;
		this.price = price;
		this.author = author;
		this.pic = pic;
		this.publish = publish;
	}

	@Override
	public String toString() {
		return "Stationery [stationeryId=" + stationeryId + ", stationeryName=" + stationeryName + ", price=" + price
				+ ", author=" + author + ", pic=" + pic + ", publish=" + publish + "]";
	}

	public Integer getStationeryId() {
		return stationeryId;
	}

	public void setStationeryId(Integer stationeryId) {
		this.stationeryId = stationeryId;
	}

	public String getStationeryName() {
		return stationeryName;
	}

	public void setStationeryName(String stationeryName) {
		this.stationeryName = stationeryName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}