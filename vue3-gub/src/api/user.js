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