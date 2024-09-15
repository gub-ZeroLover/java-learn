package com.gub.service;

import com.gub.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {

    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String url);

    void updatePwd(String newPassword);
}
