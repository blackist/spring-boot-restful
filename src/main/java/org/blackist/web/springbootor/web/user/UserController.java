package org.blackist.web.springbootor.web.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.common.response.SuccessReponse;
import org.blackist.web.springbootor.entity.user.User;
import org.blackist.web.springbootor.web.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * TODO ${TODO}
 *
 * @author 董亮亮 liangl.dong@qq.com.
 * @Date 2019/2/4 15:17.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation("用户单个获取")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public Response getUser(@PathVariable("id") Long id) {
        System.out.println(id);
        int a = 5 / 0;
        return Response.SUCCESS(users.get(id));
    }

    @ApiOperation("用户条件查询")
    @GetMapping("/")
    public Response queryUsers() {
        return new SuccessReponse(new ArrayList<>(users.values()));
    }

    @ApiOperation("用户创建")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "Json", paramType = "body")
    @PostMapping("/")
    public Response createUser(@RequestBody User user) {
        user.setId(new Random().nextLong());
        users.put(user.getId(), user);
        return Response.SUCCESS(user);
    }

    @ApiOperation("用户更新")
    @PutMapping("/")
    public Response updateUser(@RequestBody User user) {
        if (user.getId() != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return Response.SUCCESS(user);
        } else {
            return Response.PARAM_ERROR();
        }
    }

    @ApiOperation("用户删除")
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") Long id) {
        if (id != null) {
            users.remove(id);
            return Response.SUCCESS(id);
        } else {
            return Response.PARAM_NULL();
        }
    }
}
