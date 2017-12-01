package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

public class Menu implements Serializable {
    
	/**
     * 菜单ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long menuId;

    /**
     * 名称
     */
    private String menuName;

    /**
     * 方法
     */
    private String menuUrl;
    
    /**
     * 所有孩子菜单
     */
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=MenuChildren.class,orphanRemoval=true)
    @JoinColumn(name="childrenIds",referencedColumnName="menuId")
    private Set<MenuChildren> children;

    /**
     * 描述
     */
    private String menuDescription;

    /**
     * 状态
     */
    private String state;

    /**
     * 图标
     */
    private String iconCls;

    /**
     * 顺序排序
     */
    private Integer seq;

    private static final long serialVersionUID = 1L;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Set<MenuChildren> getChildren() {
		return children;
	}

	public void setChildren(Set<MenuChildren> children) {
		this.children = children;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription == null ? null : menuDescription.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls == null ? null : iconCls.trim();
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	

    
}