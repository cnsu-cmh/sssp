package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class UserToken implements Serializable {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long tokenId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户（md5）
     */
    private String userAgent;

    /**
     * md5(username+md5(useragent))
     */
    private String token;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 到期时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date expireTime;

    private static final long serialVersionUID = 1L;

    
	public UserToken() {
	}

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent == null ? null : userAgent.trim();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

    
}