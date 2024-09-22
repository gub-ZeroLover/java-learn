//导入封装好的request.js
import request from "@/utils/request";

// 提供调用接口的方法
export const userRegisterService = (registerDate) => {
  //借助URLserarchParams传递数据
  const params = new URLSearchParams();
  for (let key in registerDate) {
    params.append(key, registerDate[key]);
  }
  return request.post("/user/register", params);
};

export const userLoginService = (loginDate) =>{
    const params = new URLSearchParams();
    for (let key in loginDate){
        params.append(key, loginDate[key])
    }
    return request.post('/user/login', params)
}

//获取用户信息
export const userInfoGetService = ()=>{
  return request.get('/user/userInfo');
}

//修改用户信息
export const userUpdateService = (updateData) => {
  // const params = new URLSearchParams();
  // for (let key in updateData){
  //   params.append(key, updateData[key])
  // }
  return request.put('/user/update', updateData)
}

//修改用户头像
export const userAvatarUpdateService = (url) => {
  let params = new URLSearchParams()
  params.append('avatar', url)
  return request.patch('/user/updateAvatar', params)
}