package com.kk.coding.excercise.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="urlinfo")
public class URLInfo {
	@Id
	private String id;
	private String value;
	
	public URLInfo (String value) {
		this.value=value;
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("URLInfo [id=").append(id).append(", value=").append(value).append("]");
		return builder.toString();
	}
	
	
	

}
