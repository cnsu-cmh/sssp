package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.RoleRepository;
import com.xiaoshu.entity.Role;
import com.xiaoshu.util.PageRequestUtil;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	public Role findOneRole(Long roleId) throws Exception {
		return roleRepository.findOne(roleId);
	};


	public void deleteRole(Long roleid) throws Exception {
		roleRepository.delete(roleid);
	};

	public void addRole(Role t) throws Exception {
		roleRepository.save(t);
	};

	public void updateRole(Role t) throws Exception {
		roleRepository.save(t);
	};

	// 通过名称判断是否存在，（不能重名）
	public Role existRoleWithRoleName(String roleName) throws Exception {
		List<Role> roleList = roleRepository.findByRoleName(roleName);
		return roleList.isEmpty()?null:roleList.get(0);
	};

	// 批量删除
	public void deleteRoleByRoleIds(String[] roleIds) throws Exception {
		for (String roleid : roleIds) {
			roleRepository.delete(Long.parseLong(roleid));
		}
	}

	
	public Page<Role> PageRoleByRoleName(String roleName, Integer pageNumber, Integer pageSize, String ordername, String order) {
		Pageable pageable = PageRequestUtil.buildPageRequest(pageNumber, pageSize, order, new String[]{ordername});
		return roleRepository.findByRoleName(roleName,pageable);
	}


	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

}
