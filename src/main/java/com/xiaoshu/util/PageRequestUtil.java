package com.xiaoshu.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageRequestUtil {

	/**
	 * 构建PageRequest因子
	 * @param pageNumber
	 * @param pagzSize
	 * @param sortType
	 * @param sortParam
	 * @return
	 */
	public static Pageable buildPageRequest(int pageNumber, int pageSize, String sortType, String[] sortParam) {
		Sort sort = null;
		if(!StringUtil.stringArrIsEmpty(sortParam)){
			if(StringUtil.isEmpty(sortType)){
				sort = new Sort(Direction.DESC, sortParam);
			}else{
				sort = new Sort(Direction.fromString(sortType), sortParam);
			}
		}
		return new PageRequest(pageNumber - 1, pageSize, sort);
	}
}
