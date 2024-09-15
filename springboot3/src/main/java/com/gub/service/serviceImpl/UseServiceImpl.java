package com.gub.service.serviceImpl;

import com.gub.mapper.UserMapper;
import com.gub.pojo.User;
import com.gub.service.UserService;
import com.gub.utils.Md5Util;
import com.gub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.gub.utils.Md5Util.getMD5String;


@Service
public class UseServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUsername(String username) {



        User u = userMapper.findByUsername(username);

        return u;

    }

    @Override
    public void register(String username, String password) {

        String md5String =  Md5Util.getMD5String(password);


        userMapper.add(username, md5String);

    }

    @Override
    public void update(User user) {

        userMapper.update(user);

    }

    @Override
    public void updateAvatar(String url) {

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        //更新用户头像
        userMapper.updateAvatar(url, id);

    }

    @Override
    public void updatePwd(String newPassword) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updatePwd(newPassword, id);
    }


}
