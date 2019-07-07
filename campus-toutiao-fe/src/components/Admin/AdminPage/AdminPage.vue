<template>
    <div class="adminPage">
        <el-row class="container" v-if="$store.state.adminUserExist">
            <el-col :span="24" class="header">
                <el-col :span="3" class="logo">
                    校园头条
                </el-col>
                <el-col :span="17">
                    <!--<div class="tools" @click.prevent="collapse">
                        <i class="fa fa-align-justify"></i>
                    </div>-->
                </el-col>
                <el-col :span="4" class="userinfo">
                    <el-dropdown trigger="hover">
                        <span class="el-dropdown-link userinfo-inner"><img
                            src="../../../assets/img/no-login.jpg"/>{{ $store.state.adminUser.username }}</span>
                        <el-dropdown-menu slot="dropdown">
                            <!--<el-dropdown-item>我的消息</el-dropdown-item>
                            <el-dropdown-item>设置</el-dropdown-item>-->
                            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-col>
            </el-col>
            <el-col :span="24" class="main">
                <el-col :span="3">
                    <el-menu :router="true" :default-active="$route.path"
                        style="text-align: center;"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose">
                        <el-menu-item index="/admin/article">
                            <i class="el-icon-tickets"></i>
                            <span slot="title">资讯管理</span>
                        </el-menu-item>
                        <el-menu-item index="/admin/comment">
                            <i class="el-icon-s-comment"></i>
                            <span slot="title">评论管理</span>
                        </el-menu-item>
                        <el-menu-item index="/admin/user">
                            <i class="el-icon-s-custom"></i>
                            <span slot="title">用户管理</span>
                        </el-menu-item>
                        <el-menu-item index="/admin/category">
                            <i class="el-icon-menu"></i>
                            <span slot="title">分类管理</span>
                        </el-menu-item>
                        <el-menu-item index="/admin/charts">
                            <i class="el-icon-s-data"></i>
                            <span slot="title">数据分析</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
                <el-col :span="21">
                    <router-view v-if="isRouterAlive" />
                </el-col>
            </el-col>
        </el-row>
        <router-view v-if="!$store.state.adminUserExist" />
    </div>
</template>

<script>

    import store from '@/vuex/store.js'

    export default {
        name: "AdminPage",
        store,
        provide (){
            return {
                reload: this.reload
            }
        },
        data() {
            return {
                isRouterAlive: true,
                sysUserName: '',
                sysUserAvatar: ''
            }
        },
        methods: {
            toArticleManage() {
                this.$router.push("/admin/article");
            },
            toCategoryManage() {
                this.$router.push("/admin/category");
            },
            toCommentManage() {
                this.$router.push("/admin/comment");
            },
            toUserManage() {
                this.$router.push("/admin/user");
            },
            toCharts() {
                this.$router.push("/admin/charts");
            },
            reload() {
                this.isRouterAlive = false
                this.$nextTick(function(){
                    this.isRouterAlive = true
                })
            },
            handleOpen() {
                //console.log('handleOpen');
            },
            handleClose() {
                //console.log('handleClose');
            },
            //退出登录
            logout() {
                this.$confirm('确认退出登录吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    roundButton: true
                }).then(() => {
                    this.$store.commit('toggleAdminUserExist', false);
                    this.$store.commit('injectAdminUser', {});
                    this.$router.push('/admin/login');
                }).catch((error) => {
                    console.log(error);
                    this.$message({
                        type: 'error',
                        message: '服务器开小差了，操作失败!',
                        showClose: true
                    });
                });

            }
        },
        mounted() {

            // 管理员状态
            this.$store.commit('toggleAdminstatus', true);

            let user = sessionStorage.getItem('user');
            if (user) {
                user = JSON.parse(user);
                this.sysUserName = user.name || '';
                this.sysUserAvatar = user.avatar || '';
            }

        }
    }

</script>

<style scoped>

    .adminPage {
        position: absolute;
        /*position: relative;*/
        top: 0px;
        bottom: 0px;
        width: 100%;
    }

    .header {
        height: 60px;
        line-height: 60px;
        background: #20a0ff;
        /*background: #18c79c;*/
        color: #fff;
    }

    .userinfo {
        text-align: right;
        padding-right: 35px;
        float: right;
    }

    .userinfo-inner {
        cursor: pointer;
        color: #fff;
    }

    .userinfo-inner img {
        width: 40px;
        height: 40px;
        border-radius: 20px;
        margin: 10px 0px 10px 10px;
        float: right;
    }

    .logo {
        /*width: 230px;*/
        height: 60px;
        font-size: 26px;
        font-weight: bold;
        border-color: rgba(238, 241, 146, 0.3);
        border-right-width: 1px;
        border-right-style: solid;
        text-align: center;
    }
    .logo img {
        width: 40px;
        float: left;
        margin: 10px 10px 10px 18px;
    }

</style>
