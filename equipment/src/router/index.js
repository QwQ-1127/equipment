import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        component: () => import("@/views/Login.vue")
    },
    {
        path: '/',
        component: () => import("@/views/Home.vue"),
        children: [
            {
                path: "/equipment/list",
                component: () => import("@/views/equipment/List.vue")
            },
            {
                path: "/equipment/baoxiu",
                component: () => import("@/views/equipment/Baoxiu.vue")
            },
            {
                path: '/my/my',
                component: () => import("@/views/my/My.vue")
            },
        ]
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//配置路由拦截器
router.beforeEach((to, from, next) => {
    //to:你要跳转的路径
    //from:当前路由
    //next():执行下一步操作，放行拦截
    if (to.path == "/login") {
        return next();
    }
    if (!localStorage.getItem("user")) {
        //没有登录还访问登录页面,打回到登录页面
        return next("/login");
    }
    //登录了且访问的是非登录页面，那么正常执行
    next();
})

export default router
