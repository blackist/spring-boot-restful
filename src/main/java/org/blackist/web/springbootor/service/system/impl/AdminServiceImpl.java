package org.blackist.web.springbootor.service.system.impl;

import org.blackist.web.springbootor.mapper.AdminMapper;
import org.blackist.web.springbootor.model.entity.system.Admin;
import org.blackist.web.springbootor.service.system.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO Admin服务实现类
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/8/31
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private final AdminMapper mapper;

	public AdminServiceImpl(AdminMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Admin> findAll() {
		return mapper.findAll();
	}

	@Override
	public Integer create(Admin admin) {
		return mapper.create(admin);
	}
}
