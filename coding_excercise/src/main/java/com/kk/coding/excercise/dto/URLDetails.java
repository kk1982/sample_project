package com.kk.coding.excercise.dto;

public class URLDetails {

	private String hostName;
	private String port;
	private String uriPath;
	private String queryString;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUriPath() {
		return uriPath;
	}
	public void setUriPath(String uriPath) {
		this.uriPath = uriPath;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("URL [hostName=").append(hostName).append(", port=").append(port).append(", uriPath=")
				.append(uriPath).append(", queryString=").append(queryString).append("]");
		return builder.toString();
	}
	
	
	
	
}
