package org.blackist.web.springbootor.service.system;

import org.blackist.web.springbootor.model.entity.system.Admin;

import java.util.List;

/**
 *
 */
public interface AdminService {

	List<Admin> findAll();

	Integer create(Admin admin);
}
