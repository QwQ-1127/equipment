<template>
    <div class="">
        <div style="padding: 5px">
            <el-button type="warning">报修</el-button>
        </div>
        <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" height="400"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection"> </el-table-column>
            <!-- prop:必须跟后端返回数据的属性一模一样 -->
            <el-table-column label="设备" prop="equipment"> </el-table-column>
            <el-table-column prop="company" label="公司" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="website" label="设备官网" show-overflow-tooltip>
                <template slot-scope="scope">
                    <a :href="scope.row.website">
                        <el-button size="small" type="primary">官网</el-button>
                    </a>
                </template>
            </el-table-column>
            <el-table-column prop="logo" label="设备图片" show-overflow-tooltip>
                <template slot-scope="scope">
                    <!-- 可以弹框放大的图片标签 -->
                    <el-popover placement="top-start" trigger="click">
                        <a :href="baseUrl + scope.row.logo" target="_blank" title="查看放大图片">
                            <img :src="baseUrl + scope.row.logo" alt="" />
                        </a>
                        <img slot="reference" :src="baseUrl + scope.row.logo"
                            style="width: 120px; height: 60px; cursor: pointer" alt="" />
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column prop="number" label="设备数" show-overflow-tooltip>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页
        current-change:当前页改变时触发的事件
       -->
        <el-pagination layout="prev, pager, next" @current-change="getCurrentPage" :current-page="currentPage"
            :page-size="pageSize" :total="total"> </el-pagination>
        <!-- 弹出的添加医院对话框
        :close-on-click-modal="false" 禁止点击Modal关闭弹框
       -->
        <el-dialog title="添加设备" :visible.sync="centerDialogVisible" width="30%" :close-on-click-modal="false" center>
            <el-form ref="hospitalForm" :model="formHospital" label-width="80px">
                <!-- prop:要跟input的对象属性名一致 -->
                <el-form-item label="设备名称">
                    <el-input v-model="formHospital.equipment" placeholder="请输入设备名称"></el-input>
                </el-form-item>
                <el-form-item label="公司">
                    <el-input v-model="formHospital.company" placeholder="请输入公司名称"></el-input>
                </el-form-item>
                <el-form-item label="官网">
                    <el-input v-model="formHospital.website" placeholder="请输入官网地址"></el-input>
                </el-form-item>
                <el-form-item label="数量">
                    <el-input v-model="formHospital.number" placeholder="请输入数量"></el-input>
                </el-form-item>
                <el-form-item label="设备logo">
                    <el-upload class="avatar-uploader" action="http://localhost:9527/equipment/upload" name="upload"
                        :show-file-list="false" :on-success="handleAvatarSuccess">
                        <img v-if="formHospital.logo" :src="baseUrl + formHospital.logo" class="avatar" />
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary">重置</el-button>
                    <el-button type="success" @click="add">提交</el-button>
                </el-form-item>
            </el-form>
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
            multipleSelection: [], //多选按钮数据收集
            centerDialogVisible: false, //控制对话框显示与隐藏
            baseUrl: "http://localhost:9527/equipment/", //服务端的根路径
            formHospital: {
                equipment: "",
                company: "",
                website: "",
                number: "",
                logo: "",
            },
            //分页相关
            total: 7,//总个数
            pageSize: 3,//每页显示的总个数
            currentPage: 1,//当前页数
        };
    },
    mounted() {
        this.getHospitalByPage();
        this.getTotal();
    },
    methods: {
        getHospitalByPage() {
            //向后端发请求，分页查询医院列表
            //get请求传多个参数
            // 发起一个post请求
            get(`equipment/listAllByPage/${this.pageSize}/${this.currentPage}`)
                .then((res) => {
                    console.log(res.data);
                    this.tableData = res.data;
                });
        },
        //获取总数量
        getTotal() {
            //查询总个数
            get("equipment/listAll").then((res) => {
                console.log(res.data);
                this.total = res.data.length;
            });
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach((row) => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleAvatarSuccess(res, file) {
            this.formHospital.logo = res.data;
            console.log("图片存储的路径", res.data);
        },
        add() {
            post(
                "http://localhost:9527/equipment/equipment/add",
                this.formHospital
            ).then((res) => {
                if (res.code == 200) {
                    this.$message("添加成功");
                    setTimeout(() => {
                        this.centerDialogVisible = false;
                    }, 1000);
                    window.parent.location.reload();
                }
            });
        },
        //点击查看触发的函数
        handleClick(row) { },
        //批量删除
        deleteBatch() {
            if (this.multipleSelection.length == 0) {
                this.$message.error("请至少选择一行");
            } else {
                //传递id数组
                var idList = [];
                this.multipleSelection.forEach((item) => {
                    idList.push(item.id);
                });
                post("equipment/deleteBatch", {
                    ids: idList,
                }).then((res) => {
                    if (res.code == 200) {
                        this.$message("删除成功");
                        window.location.reload();
                    }
                });
            }
        },
        //获取当前页码
        getCurrentPage(value) {
            console.log("当前页>>>", value);
            this.currentPage = value;
            this.getHospitalByPage();
            this.getTotal();
        }
    },
};
</script>
<style  scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409eff;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>