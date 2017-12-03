package com.xiaoshu.service;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.MenuRepository;
import com.xiaoshu.entity.Menu;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;

	public void addMenu(Menu menu) throws Exception {
		menuRepository.save(menu);
	};

	public void updateMenu(Menu menu) throws Exception {
		menuRepository.save(menu);
	};

	public void deleteMenu(Long menuid) throws Exception {
		menuRepository.delete(menuid);
	};

	public Menu findByMenuId(Long menuid) {
		return menuRepository.findOne(menuid);
	}

	public Set<Menu> menuTree(Map map) {
		Long parentId = (Long)map.get("parentId");
		Set<Menu> menuSet = (Set<Menu>) map.get("menuIds");
		Set<Menu> menus = menuSet.stream().filter(m -> m.getParentId().compareTo(parentId) == 0).collect(Collectors.toSet());
		return menus;
	}

	public List<Menu> findByParentId(long parentId) {
		return menuRepository.findByParentId(parentId);
	}

	public long countMenuByParentId(long parentId) {
		return menuRepository.countByParentId(parentId);
	}

}
