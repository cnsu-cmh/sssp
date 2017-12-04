package com.xiaoshu.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.LogRepository;
import com.xiaoshu.entity.Log;
import com.xiaoshu.util.PageRequestUtil;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;

@Service
public class LogService {
	
	@Autowired
	LogRepository logRepository;

	public void insertLog(Log t) throws Exception {
		logRepository.save(t);
	};

	public Page<Log> pageLogCreateBetween(String start, String end, Log log, int pageNumber, int pageSize, String ordername, String order) throws Exception {
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"logId";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		Calendar cal = Calendar.getInstance();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01 00:00:00");
		cal.setTime(startDate);
		Date startTime = cal.getTime();
		Date endTime = new Date();
		if(StringUtil.isNotEmpty(start)){
			startTime = TimeUtil.ParseTime(start, "yyyy-MM-dd HH:mm:ss");
		}
		if(StringUtil.isNotEmpty(end)){
			endTime = TimeUtil.ParseTime(end, "yyyy-MM-dd HH:mm:ss");
		}
		
		Map<String,Object> conditionMap = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(log.getModule())){
			conditionMap.put("module","%"+log.getModule()+"%");
		}
		if(StringUtil.isNotEmpty(log.getOperation())){
			conditionMap.put("operation","%"+log.getOperation()+"%");
		}
		if(StringUtil.isNotEmpty(log.getUsername())){
			conditionMap.put("operation","%"+log.getOperation()+"%");
		}
		conditionMap.put("startTime",startTime);
		conditionMap.put("endTime",endTime);
		Pageable pageable = PageRequestUtil.buildPageRequest(pageNumber,pageSize,order,new String[]{ordername});
		return logRepository.pageLogCreateBetween(conditionMap,pageable);
	};

	public void deleteLog(long l) throws Exception {
		logRepository.delete(l);
	};

	public void truncateLog() throws Exception {
		logRepository.truncateTable();
	}

	public List<Log> findAllLog() {
		return logRepository.findAll();
	};
}
