//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'
import router from '@/router'
import { useTokenStore } from '@/store/token';

//定义一个变量,记录公共的前缀  ,  baseURL
// const baseURL = 'http://localhost:8080';
const baseURL = '/api'// 会自动拼接上面的前缀地址
const instance = axios.create({baseURL})

//添加请求拦截器
instance.interceptors.request.use(
    config=>{
        //获取token
        const tokenStore = useTokenStore();
        if(tokenStore.token){
            config.headers['Authorization'] = tokenStore.token;
        }
        return config;
    },
    (err) => {
        return Promise.reject(err);
    }
)

//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        if (result.data.code == 0){
            return result.data;
        }
        // alert(result.data.massage ? result.data.massage : '服务异常');
        ElMessage.error(result.data.massage ? result.data.massage : '服务异常')
        return Promise.reject(result.data);
    },
    err=>{
        // alert('服务异常');
        if(err.response.status == 401){
            ElMessage.error('请先登录')
            router.push('/login');
        }else{
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;