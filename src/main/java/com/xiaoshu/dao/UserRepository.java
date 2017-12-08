package com.xiaoshu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;
import com.xiaoshu.entity.User;
import com.xiaoshu.vo.UserVo;


public interface UserRepository extends GenericJpaRepository<User, Long>  {

	List<User> findByUsernameAndPassword(String username, String password);

	List<User> findByUsername(String username);

	List<User> findByRoleIdRoleId(Long roleId);

	@TemplateQuery
	Page<UserVo> findUsernameAndRole(@Param(value = "condition")Map<String, Object> conditionMap, Pageable pageRequest);
}