package com.xiaoshu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.TokenRepository;
import com.xiaoshu.entity.UserToken;

@Service
public class TokenService {

	@Autowired
	TokenRepository tokenRepository;

	public void insertToken(UserToken t) {
		tokenRepository.save(t);
	};

	@SuppressWarnings("rawtypes")
	public UserToken findByTokenAndExpireTimeGreaterThanOrEqualTo(Map map) {
		String token = (String) map.get("token");
		Date expireTime = (Date) map.get("expireTime");
		List<UserToken> tokenList = tokenRepository.findByTokenAndExpireTimeGreaterThanEqual(token,expireTime);
		return tokenList.isEmpty() ? null : tokenList.get(0);
	};

}
