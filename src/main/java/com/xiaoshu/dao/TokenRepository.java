package com.xiaoshu.dao;

import java.util.Date;
import java.util.List;

import com.slyak.spring.jpa.GenericJpaRepository;
import com.xiaoshu.entity.UserToken;

public interface TokenRepository extends GenericJpaRepository<UserToken, Long>  {

	List<UserToken> findByTokenAndExpireTimeGreaterThanEqual(String token, Date expireTime);
}