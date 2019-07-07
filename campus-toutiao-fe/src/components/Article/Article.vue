<template>
    <div class="article">
        <div class="article-header">
            <h1>{{ articleInfo.newsTitle }}</h1>
        </div>
        <div class="article-body">
            <!--<img src="../../assets/img/background-pic.jpg" alt="填写资讯标题" width="690px" style="margin: 20px 30px">-->
            <div class="article-text" v-html="articleInfo.newsContent"></div>
        </div>
        <div class="article-footer">
            <div class="article-statement">免费声明：本文来自于{{ articleInfo.postNickname }}。</div>
            <div class="user-like">
                <a href="javascript:void(0)">
                    <Icon type="ios-thumbs-up" size="30" :color="colorStyle[likeColorIndex]"
                          @click="likeArticle(articleInfo.id)" />
                </a>
                <a href="javascript:void(0)">
                    <Icon type="ios-thumbs-down" size="30" :color="colorStyle[dislikeColorIndex]"
                          @click="dislikeArticle(articleInfo.id)" />
                </a>
            </div>
            <!--用户写评论的框框-->
            <div class="user-comment-box">
                <div class="article-comment-header">
                    <h3>用户评论</h3><span>文明上网，理性发言</span>
                </div>
                <!--登录-->
                <div class="article-comment-post-box" v-show="getCurrentUserExist">
                    <div class="self-img">
                        <img :src="getCurrentUser.headImageUrl"
                             alt="" width="100%" height="100%">
                    </div>
                    <div class="comment-box">
                        <div class="box-textarea">
                            <el-input type="textarea" maxlength="120" show-word-limit
                                      :autosize="{ minRows: 5, maxRows: 5}"
                                      placeholder="说两句吧。。。"
                                      v-model="commentTextarea">
                            </el-input>
                        </div>
                        <div class="box-footer">
                            <span>{{ getCurrentUser.nickname }}</span>
                            <el-button type="primary" round @click="postComment">发布评论</el-button>
                        </div>
                    </div>
                </div>
                <!--未登录-->
                <div class="no-login-article-comment-post-box" v-show="!getCurrentUserExist">
                    <div class="self-img">
                        <img src="../../assets/img/no-login.jpg"
                             alt="" width="100%" height="100%">
                    </div>
                    <div class="no-login-comment-box">
                        <div class="box-area">请您先登录再进行评论</div>
                        <div class="box-right" @click="loginDialogFormVisible = true">登录</div>
                    </div>
                </div>
            </div>
            <!--评论列表-->
            <div class="comment-list-box">
                <div class="comment-list-header">
                    <h3>全部评论</h3>
                </div>
                <ul class="comment-list" v-if="commentList.length > 0">
                    <li v-for="(comment, index) in commentList">
                        <div class="self-img">
                            <img :src="comment.userHeadImageUrl"
                                alt="用户头像" width="100%" height="100%">
                        </div>
                        <div class="comment-wrapper">
                            <div class="comment-wrapper-header">
                                <span style="font-weight: bold; color: #379be9; cursor: pointer">{{ comment.userNickname }}</span>
                                <span style="color: #999">{{ comment.createDate | dateFormat }}</span>
                            </div>
                            <div class="comment-wrapper-body">
                                <!--<p v-html="comment.commentContent">-->

                                <!--</p>-->
                                <p>{{ comment.commentContent }}</p>
                            </div>
                            <div class="comment-wrapper-footer">
                                <div class="comment-thumb">
                                    <div @click="likeComment(comment.id, index, comment.likeCount)">
                                        <Icon type="md-thumbs-up" size="14" color="#adadad"/>
                                        <span class="comment-thumb-up-span" style="color: #adadad">{{ comment.likeCount }}</span>
                                        <!--<Icon type="md-thumbs-up" size="14" :color="allCommentColorType[index][0]"/>-->
                                        <!--<font class="comment-thumb-up-span" :color="allCommentColorType[index][0]">{{ comment.likeCount }}</font>-->
                                        <!--<Icon type="md-thumbs-up" size="14" :color="commentColorStyle[index][likeCommentColorIndexList[index]]"/>-->
                                        <!--<span class="comment-thumb-up-span">{{ comment.likeCount }}</span>-->
                                    </div>
                                </div>
                                <div class="comment-thumb">
                                    <div @click="dislikeComment(comment.id, index, comment.dislikeCount)">
                                        <Icon type="md-thumbs-down" size="14" color="#adadad"/>
                                        <span class="comment-thumb-down-span" style="color: #adadad">{{ comment.dislikeCount }}</span>
                                        <!--<Icon type="md-thumbs-down" size="14" :color="allCommentColorType[index][1]"/>-->
                                        <!--<font class="comment-thumb-down-span" :color="allCommentColorType[index][1]">{{ comment.dislikeCount }}</font>-->
                                        <!--<Icon type="md-thumbs-down" size="14" :color="commentColorStyle[index][dislikeCommentColorIndexList[index]]"/>-->
                                        <!--<span class="comment-thumb-down-span">{{ comment.dislikeCount }}</span>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <div class="no-comment" v-if="commentList.length === 0">暂无评论，赶紧发个言吧</div>
            </div>
        </div>

        <!--登录的对话框-->
        <!--<el-dialog title="登录" :visible.sync="loginDialogFormVisible" center-->
                   <!--:close-on-click-modal="closeOnClickModal" width="28%" @close="resetForm('loginForm')">-->
            <!--<el-form :rules="loginRules" :model="loginForm" ref="loginForm"-->
                     <!--label-position="left" label-width="80px">-->
                <!--<el-form-item label="用户名" prop="username">-->
                    <!--<el-input v-model="loginForm.username" autocomplete="off" placeholder="请输入用户名"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="密码" prop="passwd">-->
                    <!--<el-input v-model="loginForm.passwd" autocomplete="off" placeholder="请输入密码" show-password></el-input>-->
                <!--</el-form-item>-->
            <!--</el-form>-->
            <!--<div slot="footer" class="dialog-footer">-->
                <!--<el-button @click="resetForm('loginForm');loginDialogFormVisible = false">取 消</el-button>-->
                <!--<el-button type="primary" @click="submitLoginForm()">确 定</el-button>-->
            <!--</div>-->
        <!--</el-dialog>-->

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
    </div>
