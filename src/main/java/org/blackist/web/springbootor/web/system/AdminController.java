package org.blackist.web.springbootor.web.system;

import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.model.entity.system.Admin;
import org.blackist.web.springbootor.service.system.AdminService;
import org.blackist.web.springbootor.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO Admin Controller
 *
 * @author L.L Dong<liangl.dong@qq.com>
 * @since 2019/8/31
 */
@RestController
@RequestMapping("/admins")
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/getAll")
	public Response getAllAdmins() {

		return Response.SUCCESS(adminService.findAll());
	}

	@PostMapping
	public Response createAdmin(String name, int age) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setAge(age);
		if (adminService.create(admin) > 0) {
			return Response.SUCCESS(admin);
		} else {
			return Response.PARAM_ERROR();
		}
	}

	@GetMapping("/getRedis")
	public Response getRedis() {
		return Response.SUCCESS(adminService.findRedis());
	}
}
