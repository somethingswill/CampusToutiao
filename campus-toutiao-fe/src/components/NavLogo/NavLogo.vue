<template>
    <div class="navLogo">
        <div class="logo-content">
            <div class="web-logo">
                <a href="javascript:void(0)" class="logo-font fl" @click="toHome">校园<strong>头条</strong></a>
                <!--<router-link to="/" class="logo-font fl">校园<strong>头条</strong></router-link>-->
                <!--未登录时显示-->
                <div class="reg-login fr" v-show="!getCurrentUserExist">
                    <a href="javascript:void(0)" class="fr" @click="loginDialogFormVisible = true">登录</a>
                    <span class="fr">|</span>
                    <a href="javascript:void(0)" class="fr" @click="regDialogFormVisible = true">注册</a>
                </div>

                <!--登录时的显示-->
                <div class="top-right fr" v-show="getCurrentUserExist">
                    <el-dropdown class="fl" @command="handleCommand">
                        <div class="username-wrapper fl">
                            <i class="el-icon-user-solid"></i>
                            <!--<el-tooltip class="item" effect="dark" content="麻瓜弟麻瓜弟麻瓜弟麻瓜弟麻瓜弟"-->
                            <!--placement="left-end">-->
                            <span>{{ getCurrentUser.nickname }} </span>
                            <!--</el-tooltip>-->
                        </div>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="editPersonInfo">更改信息</el-dropdown-item>
                            <el-dropdown-item command="changeHeadPic">换个头像</el-dropdown-item>
                            <el-dropdown-item divided command="logout">注销账户</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    <div class="write-btn fl" style="margin-right: 30px">
                            <el-button type="primary" icon="el-icon-s-promotion" @click="postArticle"
                                       circle size="mini" style="font-size: 16px"></el-button>
                        <!--<el-button type="primary" circle size="mini">-->
                        <!--<i class="el-icon-s-promotion" style="font-size: 16px"></i>-->
                        <!--</el-button>-->
                    </div>
                </div>
            </div>
        </div>

        <!--登录的对话框-->
        <el-dialog title="登录" :visible.sync="loginDialogFormVisible" center
                   :close-on-click-modal="closeOnClickModal" width="25%"
                   @close="resetForm('loginForm');loginExceptionMsg = '';">
            <el-form :rules="loginRules" :model="loginForm" ref="loginForm"
                     label-position="left" label-width="80px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="loginForm.username" autocomplete="off" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="passwd">
                    <el-input v-model="loginForm.passwd" autocomplete="off" placeholder="请输入密码"
                              show-password></el-input>
                </el-form-item>
                <!--<el-form-item label="记住我" prop="rememberME" label-width="60px"  style="margin-left: 40%;">-->
                <!--<el-switch v-model="loginForm.rememberME"></el-switch>-->
                <!--</el-form-item>-->
                <el-form-item label="记住我" prop="rememberME" style="margin-left: 8px;">
                    <el-switch v-model="loginForm.rememberME"></el-switch>
                    <span style="float: right; font-size: 12px; color: #f56c6c">{{loginExceptionMsg}}</span>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('loginForm');loginDialogFormVisible = false; loginExceptionMsg = ''">取 消</el-button>
                <el-button type="primary" @click="submitLoginForm">登 录</el-button>
            </div>
        </el-dialog>

        <!--注册的对话框-->
        <el-dialog title="注册" :visible.sync="regDialogFormVisible" center top="8vh"
                   :close-on-click-modal="closeOnClickModal" width="30%" @close="resetForm('regForm')">
            <el-form :rules="regRules" :model="regForm" ref="regForm"
                     label-position="left" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="regForm.username" autocomplete="off" placeholder="请输入要注册的用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="passwd">
                    <el-input v-model="regForm.passwd" autocomplete="off" show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPasswd">
                    <el-input v-model="regForm.checkPasswd" autocomplete="off" show-password
                              placeholder="请再次输入密码"></el-input>
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="regForm.nickname" autocomplete="off" placeholder="请输入您的昵称"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="regForm.gender">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="联系电话" prop="tel">
                    <el-input v-model="regForm.tel" autocomplete="off" placeholder="请输入您的新手机号"></el-input>
                </el-form-item>
                <el-form-item label="自我介绍" prop="profile">
                    <el-input type="textarea" v-model="regForm.profile" placeholder="说点什么吧。。"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('regForm');regDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitRegForm">注 册</el-button>
            </div>
        </el-dialog>

        <!--编辑个人信息的对话框-->
        <el-dialog title="更改个人信息" :visible.sync="editPersonInfoDialogFormVisibale" center top="8vh"
                   :close-on-click-modal="closeOnClickModal" width="40%" @close="resetForm('editPersonInfoForm')">
            <el-form :rules="editPersonInfoRules" :model="editPersonInfoForm" ref="editPersonInfoForm"
                     label-position="left" label-width="100px">
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="editPersonInfoForm.nickname" autocomplete="off"
                              placeholder="请输入您的新昵称"></el-input>
                </el-form-item>
                <el-form-item label="旧密码" prop="oldPasswd">
                    <el-input v-model="editPersonInfoForm.oldPasswd" autocomplete="off" show-password
                              placeholder="请输入旧密码"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="passwd">
                    <el-input v-model="editPersonInfoForm.passwd" autocomplete="off" show-password
                              placeholder="请输入新密码"></el-input>
                </el-form-item>
                <el-form-item label="确认新密码" prop="checkPasswd">
                    <el-input v-model="editPersonInfoForm.checkPasswd" autocomplete="off" show-password
                              placeholder="请再次输入新密码"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="editPersonInfoForm.gender">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="联系电话" prop="tel">
                    <el-input v-model="editPersonInfoForm.tel" autocomplete="off" placeholder="请输入您的手机号"></el-input>
                </el-form-item>
                <el-form-item label="自我介绍" prop="profile">
                    <el-input type="textarea" v-model="editPersonInfoForm.profile" placeholder="说点什么吧。。"></el-input>
                </el-form-item>
                <span style="position: relative; width: 132px; height: 18px; left: 50%; margin-left: -76px; font-size: 12px; color: #f56c6c">
                    {{ editUserInfoExceptionMsg }}
                </span>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="resetForm('editPersonInfoForm');editPersonInfoDialogFormVisibale = false">取 消
                </el-button>
                <el-button type="primary" @click="submitEditPersonInfoForm">确 定</el-button>
            </div>
        </el-dialog>

        <!--更改用户头像弹框-->
        <el-dialog title="更改用户头像" :visible.sync="changeHeadPicDialogFormVisible" center
                   :close-on-click-modal="closeOnClickModal" width="300px" @close="cancelChangeHeadPic">

            <el-upload
                class="avatar-uploader"
                action="/api/uploadHeaderPic"
                name="headImage"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :on-error="handleAvatarError"
                :before-upload="beforeAvatarUpload" style="text-align: center">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

            <div slot="footer" class="dialog-footer">
                <el-button @click="cancelChangeHeadPic();changeHeadPicDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="uploadHeadPic">确 认</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>

    // import TinymceEditor from "@/components/TinymceEditor/TinymceEditor";
    import store from '@/vuex/store.js'

    export default {
        name: "NavLogo",
        store,
        // components: {TinymceEditor},
        computed: {
            getCurrentUserExist() {
                return this.$store.state.currentUserExist;
            },
            getCurrentUser() {
                return this.$store.state.currentUser;
            }
        },
        data() {
            /**************************以下是注册表单的校验注册**************************/
            /*自定义密码校验*/
            let validatePasswd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('密码不能为空'));
                } else {
                    if (this.regForm.checkPasswd !== '') {
                        this.$refs.regForm.validateField('checkPasswd');
                    }
                    callback();
                }
            };
            /*自定义确认密码校验*/
            let validateCheckPasswd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.regForm.passwd) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            /*自定义手机号校验*/
            let validateTel = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('手机号不能为空'));
                } else {
                    /*手机号校验正则表达式*/
                    let telReg = /^[1][3,4,5,7,8][0-9]{9}$/;
                    if (!telReg.test(value)) {
                        callback(new Error('您输入的手机号格式不合法'));
                    } else {
                        callback();
                    }
                }
            };
            /*自定义用户名是否被注册校验*/
            let validateCheckUsername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('用户名不能为空'));
                } else {
                    let flag = false;
                    /*查询是否已存在用户名*/
                    this.$http({
                        method: 'get',
                        url: '/api/checkUsername',
                        params: {
                            username: value
                        }
                    }).then((response) => {
                        if (response.data.toutiaoCode !== 0) {
                            flag = true;
                        }
                        if (flag) {
                            callback(new Error('此用户名已经被注册'));
                        } else {
                            callback();
                        }
                    }).catch((error) => {
                        console.log(error);
                        this.$Message.error({
                            content: '服务器开小差了，请重试！',
                            duration: 3,
                            closable: true
                        });
                    })

                }
            };
            /**************************以上是注册表单的校验注册**************************/

            /**************************以下是编辑个人信息表单的校验**************************/
            /*自定义密码校验*/
            let validatePasswdEdit = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('新密码不能为空'));
                } else {
                    if (this.editPersonInfoForm.checkPasswd !== '') {
                        this.$refs.editPersonInfoForm.validateField('checkPasswd');
                    }
                    callback();
                }
            };
            /*自定义确认密码校验*/
            let validateCheckPasswdEdit = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入新密码'));
                } else if (value !== this.editPersonInfoForm.passwd) {
                    callback(new Error('两次输入的新密码不一致!'));
                } else {
                    callback();
                }
            };
            /**************************以上是编辑个人信息表单的校验**************************/

            return {
                // getCurrentUserExist: false,
                // getCurrentUser: {},
                editUserInfoExceptionMsg: '',  // 修改用户信息时的错误信息
                loginExceptionMsg: '',  // 登录错误信息
                imageUrl: '',  // 上传头像后返回的用户头像url，用于在上传头像的弹框中显示的图片
                closeOnClickModal: false,
                loginDialogFormVisible: false,
                regDialogFormVisible: false,
                editPersonInfoDialogFormVisibale: false,
                changeHeadPicDialogFormVisible: false,
                loginForm: {
                    username: '',
                    passwd: '',
                    rememberME: false
                },
                /*登录表单校验规则*/
                loginRules: {
                    username: [
                        {required: true, message: '用户名不能为空', trigger: 'blur'},
                        {min: 3, max: 15, message: '用户名长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    passwd: [
                        {required: true, message: '密码不能为空', trigger: 'blur'},
                        {min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur'}
                    ]
                },
                regForm: {
                    username: '',
                    passwd: '',
                    checkPasswd: '',
                    nickname: '',
                    gender: '男',
                    profile: '',
                    tel: ''
                },
                /*注册表单校验规则*/
                regRules: {
                    username: [
                        {required: true, validator: validateCheckUsername, trigger: 'blur'},
                        {min: 3, max: 15, message: '用户名长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    passwd: [
                        {required: true, validator: validatePasswd, trigger: 'blur'},
                        {min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur'}
                    ],
                    checkPasswd: [
                        {required: true, validator: validateCheckPasswd, trigger: 'blur'},
                        {min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur'}
                    ],
                    nickname: [
                        {required: true, message: '请输入您的昵称', trigger: 'blur'},
                        {min: 3, max: 15, message: '昵称长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    gender: [
                        {required: true, message: '必须选择一个性别', trigger: 'blur'}
                    ],
                    tel: [
                        {required: true, validator: validateTel, trigger: 'blur'},
                    ]
                },
                editPersonInfoForm: {
                    nickname: '',
                    oldPasswd: '',
                    passwd: '',
                    checkPasswd: '',
                    gender: '男',
                    profile: '',
                    tel: ''
                },
                /*发布资讯表单校验规则*/
                editPersonInfoRules: {
                    nickname: [
                        {required: true, message: '请输入您的昵称', trigger: 'blur'},
                        {min: 3, max: 15, message: '昵称长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    oldPasswd: [
                        {required: true, message: '请输入你的旧密码', trigger: 'blur'},
                        {min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur'}
                    ],
                    passwd: [
                        {required: true, validator: validatePasswdEdit, trigger: 'blur'},
                        {min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur'}
                    ],
                    checkPasswd: [
                        {required: true, validator: validateCheckPasswdEdit, trigger: 'blur'},
                        {min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur'}
                    ],
                    gender: [
                        {required: true, message: '必须选择一个性别', trigger: 'blur'}
                    ],
                    tel: [
                        {required: true, validator: validateTel, trigger: 'blur'},
                    ]
                }
            };
        },

        mounted() {
            // 去拿ticket
            let loginTicket = this.getCookie('ticket');
            if (loginTicket !== '') {
                // console.log('ticket,,,' + loginTicket);
                // 携带ticket，发送自动登录请求
                this.$http({
                    method: 'get',
                    url: '/api/autoLogin'
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        // this.getCurrentUser = response.data.toutiaoData.user;
                        // this.getCurrentUserExist = true;
                        // 放vuex里一份
                        this.$store.commit('toggleCurrentUserExist', true);
                        this.$store.commit('injectUserInfo', response.data.toutiaoData.user);
                        // console.log('navlog中' + this.$store.state.currentUser);
                        // console.log('navlog中' + this.$store.state.currentUserExist);
                        // console.log(this.currentUser);
                        // sessionStorage.setItem("currentUser", JSON.stringify(response.data.toutiaoData.user));
                        // console.log(this.user);
                    }
                })
            }
        },

        methods: {
            getCookie(cname) {
                let name = cname + "=";
                let ca = document.cookie.split(';');
                for (let i = 0; i < ca.length; i++) {
                    let c = ca[i].trim();
                    if (c.indexOf(name) == 0) {
                        return c.substring(name.length, c.length);
                    }
                }
                return "";
            },
            submitLoginForm() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        // 表单校验通过
                        this.$http({
                            method: 'post',
                            url: '/api/login',
                            data: {
                                username: this.loginForm.username,
                                passwd: this.loginForm.passwd,
                                rememberME: this.loginForm.rememberME
                            }
                        }).then((response) => {
                            // 登录成功
                            if (response.data.toutiaoCode === 0) {
                                // this.currentUser = response.data.toutiaoData.user;
                                // this.currentUserExist = true;
                                // 放vuex里
                                this.$store.commit('toggleCurrentUserExist', true);
                                this.$store.commit('injectUserInfo', response.data.toutiaoData.user);
                                // console.log('navlog中' + this.$store.state.currentUser);
                                // console.log('navlog中' + this.$store.state.currentUserExist);
                                // console.log(this.currentUser.username);
                                // 放sessionStorage一份
                                // sessionStorage.setItem("currentUser", JSON.stringify(this.currentUser));
                                // 重置表单
                                this.resetForm('loginForm');
                                this.loginExceptionMsg = '';
                                this.loginForm.rememberME = false;
                                this.loginDialogFormVisible = false;
                            } else {
                                this.loginExceptionMsg = response.data.toutiaoMsg;
                            }
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，登录失败',
                                duration: 3,
                                closable: true
                            });
                            // 重置表单
                            this.resetForm('loginForm');
                            this.loginExceptionMsg = '';
                            this.loginForm.rememberME = false;
                            this.loginDialogFormVisible = false;
                        })
                    } else {
                        // 表单校验不通过
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            submitRegForm() {
                this.$refs.regForm.validate((valid) => {
                    if (valid) {
                        // 表单校验通过
                        let genderAlias = 0;
                        if (this.regForm.gender === '女') {
                            genderAlias = 1;
                        }
                        this.$http({
                            method: 'post',
                            url: '/api/reg',
                            data: {
                                username: this.regForm.username,
                                passwd: this.regForm.passwd,
                                nickname: this.regForm.nickname,
                                gender: genderAlias,
                                tel: this.regForm.tel,
                                profile: this.regForm.profile
                            }
                        }).then((response) => {
                            this.$Message.success({
                                content: response.data.toutiaoMsg + '，请登录',
                                duration: 3,
                                closable: true
                            });
                            this.resetForm('regForm');
                            this.regForm.gender = '男';
                            this.regDialogFormVisible = false;
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，注册失败',
                                duration: 3,
                                closable: true
                            });
                            this.resetForm('regForm');
                            this.regForm.gender = '男';
                            this.regDialogFormVisible = false;
                        })
                    } else {
                        // 表单校验不通过
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            submitEditPersonInfoForm() {
                this.$refs.editPersonInfoForm.validate((valid) => {
                    if (valid) {
                        // 表单校验通过
                        let genderAlias = 0;
                        if (this.editPersonInfoForm.gender === '女') {
                            genderAlias = 1;
                        }
                        // 去服务端修改用户信息
                        this.$http({
                            method: 'post',
                            url: '/api/editUserInfo',
                            data: {
                                userId: this.getCurrentUser.id,
                                oldPasswd: this.editPersonInfoForm.oldPasswd,
                                nickname: this.editPersonInfoForm.nickname,
                                gender: genderAlias,
                                newPasswd: this.editPersonInfoForm.passwd,
                                profile: this.editPersonInfoForm.profile,
                                tel: this.editPersonInfoForm.tel
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                // 更新vuex里边的信息
                                this.$store.commit('injectUserInfo', response.data.toutiaoData.user);
                                this.$Message.success({
                                    content: '修改用户信息成功',
                                    duration: 3,
                                    closable: true
                                });
                                this.editUserInfoExceptionMsg = '';
                                this.resetForm('editPersonInfoForm');
                                this.editPersonInfoDialogFormVisibale = false;
                            } else {
                                this.editUserInfoExceptionMsg = response.data.toutiaoMsg;
                            }
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，修改用户信息失败',
                                duration: 3,
                                closable: true
                            });
                            this.editUserInfoExceptionMsg = '';
                            this.resetForm('editPersonInfoForm');
                            this.editPersonInfoDialogFormVisibale = false;
                        })
                    } else {
                        // 表单校验不通过
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            handleCommand(command) {
                // this.$message('click on item ' + command);
                if (command === 'editPersonInfo') {
                    this.editPersonInfoDialogFormVisibale = true;
                    // 回显用户信息
                    this.editPersonInfoForm.nickname = this.getCurrentUser.nickname;
                    this.editPersonInfoForm.gender = this.getCurrentUser.gender == 0 ? '男' : '女';
                    this.editPersonInfoForm.tel = this.getCurrentUser.tel;
                    this.editPersonInfoForm.profile = this.getCurrentUser.profile;
                }
                if (command === 'changeHeadPic') {
                    this.changeHeadPicDialogFormVisible = true;
                }
                if (command === 'logout') {
                    this.$confirm('此操作将注销当前用户, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                        roundButton: true
                    }).then(() => {
                        this.$http({
                            method: 'get',
                            url: '/api/logout'
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                // 删除vuex里面的用户数据
                                this.$store.commit('toggleCurrentUserExist', false);
                                this.$store.commit('injectUserInfo', {});
                                this.$message({
                                    type: 'success',
                                    message: '注销成功!',
                                    showClose: true
                                });
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: '服务器开小差了，注销失败!',
                                    showClose: true
                                });
                            }
                        }).catch((error) => {
                            this.$message({
                                type: 'error',
                                message: '服务器开小差了，注销失败!',
                                showClose: true
                            });
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消',
                            showClose: true
                        });
                        // this.$Message.info({
                        //     content: '已取消',
                        //     duration: 3,
                        //     closable: true
                        // });
                    });
                }
            },
            // 文件上传成功时的钩子(回调)函数
            handleAvatarSuccess(res, file) {
                // this.imageUrl = URL.createObjectURL(file.raw);
                // console.log(file);
                // console.log(res);
                // console.log(URL.createObjectURL(file.raw));
                if (res.toutiaoCode === 0) {
                    this.imageUrl = res.toutiaoData.headImageUrl;
                }
                // console.log(res);
            },
            // 文件上传失败时的钩子(回调)函数
            handleAvatarError(res, file) {
                this.$Message.error({
                    content: '服务器开小差了，更换头像失败',
                    duration: 3,
                    closable: true
                });
                // 重置一下数据
                this.imageUrl = '';
            },
            beforeAvatarUpload(file) {
                // console.log(file);
                const isJPG = file.type === 'image/jpeg';
                const isPNG = file.type === 'image/png';
                const isGIF = file.type === 'image/gif';
                const isLt100M = file.size / 1024 / 1024 <= 100;

                if (!isLt100M) {
                    this.$Message.error({
                        content: '上传头像图片大小不能超过 100MB!',
                        duration: 3,
                        closable: true
                    });
                    return false;
                }

                if (!(isJPG || isPNG || isGIF)) {
                    this.$Message.error({
                        content: '上传头像图片只能是 JPG、PNG、GIF 格式!',
                        duration: 3,
                        closable: true
                    });
                    return false;
                } else {
                    true;
                }

            },
            // 将图片地址写入数据库
            uploadHeadPic() {
                // this.$message('上传头像');
                this.$http({
                    method: 'post',
                    url: '/api/updateUserHeadImage',
                    data: {
                        userId: this.getCurrentUser.id,
                        imgUrl: this.imageUrl
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        // 将头像地址更新至数据库，更新vuex中的数据
                        this.$store.commit('injectUserInfo', response.data.toutiaoData.user);
                        this.$Message.success({
                            content: '更换头像成功',
                            duration: 3,
                            closable: true
                        });
                        this.imageUrl = '';
                        this.changeHeadPicDialogFormVisible = false;
                    } else {
                        this.$Message.success({
                            content: '服务器开小差了，更换头像失败',
                            duration: 3,
                            closable: true
                        });
                        this.imageUrl = '';
                        this.changeHeadPicDialogFormVisible = false;
                    }
                }).catch((error) => {
                    this.$Message.success({
                        content: '服务器开小差了，更换头像失败',
                        duration: 3,
                        closable: true
                    });
                    this.imageUrl = '';
                    this.changeHeadPicDialogFormVisible = false;
                });
            },
            cancelChangeHeadPic() {
                this.imageUrl = '';
            },
            toHome() {
                // 将分类div重新展示
                this.$store.commit('toggleNavHeaderVisable', true);
                // 跳路由
                this.$router.push("/");
            },
            postArticle() {
                // 将分类div隐藏掉
                this.$store.commit('toggleNavHeaderVisable', false);
                // 跳路由
                this.$router.push("/post");
            }
        }
    }
</script>

<style scoped>

    h1, h2, h3, h4, h5, h6, span, a {
        color: #000;
    }

    .fl {
        float: left;
    }

    .fr {
        float: right;
    }

    .logo-content {
        /*width: 960px;*/
        width: 100%;
        height: 60px;
        line-height: 60px;
        background-color: #fff;
        position: fixed;
        top: 0;
        z-index: 1000;
        /*border-bottom: 1px solid #e6e6e6;*/
        border-bottom: 1px solid #bfcce5;
        /*border-top: 2px solid #e10000;*/
    }

    .web-logo {
        width: 1000px;
        margin: 0 auto;
        /*padding-left: 15px;*/

    }

    .logo-font {
        color: #e10000;
        letter-spacing: 3px;
        font-size: 30px;
        font-weight: bold;
        font-family: "Microsoft YaHei";
    }

    .logo-font strong {
        background-color: #e10000;
        color: #fff;
        border-radius: 5px;
        padding-left: 8px;
        padding-right: 5px;
    }

    .reg-login {
        width: 120px;
        text-align: center;
        margin-right: 30px;
    }

    .reg-login a {
        margin-right: 10px;
        color: #555;
        font-size: 16px;
        font-weight: bold;
    }

    .reg-login a:hover {
        /*color: #379be9;*/
        color: #f66213;
    }

    .reg-login span {
        margin-right: 10px;
        color: #d9d9d9;
        font-size: 16px;
    }

    .username-wrapper {
        width: 100px;
        text-align: center;
        /*margin-right: 10px;*/
        /*background-color: orangered;*/
        cursor: pointer;
        font-size: 16px;
        font-weight: bold;
        white-space: nowrap;
        overflow: hidden;
        color: #555;
        text-overflow: ellipsis;
    }

    .username-wrapper * {
        color: #555;
    }

    .username-wrapper:hover {
        /*color: #379be9;*/
        color: #f66213;
    }

    .username-wrapper:hover * {
        /*color: #379be9;*/
        color: #f66213;
    }

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    /*.upload-contain {*/
    /*width: 178px;*/
    /*height: 178px;*/
    /*margin: 0 auto 0;*/
    /*}*/
    .avatar {
        width: 178px;
        height: 178px;
        display: inline-block;
        /*margin-left: 37px;*/
    }

</style>
