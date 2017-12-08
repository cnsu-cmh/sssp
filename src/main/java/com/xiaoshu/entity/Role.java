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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 菜单IDs
     */
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.EAGER)
    @JoinTable(name="roleMenuIds",joinColumns = { @JoinColumn(name ="roleId" )}, inverseJoinColumns = { @JoinColumn(name = "menuId") })
    @OrderBy("seq")
    private Set<Menu> menuIds;

    /**
     * 按钮IDS
     */
    @ManyToMany(cascade =  {CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.EAGER)
    @JoinTable(name="roleOperationIds",joinColumns = { @JoinColumn(name ="roleId" )}, inverseJoinColumns = { @JoinColumn(name = "operationId") })
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