package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.mapper.AdminMapper;
import org.blackist.web.springbootor.model.entity.system.Admin;
import org.blackist.web.springbootor.service.system.AdminService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.JedisCluster;

/**
 * TODO Admin服务实现类
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/8/31
 */
@Log4j2
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private final AdminMapper mapper;
	// @Resource
	// private final JedisCluster jedisCluster;

	public AdminServiceImpl(AdminMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	@Cacheable(value = "admins")
	public List<Admin> findAll() {
		log.debug("Query DB, Not From Redis Cache");
		return mapper.findAll();
	}

	@Override
	public Integer create(Admin admin) {
		return mapper.create(admin);
	}

	public String findRedis() {
		// jedisCluster.set("user", "UserA is Cool");
		// return jedisCluster.get("user");
		return null;
	}
}
