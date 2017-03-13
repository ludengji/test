package com.local.test.user;

import com.local.test.user.model.User;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by meisei on 2017/3/13.
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Object createUser(@RequestBody User user) {
        return user;
    }
}
