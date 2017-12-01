package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

public class User implements Serializable {
    
	/**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private Byte userType;

    /**
     * 角色
     */
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Role.class)
    @JoinColumn(name="roleId",referencedColumnName="roleId")
    private Role roleId;

    /**
     * 描述信息
     */
    private String userDescription;

    private static final long serialVersionUID = 1L;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
    
    
}