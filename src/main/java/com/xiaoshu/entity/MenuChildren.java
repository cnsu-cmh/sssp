package com.xiaoshu.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MenuChildren  implements Serializable {
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
     * 父菜单
     */
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Menu.class)
    @JoinColumn(name="parentId",referencedColumnName="menuId")
    private Menu parent;

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

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
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