package org.blackist.web.springbootor.model.entity.system;

import java.io.Serializable;

import lombok.Data;

/**
 * TODO 管理员实体
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/8/31
 */
@Data
public class Admin implements Serializable {

	private static final long serialVersionUID = -3307220266299805421L;

	private int id;
	private String name;
	private int age;
}
