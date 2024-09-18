import request from '@/utils/request'
//携带token
import { useTokenStore } from '@/store/token.js';

export const articleCategoryService = () => {
    const tokenStore = useTokenStore();
    return request.get('/category', {
        headers:{
            'Authorization': tokenStore.token
        }
    });
}