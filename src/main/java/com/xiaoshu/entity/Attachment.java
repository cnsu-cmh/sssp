package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Attachment implements Serializable {
    /**
     * 附件ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long attachmentId;

    /**
     * 名称
     */
    private String attachmentName;

    /**
     * 路径
     */
    private String attachmentPath;

    /**
     * 时间
     */
    private Date attachmentTime;

    private static final long serialVersionUID = 1L;

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName == null ? null : attachmentName.trim();
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
	}

	public Date getAttachmentTime() {
		return attachmentTime;
	}

	public void setAttachmentTime(Date attachmentTime) {
		this.attachmentTime = attachmentTime;
	}

}