</template>

<script>

    import store from '@/vuex/store.js'

    export default {
        name: "Article",
        store,
        computed: {
            getCurrentUserExist() {
                return this.$store.state.currentUserExist;
            },
            getCurrentUser() {
                return this.$store.state.currentUser;
            },
            allCommentColorType() {
                let colors = [];
                for (let i=0; i<this.commentList.length; i++) {
                    colors.push(['#adadad', '#adadad']);
                }
                return colors;
            }
        },
        // 解决同路由情况下，跳转同页面不用query时，this.$router.push不跳转，页面只有地址栏变化，页面不变化，但是这种会刷新页面
        // watch: {
        //     '$route' (to, from) {
        //         this.$router.go(0);
        //     }
        // },
        data() {
            return {
                // allCommentColorType: [],
                likeCommentColorIndexList: [],
                dislikeCommentColorIndexList: [],
                commentColorStyle: [],
                likeColorIndex: 1,  // 喜欢资讯
                dislikeColorIndex: 1,  // 不喜欢资讯
                colorStyle: ['#f66213', '#adadad'],  // 点赞的两种颜色，index 0的时候亮，index 1的时候灰
                articleInfo: {},
                commentList: [],  // 资讯的评论列表
                // currentUserExist: false,
                // currentUser: {},  // 用户信息
                loginExceptionMsg: '',  // 登录错误信息
                commentTextarea: '',  // 评论的textarea框
                commentHeadImgUrl: 'https://www.gravatar.com/avatar/14de65c59db1c84d7dc7fa2c34e438a',
                commentHeadImgUrlSuffix: '?s=230&d=identicon',
                closeOnClickModal: false,
                loginDialogFormVisible: false,
                regDialogFormVisible: false,
                loginForm: {
                    username: '',
                    passwd: '',
                    rememberME: false
                },
                /*登录表单校验规则*/
                loginRules: {
                    username: [
                        { required: true, message: '用户名不能为空', trigger: 'blur' },
                        { min: 3, max: 15, message: '用户名长度在 3 到 15 个字符', trigger: 'blur' }
                    ],
                    passwd: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { min: 6, max: 16, message: '密码长度在 6 到 16 个字符', trigger: 'blur' }
                    ]
                }
            }
        },
        mounted() {
            // 去后端请求当前资讯的详细信息
            this.getArticleInfo(this.$route.query.newsId);
            // 判断用户是否登录，然后去返回此用户对该资讯的态度
            if (this.getCurrentUserExist) {
                // 查询一下当前用户对该资讯的态度
                this.$http({
                    method: 'get',
                    url: '/api/getNewsAttitude',
                    params: {
                        userId: this.getCurrentUser.id,
                        newsId: this.$route.query.newsId
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        if (response.data.toutiaoData.attitude === 1) {
                            // 喜欢
                            this.likeColorIndex = 0;
                            this.dislikeColorIndex = 1;
                        } else if (response.data.toutiaoData.attitude === -1) {
                            // 不喜欢
                            this.dislikeColorIndex = 0;
                            this.likeColorIndex = 1;
                        } else {
                            // 中性态度
                            this.likeColorIndex = 1;
                            this.dislikeColorIndex = 1;
                        }
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，登录失败',
                        duration: 3,
                        closable: true
                    });
                })
            }

            // 去后端请求当前资讯的所有评论信息
            this.getCommentList(this.$route.query.newsId);
        },
        methods: {
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
                                // 放vuex里
                                this.$store.commit('toggleCurrentUserExist', true);
                                this.$store.commit('injectUserInfo', response.data.toutiaoData.user);
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
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            getArticleInfo(newsId) {
                this.$http({
                    methods: 'get',
                    url: '/api/getNewsByNewsId',
                    params: {
                        newsId: newsId
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.articleInfo = response.data.toutiaoData.newsInfo;
                    }
                }).catch((error)=> {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，请重试',
                        duration: 3,
                        closable: true
                    });
                });
            },
            getCommentList(newsId) {
                this.$http({
                    method: 'get',
                    url: '/api/getCommentListByNewsId',
                    params: {
                        newsId: newsId
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.commentList = response.data.toutiaoData.commentInfoList;
                        // let list = response.data.toutiaoData.commentInfoList;
                        for (let i = 0; i < this.commentList.length; i++) {
                            this.likeCommentColorIndexList[i] = 1;
                            this.dislikeCommentColorIndexList[i] = 1;
                            this.commentColorStyle.push(this.colorStyle);
                        }
                        // if (this.getCurrentUserExist) {
                        //     // 查询一下当前用户对该些评论的态度
                        //     for (let i = 0; i < this.commentList.length; i++) {
                        //         let commId = this.commentList[i].id;
                        //         this.$http({
                        //             method: 'get',
                        //             url: '/api/getCommentAttitude',
                        //             params: {
                        //                 userId: this.getCurrentUser.id,
                        //                 commentId: commId
                        //             }
                        //         }).then((response) => {
                        //             if (response.data.toutiaoCode === 0) {
                        //                 if (response.data.toutiaoData.attitude === 1) {
                        //                     // 喜欢
                        //                     this.likeCommentColorIndexList[i] = 0;
                        //                     this.dislikeCommentColorIndexList[i] = 1;
                        //                     this.allCommentColorType[i][0] = '#f66213';
                        //                     this.allCommentColorType[i][1] = '#adadad';
                        //                 } else if (response.data.toutiaoData.attitude === -1) {
                        //                     // 不喜欢
                        //                     this.dislikeCommentColorIndexList[i] = 0;
                        //                     this.likeCommentColorIndexList[i] = 1;
                        //                     this.allCommentColorType[i][1] = '#f66213';
                        //                     this.allCommentColorType[i][0] = '#adadad';
                        //                 } else {
                        //                     // 中性态度
                        //                     this.likeCommentColorIndexList[i] = 1;
                        //                     this.dislikeCommentColorIndexList[i] = 1;
                        //                     this.allCommentColorType[i][1] = '#adadad';
                        //                     this.allCommentColorType[i][0] = '#adadad';
                        //                 }
                        //                 // console.log(this.commentColorStyle);
                        //                 // console.log(this.likeCommentColorIndexList);
                        //                 // console.log(this.dislikeCommentColorIndexList);
                        //                 // for (let i=0; i<this.likeCommentColorIndexList.length; i++) {
                        //                 //     //console.log(this.commentColorStyle[i][this.likeCommentColorIndexList[i]] + ' ' + this.commentColorStyle[i][this.dislikeCommentColorIndexList[i]]);
                        //                 //     console.log(this.likeCommentColorIndexList[i] + ' ' + this.dislikeCommentColorIndexList[i]);
                        //                 // }
                        //             }
                        //         }).catch((error) => {
                        //             console.log(error);
                        //             this.$Message.error({
                        //                 content: '服务器开小差了，请重试',
                        //                 duration: 3,
                        //                 closable: true
                        //             });
                        //         });
                        //     }
                        // }
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，请重试',
                        duration: 3,
                        closable: true
                    });
                })
            },
            // 发布评论
            postComment() {
                if (this.commentTextarea === '') {
                    this.$Message.error({
                        content: '评论内容不能为空哦',
                        duration: 3,
                        closable: true
                    });
                    return;
                }
                this.$http({
                    method: 'post',
                    url: '/api/postComment',
                    data: {
                        commentContent: this.commentTextarea,
                        newsId: this.articleInfo.id,
                        userId: this.getCurrentUser.id
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.$Message.success({
                            content: '发表评论成功',
                            duration: 3,
                            closable: true
                        });
                        // 将发表成功的评论塞在资讯评论列表的最前面
                        // this.commentList.unshift(response.data.toutiaoData.commentInfo);
                        this.getCommentList(this.articleInfo.id);
                        // 评论textarea框置空
                        this.commentTextarea = '';
                    } else {
                        this.$Message.error({
                            content:  '发表评论失败，' + response.data.toutiaoMsg,
                            duration: 3,
                            closable: true
                        });
                        // 评论textarea框置空
                        this.commentTextarea = '';
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，请重试',
                        duration: 3,
                        closable: true
                    });
                });
            },
            likeArticle(newsId) {
                // 用户已登录
                if (this.getCurrentUserExist) {
                    if (this.likeColorIndex === 0) {
                        // 已经点过赞了
                        this.$Message.info({
                            content: '您已经点过赞了，无需重复操作^_^',
                            duration: 3,
                            closable: true
                        });
                    } else {
                        this.likeColorIndex = 0;
                        this.dislikeColorIndex = 1;

                        this.$http({
                            method: 'get',
                            url: '/api/likeNews',
                            params: {
                                newsId: newsId,
                                userId: this.getCurrentUser.id
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                this.$Message.success({
                                    content: '您已经成功赞了这条资讯😊',
                                    duration: 3,
                                    closable: true
                                });
                            }
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，请重试',
                                duration: 3,
                                closable: true
                            });
                        })
                    }
                } else {
                    // 用户未登录状态，只改变页面效果，但是不发送后台请求，刷新页面后回到原来状态，其实还是没点赞
                    this.likeColorIndex = 0;
                    this.dislikeColorIndex = 1;
                }
            },
            dislikeArticle(newsId) {
                // 用户已登录
                if (this.getCurrentUserExist) {
                    if (this.dislikeColorIndex === 0) {
                        // 已经踩过了
                        this.$Message.info({
                            content: '您已经踩过了，无需重复操作^_^',
                            duration: 3,
                            closable: true
                        });
                    } else {
                        this.dislikeColorIndex = 0;
                        this.likeColorIndex = 1;

                        this.$http({
                            method: 'get',
                            url: '/api/dislikeNews',
                            params: {
                                newsId: newsId,
                                userId: this.getCurrentUser.id
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                this.$Message.success({
                                    content: '您已经成功踩了这条资讯😭',
                                    duration: 3,
                                    closable: true
                                });
                            }
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，请重试',
                                duration: 3,
                                closable: true
                            });
                        })
                    }
                } else {
                    // 用户未登录状态，只改变页面效果，但是不发送后台请求，刷新页面后回到原来状态，其实还是没点踩
                    this.dislikeColorIndex = 0;
                    this.likeColorIndex = 1;
                }
            },
            likeComment(commentId, index, currentLikeCount) {
                //alert(index);
                // 用户已登录
                if (this.getCurrentUserExist) {
                    if (this.likeCommentColorIndexList[index] === 0) {
                        // 已经点过赞了
                        this.$Message.info({
                            content: '您已经点过赞了，无需重复操作^_^',
                            duration: 3,
                            closable: true
                        });
                    } else {
                        this.$http({
                            method: 'get',
                            url: '/api/likeComment',
                            params: {
                                commentId: commentId,
                                userId: this.getCurrentUser.id
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {

                                this.likeCommentColorIndexList[index] = 0;
                                this.dislikeCommentColorIndexList[index] = 1;

                                this.allCommentColorType[index][0] = '#f66213';
                                this.allCommentColorType[index][1] = '#adadad';

                                document.getElementsByClassName("comment-thumb-up-span")[index].innerHTML = response.data.toutiaoData.comment.likeCount;
                                document.getElementsByClassName("comment-thumb-down-span")[index].innerHTML = response.data.toutiaoData.comment.dislikeCount;
                                //this.likeCountList[index] = response.data.toutiaoData.comment.likeCount;
                                this.$Message.success({
                                    content: '您已经成功赞了这条资讯😊',
                                    duration: 3,
                                    closable: true
                                });
                            }
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，请重试',
                                duration: 3,
                                closable: true
                            });
                        })
                    }
                } else {
                    // 用户未登录状态，只改变页面效果，但是不发送后台请求，刷新页面后回到原来状态，其实还是没点赞
                    this.likeCommentColorIndexList[index] = 0;
                    this.dislikeCommentColorIndexList[index] = 1;

                    document.getElementsByClassName("comment-thumb-up-span")[index].innerHTML = currentLikeCount + 1;
                    // document.getElementsByClassName("comment-thumb-down-span")[index].innerHTML = ;

                    this.allCommentColorType[index][0] = '#f66213';
                    this.allCommentColorType[index][1] = '#adadad';
                }
            },
            dislikeComment(commentId, index, currentDislikeCount) {
                //alert(index);
                // 用户已登录
                if (this.getCurrentUserExist) {
                    if (this.dislikeCommentColorIndexList[index] === 0) {
                        // 已经点过赞了
                        this.$Message.info({
                            content: '您已经踩过了，无需重复操作^_^',
                            duration: 3,
                            closable: true
                        });
                    } else {
                        this.$http({
                            method: 'get',
                            url: '/api/dislikeComment',
                            params: {
                                commentId: commentId,
                                userId: this.getCurrentUser.id
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                this.dislikeCommentColorIndexList[index] = 0;
                                this.likeCommentColorIndexList[index] = 1;

                                this.allCommentColorType[index][1] = '#f66213';
                                this.allCommentColorType[index][0] = '#adadad';

                                document.getElementsByClassName("comment-thumb-down-span")[index].innerHTML = response.data.toutiaoData.comment.dislikeCount;
                                document.getElementsByClassName("comment-thumb-up-span")[index].innerHTML = response.data.toutiaoData.comment.likeCount;
                                this.$Message.success({
                                    content: '您已经成功踩了这条资讯😭',
                                    duration: 3,
                                    closable: true
                                });
                            }
                        }).catch((error) => {
                            console.log(error);
                            this.$Message.error({
                                content: '服务器开小差了，请重试',
                                duration: 3,
                                closable: true
                            });
                        })
                    }
                } else {
                    // 用户未登录状态，只改变页面效果，但是不发送后台请求，刷新页面后回到原来状态，其实还是没点踩
                    this.dislikeCommentColorIndexList[index] = 0;
                    this.likeCommentColorIndexList[index] = 1;

                    document.getElementsByClassName("comment-thumb-down-span")[index].innerHTML = currentDislikeCount + 1;

                    this.allCommentColorType[index][1] = '#f66213';
                    this.allCommentColorType[index][0] = '#adadad';
                }
            }
        }
    }
