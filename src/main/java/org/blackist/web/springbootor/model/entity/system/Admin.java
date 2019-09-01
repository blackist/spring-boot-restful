package org.blackist.web.springbootor.model.entity.system;

/**
 * TODO 管理员实体
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/8/31
 */
public class Admin {

	private int id;
	private String name;
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
