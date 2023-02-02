package com.agencyapp.model;


import java.io.Serializable;

public class GoogleDriveFileDTO extends AbstractDTO<GoogleDriveFileDTO> implements Serializable {

    private String size;
    private String thumbnailLink;
    private boolean shared;
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getThumbnailLink() {
		return thumbnailLink;
	}
	public void setThumbnailLink(String thumbnailLink) {
		this.thumbnailLink = thumbnailLink;
	}
	public boolean isShared() {
		return shared;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}
    
}