</script>

<style scoped>

    h1, h2, h3, h4, h5, h6, p, span, a {
        color: #000;
    }

    .article {
        margin-top: 10px;
        background-color: #fff;
        overflow: hidden;
    }

    .article-header h1 {
        padding: 15px;
        font-size: 28px;
        font-family: 'Microsoft YaHei';
        /*border-bottom: 1px solid #bfcce5;*/
        border-bottom: 1px solid #f0f0f0;
    }

    .article-text {
        padding: 10px 15px 15px;
        font-size: 16px;
        font-family: "Microsoft YaHei";
    }

    .article-text p {
        margin-top: 10px;
    }

    .article-text p img {
        margin: 0 auto 0;
    }

    .article-statement {
        margin: 10px 0;
        height: 30px;
        line-height: 30px;
        font-size: 12px;
        text-align: center;
        /*padding: 23px 0 6px;*/
    }

    .user-like {
        height: 80px;
        /*background-color: red;*/
    }

    .user-like a {
        float: left;
        width: 50%;
        height: 80px;
        line-height: 80px;
        text-align: center;
    }

    .user-like a i {
        color: #adadad;
        /*color: #5c6b77;*/
    }

    .user-like a i:hover {
        color: #f66213;
        /*color: #ed4014;*/
    }

    .thump-active {
        color: #f66213;
    }

    .user-comment-box {
        border-top: 1px solid #f0f0f0;
        /*border-top: 1px solid #e6e6e6;*/
        /*height: 80px;*/
    }

    .article-comment-header {
        height: 60px;
        line-height: 60px;
    }

    .article-comment-header h3 {
        /*width: 720px;*/
        float: left;
        margin-left: 15px;
        color: #000;
        font-size: 20px;
        font-family: "Microsoft YaHei";
        /*background-color: orangered;*/
    }

    .article-comment-header span {
        float: left;
        color: #ccc;
        height: 50px;
        line-height: 50px;
        margin-top: 8px;
        margin-left: 10px;
        font-size: 14px;
    }

    .article-comment-post-box {
        margin-top: 10px;
        width: 100%;
        height: 186px;
        /*height: 300px;*/
        background-color: #fff;
    }

    .no-login-article-comment-post-box {
        margin-top: 10px;
        width: 100%;
        height: 100px;
        /*height: 200px;*/
        background-color: #fff;
    }

    .self-img {
        float: left;
        margin-left: 15px;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        overflow: hidden;
    }

    .comment-box {
        float: right;
        width: 650px;
        height: 180px;
        border: 3px solid #f0f0f0;
        margin-right: 15px;
    }

    .no-login-comment-box {
        float: right;
        width: 650px;
        height: 100px;
        margin-right: 15px;
    }

    .box-area {
        float: left;
        width: 547px;
        height: 100px;
        line-height: 100px;
        text-align: center;
        color: #c2c7ce;
        font-size: 16px;
        border-top: 3px solid #f0f0f0;
        border-left: 3px solid #f0f0f0;
        border-bottom: 3px solid #f0f0f0;
        cursor: not-allowed;
    }

    .box-right {
        float: left;
        width: 100px;
        height: 100px;
        line-height: 100px;
        background-color: #379be9;
        text-align: center;
        color: #fff;
        font-size: 18px;
        cursor: pointer;
    }

    .box-right:hover {
        background-color: #328bd1;
    }

    .box-footer span {
        float: left;
    }

    .box-footer {
        width: 100%;
        height: 57px;
        line-height: 57px;
    }

    .box-footer span {
        float: left;
        width: 20%;
        margin-left: 16px;
        color: #3d3d3d;
        font-size: 16px;
        font-weight: bold;
        font-family: "Microsoft YaHei";
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .box-footer button {
        float: right;
        margin-right: 16px;
        margin-top: 9px;
    }

    .comment-list-header {
        margin-top: 10px;
        height: 60px;
        line-height: 60px;
    }

    .comment-list-header h3 {
        float: left;
        margin-left: 15px;
        color: #000;
        font-size: 20px;
        font-family: "Microsoft YaHei";
    }

    .comment-list li {
        height: 190px;
        padding-top: 20px;
        padding-bottom: 20px;
        border-top: 1px solid #f0f0f0;
        /*border-top: 1px solid #e6e6e6;*/
        /*background-color: orangered;*/
    }

    .no-comment {
        height: 150px;
        /*padding-top: 20px;*/
        /*padding-bottom: 20px;*/
        color: #ababab;
        text-align: center;
        font-size: 24px;
        font-weight: bold;
        line-height: 150px;
    }

    .comment-list li:hover {
        background-color: #f3f3f3;
    }

    .comment-wrapper {
        float: right;
        width: 650px;
        margin-right: 15px;
    }

    .comment-wrapper-header {
        height: 32px;
        line-height: 32px;
    }

    .comment-wrapper-header span {
        font-size: 14px;
        margin-right: 15px;
    }

    .comment-wrapper-body p {
        display: inline-block;
        width: 650px;
        height: 85px;
        font-size: 14px;
        color: #4b4b4b;
        word-break: break-all;
        /*word-break: break-word;*/
    }

    .comment-wrapper-footer {
        margin-top: 7px;
    }

    .comment-thumb {
        float: left;
        cursor: pointer;
        /*color: #adadad;*/
    }

    .comment-thumb div {
        float: left;
        width: 40px;
        font-size: 14px;
        margin-top: 8px;
        margin-right: 50px;
        color: #adadad;
    }

    .comment-thumb div span {
        color: #adadad;
    }

    /*.comment-thumb div:hover span {*/
        /*color: #f66213;*/
        /*!*color: #ed4014;*!*/
    /*}*/

    /*.comment-thumb div:hover i {*/
        /*color: #f66213;*/
        /*!*color: #ed4014;*!*/
    /*}*/

    /*.comment-thumb div:hover * {*/
        /*color: #f66213;*/
        /*!*color: #ed4014;*!*/
    /*}*/

</style>
