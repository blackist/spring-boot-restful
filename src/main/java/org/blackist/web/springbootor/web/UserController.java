package org.blackist.web.springbootor.web;

import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.common.response.SuccessReponse;
import org.blackist.web.springbootor.entity.User;
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
public class UserController extends  BaseController {

    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @GetMapping("/{id}")
    public Response getUser(@PathVariable("id") Long id) {
        return Response.SUCCESS(users.get(id));
    }

    @GetMapping("/")
    public Response queryUsers() {
        return new SuccessReponse(new ArrayList<>(users.values()));
    }

    @PostMapping("/")
    public Response createUser(@RequestBody User user) {
        user.setId(new Random().nextLong());
        users.put(user.getId(), user);
        return Response.SUCCESS(user);
    }

    @PutMapping("/")
    public Response updateUser(@RequestBody User user) {
        if (user.getId() != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return Response.SUCCESS(user);
        } else {
            return Response.PARAM_ERROR();
        }
    }

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
