package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Role implements Serializable {
	
    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 菜单IDs
     */
    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Menu.class)
    @JoinColumn(name="menuIds",referencedColumnName="roleId")
    private Set<Menu> menuIds;

    /**
     * 按钮IDS
     */
    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Operation.class)
    @JoinColumn(name="operationIds",referencedColumnName="roleId")
    private Set<Operation> operationIds;

    /**
     * 描述
     */
    private String roleDescription;
    
    private static final long serialVersionUID = 1L;

    
	public Role() {
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public Set<Menu> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Set<Menu> menuIds) {
		this.menuIds = menuIds;
	}

	public Set<Operation> getOperationIds() {
		return operationIds;
	}

	public void setOperationIds(Set<Operation> operationIds) {
		this.operationIds = operationIds;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription == null ? null : roleDescription.trim();
	}
    
}