package com.ssafy.fitty.security;

public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public JwtToken(String grantType, String accessToken, String refreshToken) {
		super();
		this.grantType = grantType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	public JwtToken() {
	}
	@Override
	public String toString() {
		return "JwtToken [grantType=" + grantType + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken
				+ "]";
	}
    
}
