package com.gub.controller;

import com.gub.pojo.Result;
import com.gub.pojo.User;
import com.gub.service.UserService;
import com.gub.utils.JwtUtil;
import com.gub.utils.Md5Util;
import com.gub.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,6}$") String username, @Pattern(regexp = "^\\S{3,10}$") String password) {

        User u = userService.findByUsername(username);

        if(u == null){
            userService.register(username, password);
            return Result.success();
        }else{
            return Result.error("用户名已存在");
        }
    }
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{3,6}") String username, @Pattern(regexp = "^\\S{3,10}$") String password){

        User loginUser = userService.findByUsername(username);
        if (loginUser == null){
            return Result.error("用户名错误");
        }else{
            if(Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
                //登录成功
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", loginUser.getId());
                claims.put("username", loginUser.getUsername());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            }else{
                return Result.error("密码错误");
            }
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        //这里通过ThreadLocal获取用户信息，在拦截器中创造和销毁
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result<User> update(@RequestBody @Validated User user){

        userService.update(user);

        return Result.success();

    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){

        userService.updateAvatar(avatarUrl);

        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params){

        //controller 参数校验， service写业务逻辑

        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if(!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)){
            return Result.error("未填写完整");
        }

        //比对旧密码是否正确
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        if(!Md5Util.getMD5String(old_pwd).equals(user.getPassword())){
            return Result.error("旧密码错误");
        }

        if(!new_pwd.equals(re_pwd)){
            return Result.error("两次密码不一致");
        }

        userService.updatePwd(Md5Util.getMD5String(new_pwd));

        return Result.success();
    }


}
