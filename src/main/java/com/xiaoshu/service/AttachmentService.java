package com.xiaoshu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.AttachmentRepository;
import com.xiaoshu.entity.Attachment;
import com.xiaoshu.util.PageRequestUtil;


@Service
public class AttachmentService {
	
	@Autowired
	AttachmentRepository attachmentRepository;
	
	public void insertAttachment(Attachment t) throws Exception{
		attachmentRepository.save(t);
	};
	
	public Page<Attachment> findAttachment(int pageNumber, int pageSize) throws Exception{
		Pageable pageable = PageRequestUtil.buildPageRequest(pageNumber,pageSize,null,null);
		return attachmentRepository.findAll(pageable);
	};
	
}
