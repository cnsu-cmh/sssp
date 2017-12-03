package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Log implements Serializable {
    /**
     * 日志
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long logId;

    /**
     * 操作人
     */
    private String username;

    /**
     * 时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 操作类型（增删改）
     */
    private String operation;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 详细
     */
    private String content;

    private static final long serialVersionUID = 1L;

    
	public Log() {
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null :username.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation == null ? null :operation.trim();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip  == null ? null :ip.trim();
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module  == null ? null :module.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content  == null ? null :content.trim();
	}

	
   
}