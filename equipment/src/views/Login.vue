<template>
    <div class="login-container">
        <el-card class="login-card">
            <h2 class="title">设备管理系统</h2>
            <h3 class="login-title">用户登录/注册</h3>
            <!-- model:表单数据对象 -->
            <el-form ref="userForm" :rules="rules" :model="formUser" label-width="80px">
                <!-- prop:要跟input的对象属性名一致 -->
                <el-form-item label="账号" prop="userphone">
                    <el-input v-model="formUser.userphone" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="formUser.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="性别" v-if="isShowRegist" prop="sex">
                    <el-select v-model="formUser.sex" placeholder="请选择性别">
                        <el-option :key="index" v-for="(item, index) in [
                            { label: '男', value: '男' },
                            { label: '女', value: '女' },
                        ]" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年龄" v-if="isShowRegist" prop="age">
                    <el-input-number v-model="formUser.age"></el-input-number>
                </el-form-item>
                <el-form-item label="姓名" v-if="isShowRegist" prop="user">
                    <el-input v-model="formUser.user" placeholder="请输入真实姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <!-- userForm:是当前form表单的ref名 -->
                    <el-button type="primary" @click="regist('userForm')">注册</el-button>
                    <el-button type="success" @click="login('userForm')">登录</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
//导入get和post两个请求
import { get, post } from "@/utils/http.js";
export default {
    components: {},
    data() {
        //自定义的手机号校验规则
        var validatePhone = (rule, value, callback) => {
            //手机号正则
            var reg = /^1[3-9]\d{9}$/;
            //reg.test(value):校验值是否满足正则
            if (!reg.test(value)) {
                callback(new Error("手机号格式不正确"));
            } else {
                callback();
            }
        };
        return {
            formUser: {
                userphone: "",
                password: "",
                sex: "",
                age: 18,
                user: "", //真实姓名
            },
            rules: {
                userphone: [
                    //validator:自定义的手机号校验器
                    { required: true, trigger: "blur", validator: validatePhone },
                ],
                password: [{ required: true, message: "请输入密码", trigger: "blur" }],
                sex: [{ required: true, message: "请选择性别", trigger: "change" }],
                user: [{ required: true, message: "请选择姓名", trigger: "blur" }],
                age: [{ required: true, type: "number", message: "年龄必须为数字值" }],
            },
            isShowRegist: false, //控制性别、年龄、真实姓名的是否显示，如果显示就是注册，否则就是登录
        };
    },
    methods: {
        regist(formName) {
            if (!this.isShowRegist) {
                //对regist取反，显示注册相关的信息
                this.isShowRegist = !this.isShowRegist;
            } else {
                //校验是否满足表单格式
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //使用axios发请求，封装版的ajax
                        post("user/regist", this.formUser)
                            //then:接口调用成功会执行的回调函数
                            //res:接口返回的结果
                            .then((res) => {
                                if (res.code == 200) {
                                    this.$message({
                                        message: "注册成功",
                                        type: "success",
                                    });
                                } else {
                                    this.$message.error(res.msg);
                                }
                            });
                    } else {
                        console.log("error submit!!");
                        return false;
                    }
                });
            }
        },
        login(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    post("user/login", this.formUser).then(
                        (res) => {
                            if (res.code == 200) {
                                this.$message({
                                    message: "登录成功",
                                    type: "success",
                                });
                                //将用户信息存到localstorage中
                                //存json对象一定先转换为字符串
                                localStorage.setItem("user", JSON.stringify(res.data));
                                //路由跳转到首页
                                //$router:整个vue实例所有的路由的对象【所有路由】
                                //$route:当前页面对应的路由【当前路由】
                                this.$router.push("/")
                            } else {
                                this.$message.error(res.msg);
                            }
                        }
                    );
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        },
    },
};
</script>
<style  scoped>
.login-container {
    background-image: url("../../public/bg.png");
    background-size: cover;
    /* vw(view width):视图宽度
       vh(view height):视图高度
       100vw = 当前屏幕的宽   100vh=当前屏幕的高
       可以自适应屏幕大小取值，也适配移动端
    */
    height: 100vh;
    background-position: center;
    /* 弹性盒布局 */
    display: flex;
    /* 水平对齐方式：靠右对齐 */
    justify-content: space-around;
}

.login-card {
    margin-top: 50px;
    width: 400px;
    height: 600px;
    background-color: rgba(255, 253, 253, 0.525);
    font-weight: bolder;
}

.title {
    font-size: 28px;
    text-align: center;
    margin-top: 10px;
}

.login-title {
    font-size: 20px;
    text-align: center;
    margin-top: 50px;
}

.el-form {
    margin-top: 40px;
}
</style>