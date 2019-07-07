<template>
    <div class="adminLogin">
        <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
                 class="demo-ruleForm login-container">
            <h3 class="title">系统登录</h3>
            <el-form-item prop="account">
                <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="管理员账号"></el-input>
            </el-form-item>
            <el-form-item prop="checkPass">
                <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="管理员密码"></el-input>
            </el-form-item>
            <!--<el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>-->
            <span style="float: left; font-size: 12px; color: #f56c6c; margin-left: 40%;">{{ loginError }}</span>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">
                    登录
                </el-button>
                <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "AdminLogin",
        data() {
            return {
                loginError: '',
                logining: false,
                ruleForm2: {
                    account: '',
                    checkPass: ''
                },
                rules2: {
                    account: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        //{ validator: validaePass }
                    ],
                    checkPass: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        //{ validator: validaePass2 }
                    ]
                },
                checked: true
            };
        },
        methods: {
            handleReset2() {
                this.$refs.ruleForm2.resetFields();
            },
            handleSubmit2(ev) {
                this.$refs.ruleForm2.validate((valid) => {
                    if (valid) {
                        this.$http({
                            method: 'post',
                            url: '/api/admin/login',
                            data: {
                                adminUsername: this.ruleForm2.account,
                                adminPasswd: this.ruleForm2.checkPass
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                // 放vuex里一份
                                this.$store.commit("toggleAdminUserExist", true);
                                this.$store.commit("injectAdminUser", response.data.toutiaoData.adminUser);
                                this.ruleForm2.account = '';
                                this.ruleForm2.checkPass = '';
                                this.loginError = '';
                                this.$router.push('/admin');
                            } else {
                                this.loginError = response.data.toutiaoMsg;
                                this.$store.commit("toggleAdminUserExist", false);
                                this.$store.commit("injectAdminUser", {});
                            }
                        }).catch((error) => {
                            this.$store.commit("toggleAdminUserExist", false);
                            this.$store.commit("injectAdminUser", {});
                            console.log(error);
                            this.$message({
                                type: 'error',
                                message: '服务器开小差了，登录失败!',
                                showClose: true
                            });
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

    .adminLogin {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .remember {
        margin: 0px 0px 35px 0px;
    }

    .title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }

</style>
