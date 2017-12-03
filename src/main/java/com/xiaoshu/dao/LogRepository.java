package com.xiaoshu.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.slyak.spring.jpa.TemplateQuery;
import com.xiaoshu.entity.Log;

public interface LogRepository extends GenericJpaRepository<Log, Long>  {

	@Modifying
    @Query(value = "truncate table Log",nativeQuery=true)
	void truncateTable();

	@TemplateQuery
	Page<Log> pageLogCreateBetween(@Param(value = "condition")Map<String, Object> conditionMap, Pageable pageable);
}