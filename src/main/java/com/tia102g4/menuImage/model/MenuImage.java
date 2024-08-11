package com.tia102g4.menuImage.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g4.rest.model.Restaurant;

@Entity
@Table(name = "menu_image")
public class MenuImage {
	@Id
	@Column(name = "menu_image_id", updatable = false)
	private Long menuImageId; // 餐廳圖片ID

	@Column(name = "image", columnDefinition = "LONGBLOB")
	private Byte[] image; // 餐廳圖片

	@Column(name = "image_name")
	private String imageName; // 圖片名稱

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rest_id", referencedColumnName = "rest_id")
	private Restaurant restaurant; // 餐廳ID

	public MenuImage() {
		super();
	}

	public MenuImage(Long menuImageId, Byte[] image, String imageName) {
		super();
		this.menuImageId = menuImageId;
		this.image = image;
		this.imageName = imageName;
	}

	public Long getMenuImageId() {
		return menuImageId;
	}

	public Byte[] getImage() {
		return image;
	}

	public String getImageName() {
		return imageName;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setMenuImageId(Long menuImageId) {
		this.menuImageId = menuImageId;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "MenuImage [menuImageId=" + menuImageId + ", image=" + Arrays.toString(image) + ", imageName="
				+ imageName + "]";
	}

}
