package org.blackist.web.springbootor.common.redis;

import lombok.Data;

/**
 * TODO 测试Redis对象存储
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/9/4
 */
@Data
class RedisUser {

	private String uuid;
	private String name;
	private int age;
	private String sex;

	RedisUser(String uuid, String name, int age, String sex) {
		this.uuid = uuid;
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
}
