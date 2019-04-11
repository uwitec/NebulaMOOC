package com.nebula.mooc.ssoserver.service.impl;

import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.ssoserver.core.model.UserInfo;
import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static List<UserInfo> mockUserList = new ArrayList<>();

    static {
        for (int i = 0; i < 5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserid(1000 + i);
            userInfo.setUsername("user" + (i > 0 ? String.valueOf(i) : ""));
            userInfo.setPassword("123456");
            mockUserList.add(userInfo);
        }
    }

    @Override
    public Return<UserInfo> findUser(String username, String password) {

        if (username == null || username.trim().length() == 0) {
            return new Return<UserInfo>(Return.ERROR_CODE, "Please input username.");
        }
        if (password == null || password.trim().length() == 0) {
            return new Return<UserInfo>(Return.ERROR_CODE, "Please input password.");
        }

        // mock user
        for (UserInfo mockUser : mockUserList) {
            if (mockUser.getUsername().equals(username) && mockUser.getPassword().equals(password)) {
                return new Return<UserInfo>(mockUser);
            }
        }

        return new Return<UserInfo>(Return.ERROR_CODE, "username or password is invalid.");
    }


}
