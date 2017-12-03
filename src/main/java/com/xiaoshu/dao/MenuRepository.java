package com.xiaoshu.dao;

import java.util.List;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.xiaoshu.entity.Menu;

public interface MenuRepository extends GenericJpaRepository<Menu, Long>  {

	List<Menu> findByParentId(long parentId);

	long countByParentId(long parentId);
}