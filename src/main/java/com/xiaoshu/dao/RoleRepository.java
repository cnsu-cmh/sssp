package com.xiaoshu.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.xiaoshu.entity.Role;

public interface RoleRepository extends GenericJpaRepository<Role, Long>  {

	List<Role> findByRoleName(String roleName);

	Page<Role> findByRoleNameLike(String roleName, Pageable pageable);

}