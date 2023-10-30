<template>
    <div class="home-container">
        <el-menu style="width:200px" default-active="2" @open="handleOpen" @close="handleClose" unique-opened
            :default-openeds="index" router>
            <!-- 一级菜单 -->
            <el-submenu :key="index" :index="'' + index" v-for="(item, index) in menus">
                <template slot="title">

                    <i class="el-icon-location"></i>
                    <span>{{ item.index }}</span>

                </template>
                <!-- 二级菜单 -->
                <!-- 
          bean.have.indexOf(user.status):判断have中是否存在status,
          如果有返回下标，如果没有返回-1
         -->

                <el-menu-item :index="bean.path" v-if="bean.have.indexOf(user.status) != -1"
                    v-for="(bean, i) in item.children" :key="i">
                    {{ bean.menu }}
                </el-menu-item>

            </el-submenu>
        </el-menu>
        <router-view style="height:100vh;overflow:auto;flex:1"></router-view>
        <div class="tools">
            <span class="welcome">欢迎:{{ user.status + user.user }}</span>
            <el-button class="logout" type="danger" @click="logout">退出登录</el-button>

        </div>

    </div>
</template>

<script>
import { menus } from "@/utils/menu.js"
export default {
    components: {},
    data() {
        return {
            menus: menus,
            index: ['0'],
            user: {},//当前登录的用户
        };
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        logout() {
            localStorage.removeItem("user");
            this.user = {};
            this.$router.push("/login")
        }
    },
    created() {
        //将字符串解析为json对象
        this.user = JSON.parse(localStorage.getItem("user"));
    },
    watch: {
        //监听路由变化，路由一变，我就获取登录的用户信息
        $route(to, from) {
            console.log("aaaa");
            console.log(to, from);
        }
    }
};
</script>
<style  scoped>
.home-container {
    display: flex;
    flex-direction: row;
    /* 占据父容器剩下的空间 */
    flex: 1;
}

.tools {
    position: absolute;
    right: 20px;
    top: 10px;
    display: flex;
    justify-content: space-between;
    align-content: center;
}

.welcome {
    display: block;
    margin-right: 30px;
    display: flex;
    align-items: center;
}
</style>