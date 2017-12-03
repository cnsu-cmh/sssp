package com.xiaoshu.dao;


import com.slyak.spring.jpa.GenericJpaRepository;
import com.xiaoshu.entity.Attachment;

public interface AttachmentRepository extends GenericJpaRepository<Attachment, Long>  {
}