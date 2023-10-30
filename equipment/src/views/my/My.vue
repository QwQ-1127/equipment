<template>
    <div class="user">
        <el-descriptions class="margin-top" title="" :column="2" :size="size" border>
            <el-descriptions-item prop="user">
                <template slot="label">
                    <i class="el-icon-user"></i>
                    用户名
                </template>
                {{ this.tableData.user }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-set-up"></i>
                    性别
                </template>
                {{ this.tableData.sex }}
            </el-descriptions-item>

            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-watch"></i>
                    年龄
                </template>
                {{ this.tableData.age }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-mobile-phone"></i>
                    手机号
                </template>
                {{ this.tableData.userphone }}
            </el-descriptions-item>
        </el-descriptions>
        <el-button style="margin-top: 35px;" type="primary" @click="dialogFormVisible = true">修改密码</el-button>

        <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="新密码" :label-width="formLabelWidth">
                    <el-input v-model="form.password" placeholder="请输入新密码"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="change">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { get, post } from "@/utils/http.js";
export default {
    components: {},
    data() {
        return {
            tableData: [], //表格数据
            size: '',
            baseUrl: "http://localhost:9527/equipment/",
            formUser: localStorage.getItem("user"),

            dialogFormVisible: false,
            form: {
                password: '',
                id: JSON.parse(localStorage.getItem("user")).id,
            },
            formLabelWidth: '120px'
        };
    },
    mounted() {
        this.getUser();
    },
    methods: {
        getUser() {
            post("user/show", JSON.parse(this.formUser))
                .then((res) => {
                    console.log(res.data);
                    this.tableData = res.data;
                });
        },
        change() {
            post(
                "http://localhost:9527/equipment/user/change",
                this.form
            ).then((res) => {
                if (res.code == 200) {
                    this.$message("修改成功");
                    setTimeout(() => {
                        this.centerDialogVisible = false;
                    }, 1000);
                    window.parent.location.reload();
                }
            });
        },
    }
}
</script>
<style scoped>
.user {
    margin: 55px;
}
</style>