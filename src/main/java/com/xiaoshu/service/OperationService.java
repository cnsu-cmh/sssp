package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.OperationRepository;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.util.PageRequestUtil;

@Service
public class OperationService {

	@Autowired
	OperationRepository operationRepository;
	

	public void addOperation(Operation t) throws Exception {
		operationRepository.save(t);
	};

	public void updateOperation(Operation t) throws Exception {
		operationRepository.save(t);
	};

	public void deleteOperation(Long operationId) throws Exception {
		operationRepository.delete(operationId);
	}

	public List<Operation> findByMenuId(Long menuId) {
		return operationRepository.findByMenuIdMenuId(menuId);
	}


	public Page<Operation> pageByMenuId(Long menuId, Integer pageNumber, Integer pagzSize) {
		Pageable pageable = PageRequestUtil.buildPageRequest(pageNumber, pagzSize, null, null);
		return operationRepository.findByMenuIdMenuId(menuId,pageable);
	}

	public Operation findByOperationId(long operationId) {
		return operationRepository.findOne(operationId);
	}

}