package org.blackist.web.springbootor.web.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.common.response.SuccessReponse;
import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.service.system.UserService;
import org.blackist.web.springbootor.web.BaseController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 liangl.dong@qq.com.
 * @Date 2019/2/4 15:17.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @ApiOperation("用户单个获取")
    @ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true,
            dataType = "Long",
            paramType = "path")
    @GetMapping("/{id}")
    public Response getUser(@PathVariable("id") Long id) {
        logger.info("userId: " + id);
        User user = userService.findById(id);
        return user != null ? Response.SUCCESS(user) : Response.DATA_NULL();
    }

    @ApiOperation("用户条件查询")
    @GetMapping("/")
    public Response queryUsers() {
        return new SuccessReponse(userService.findAll());
    }

    @ApiOperation("用户创建")
    @ApiImplicitParam(
            name = "system",
            value = "用户信息",
            required = true,
            dataType = "Json",
            paramType = "body")
    @PostMapping("/")
    public Response createUser(@RequestBody User user) {


        return Response.SUCCESS(userService.save(user));
    }

    @ApiOperation("用户更新")
    @PutMapping("/")
    public Response updateUser(@RequestBody User user) {
        return Response.SUCCESS(userService.save(user));
    }

    @ApiOperation("用户删除")
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") Long id) {
        if (id != null) {
            User user = userService.findById(id);
            if (user != null) {
                userService.deleteById(id);
                return Response.SUCCESS(id);
            } else {
                logger.error("User Not exists");
                return Response.PARAM_ERROR().message("User Not exists");
            }
        } else {
            return Response.PARAM_NULL();
        }
    }

    private void testUsers() {

    }
}
