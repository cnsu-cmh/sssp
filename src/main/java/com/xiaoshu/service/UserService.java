package com.xiaoshu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.UserRepository;
import com.xiaoshu.entity.User;
import com.xiaoshu.util.PageRequestUtil;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// 通过ID查询
	public User findOneUser(Long id) throws Exception {
		return userRepository.findOne(id);
	};

	// 新增
	public void addUser(User t) throws Exception {
		userRepository.save(t);
	};

	// 修改
	public void updateUser(User t) throws Exception {
		userRepository.save(t);
	};

	// 删除
	public void deleteUser(Long id) throws Exception {
		userRepository.delete(id);
	};

	// 登录
	public User loginUser(User user) throws Exception {
		List<User> userList = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		return userList.isEmpty() ? null : userList.get(0);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public User existUserWithUserName(String username) throws Exception {
		List<User> userList = userRepository.findByUsername(username);
		return userList.isEmpty()?null:userList.get(0);
	};

	// 通过角色判断是否存在
	public User existUserWithRoleId(Long roleId) throws Exception {
		List<User> userList = userRepository.findByRoleIdRoleId(roleId);
		return userList.isEmpty()?null:userList.get(0);
	}

	
	public Page<UserVo> findUserPage(String username, String roleId,int pageNumber, int pageSize, String ordername, String order) {
		Map<String,Object> conditionMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(username)) {
			conditionMap.put("username","%"+username+"%");
		}
		if (StringUtil.isNotEmpty(roleId) && !"0".equals(roleId)) {
			conditionMap.put("roleId",roleId);
		}
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"userId";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		Pageable pageable = PageRequestUtil.buildPageRequest(pageNumber, pageSize, order, new String[]{ordername});
		return userRepository.findUsernameAndRole(conditionMap,pageable);
	}


}
