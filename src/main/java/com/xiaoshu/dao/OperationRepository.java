package com.xiaoshu.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.xiaoshu.entity.Operation;

public interface OperationRepository extends GenericJpaRepository<Operation, Long>  {

	List<Operation> findByMenuIdMenuId(Long menuId);

	Page<Operation> findByMenuIdMenuId(Long menuId,Pageable pageable);
}