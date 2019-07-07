import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/Home/Home";
import Article from "@/components/Article/Article";
import HelloWorld from "@/components/HelloWorld";
import PostArticle from "@/components/PostArticle/PostArticle";
import AdminPage from "@/components/Admin/AdminPage/AdminPage";
import UserManage from "@/components/Admin/UserManage/UserManage";
import CommentManage from "@/components/Admin/CommentManage/CommentManage";
import CategoryManage from "@/components/Admin/CategoryManage/CategoryManage";
import ArticleManage from "@/components/Admin/ArticleManage/ArticleManage";
import Charts from "@/components/Admin/Charts/Charts";
import AdminLogin from "@/components/Admin/AdminLogin/AdminLogin";

Vue.use(Router)

export default new Router({
    mode: 'history',
    scrollBehavior: () => ({ y : 0}),
    routes: [
        {
            path: '/admin',
            component: AdminPage,
            name: 'AdminPage',
            children: [
                { path: '/admin', component: ArticleManage, name: '管理首页', meta: {requireAuth: true} },
                { path: '/admin/article', component: ArticleManage, name: '资讯管理', meta: {requireAuth: true} },
                { path: '/admin/category', component: CategoryManage, name: '分类管理', meta: {requireAuth: true} },
                { path: '/admin/comment', component: CommentManage, name: '评论管理', meta: {requireAuth: true} },
                { path: '/admin/user', component: UserManage, name: '用户管理', meta: {requireAuth: true} },
                { path: '/admin/charts', component: Charts, name: '数据分析', meta: {requireAuth: true} },
                { path: '/admin/login', component: AdminLogin, name: 'AdminLogin' },
            ]
        },
        {
            path: '/home',
            name: 'Home',
            component: Home
        },
        {
            path: '/article',
            name: 'Article',
            component: Article
        },
        {
            path: '/post',
            name: 'PostArticle',
            component: PostArticle
        },
        {
            path: '/hello',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/',
            // name: 'Home',
            component: Home
        }
    ]
})
