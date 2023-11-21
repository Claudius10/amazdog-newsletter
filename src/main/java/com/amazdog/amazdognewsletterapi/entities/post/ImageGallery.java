package com.amazdog.amazdognewsletterapi.entities.post;

import java.io.Serializable;

public class ImageGallery implements Serializable {

	private String imageName;

	private String imageLink;

	public ImageGallery() {
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public String toString() {
		return "ImageGallery{" +
				"imageName='" + imageName + '\'' +
				", imageLink='" + imageLink + '\'' +
				'}';
	}
}
