//导入vue-router
import { createRouter, createWebHistory } from "vue-router";
//导入组件
import LoginVue from "@/views/Login.vue";
import LayoutVue from "@/views/Layout.vue";
import UserInfo from "@/views/user/UserInfo.vue";
import UserResetPassword from "@/views/user/UserResetPassword.vue";
import UserAvatarVue from "@/views/user/UserAvatar.vue";
import ArticleCategoryVue from "@/views/article/ArticleCategory.vue";
import ArticleManageVue from "@/views/article/ArticleManage.vue";


//定义路由关系
const routes = [
  { path: "/login", component: LoginVue },
  {
    path: "/",
    component: LayoutVue,
    redirect:'/article/category',
    children: [
      { path: "article/category", component: ArticleCategoryVue },
      { path: "article/manage", component: ArticleManageVue },
      { path: "user/avatar", component: UserAvatarVue },
      { path: "user/info", component: UserInfo },
      { path: "user/password", component: UserResetPassword },
    ],
  },
];

//创建路由器
const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

export default router;
