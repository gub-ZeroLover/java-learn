import request from '@/utils/request'
//携带token
import { useTokenStore } from '@/store/token.js';


//分类管理
export const articleCategoryService = () => {
    const tokenStore = useTokenStore();
    return request.get('/category');
}

export const articleCategoryAddService = (data) => {
    return request.post('/category',data)
}

export const articleCategoryUpdateService = (data) => {
    return request.put('/category',data)
}

export const articleCategoryDeleteService = (id) => {
    return request.delete('/category?id=' + id)
}

//文章管理
export const articleListService = (params) => {
    return request.get('/article', {params})
}