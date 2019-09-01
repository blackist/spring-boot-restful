package org.blackist.web.springbootor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.blackist.web.springbootor.model.entity.system.Admin;

import java.util.List;

/**
 *
 */
@Mapper
public interface AdminMapper {

	List<Admin> findAll();

	Integer create(Admin admin);
}
