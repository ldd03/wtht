import { createWebHashHistory, createRouter } from 'vue-router'
//配置路由规则
const routes=[
    // 配置 默认访问路径
    {
    path:'/',
    redirect: 'login'
    },
    {
    path:'/register',
    component: ()=>import('@/views/register.vue')
    },
    {
    path:'/login',
    component: ()=>import('@/views/login.vue')
    },
    {
    path:'/menu',
    component: ()=>import('@/views/menu.vue'),
        children:[
            // 如果需要显式访问 /main/index ，可以添加以下配置
            {
                path: 'index',
                name:'loginIndex',
                component: () => import('@/components/loginIndex.vue')
            },
            {
                path: 'noticeManage',
                name:'noticeManage',
                component: () => import('@/components/noticeManage.vue')
            },
            {
                path: 'placeManage',
                name:'placeManage',
                component: () => import('@/components/placeManage.vue')
            },
            {
                path: 'placeSubscribe',
                name:'placeSubscribe',
                component: () => import('@/components/placeSubscribe.vue')
            },
            {
                path: 'subscribeHistory',
                name:'subscribeHistory',
                component: () => import('@/components/subscribeHistory.vue')
            },
            {
                path: 'usersManage',
                name: 'usersManage',
                component: () => import('@/components/usersManage.vue')
            },
            {
                path: 'myInfo',
                name: 'myInfo',
                component: () => import('@/components/myInfo.vue')
            }
        ]
    }
    
]

const router = createRouter({
// 路由的方式： 两种： 基于history
history:createWebHashHistory(),
routes
})

export default router