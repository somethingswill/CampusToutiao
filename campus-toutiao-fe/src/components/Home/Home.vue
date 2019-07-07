<template>
    <div>
        <ul class="new-list">
            <li class="new-article" v-for="(news, index) in newsList" @click="viewArticleInfo(news.id)">
                <a href="javascript:void(0)" class="new-picture">
                    <img :src="news.newsImgs" :alt="news.newsTitle" width="100%" height="100%">
                </a>
                <div class="new-title-content">
                    <!--{{news}}-->
                    <h3><a href="javascript:void(0)" style="font-size: 18px">{{ news.newsTitle }}</a></h3>
                    <div class="new-footer">
                        <div class="new-source fl">
                            <!--发布人、发布时间-->
                            <a href="javascript:void(0)" class="fl">{{ news.postNickname }}</a>
                            <span class="report-time fl">{{ news.createDate | dateFormat }}</span>
                        </div>
                        <div class="user-response fr">
                            <!--赞、踩、评论-->
                            <div class="like-thumb fr">
                                <div>
                                    <Icon type="md-text" size="14"/>
                                    <span>{{ news.commentCount }}</span>
                                </div>
                            </div>
                            <div class="like-thumb fr">
                                <div>
                                    <Icon type="md-thumbs-down" size="14"/>
                                    <span>{{ news.dislikeCount }}</span>
                                </div>
                            </div>
                            <div class="like-thumb fr">
                                <div>
                                    <Icon type="md-thumbs-up" size="14"/>
                                    <span>{{ news.likeCount }}</span>
                                    <!--<Icon type="md-thumbs-up" size="18" color="#fa7d3c"/>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>

    export default {
        name: "Home",
        data() {
            return {
                page: 1,  // 页码
                listFlag : true,  // 请求一次后返回非空数据的标志
                currentCategoryId: -1,  // 记录一下当前页面显示的是哪种类型的资讯，默认是全部类型的
                newsList: [],
                // aa: ["1", "1", "1", "1", "1", "1", "1", "1", "1", "1"]
            }
        },
        // computed: {
        //     getNewsList() {
        //         return this.$store.state.allNewsList;
        //         // return this.newsList;
        //     }
        // },
        // 组件复用的时候用只能watch监控路由参数的变化
        watch: {
            '$route' (to, from) {
                // console.log('来:' + from.params.categoryId + '-------------' + '去' + to.params.categoryId);
                // console.log('来:' + from.query.categoryId + '-------------' + '去' + to.query.categoryId);
                // console.log(from);
                // console.log(to);
                if (to.query.categoryId === undefined) {
                    // 查全部资讯
                    this.newsList = this.getNewsListByCategoryId(-1);
                } else {
                    // 查某种分类资讯
                    this.newsList = this.getNewsListByCategoryId(to.query.categoryId);
                }
            }
        },
        created() {
            // window.onscroll = function(){
            //     //变量scrollTop是滚动条滚动时，距离顶部的距离
            //     let scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
            //     //变量windowHeight是可视区的高度
            //     let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
            //     //变量scrollHeight是滚动条的总高度
            //     let scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
            //
            //     // console.log(scrollTop + '+' + windowHeight + '!=' + scrollHeight);
            //     //滚动条到底部（之上）的条件
            //     if(scrollTop + windowHeight >= scrollHeight) {
            //         //写后台加载数据的函数
            //         console.log("距顶部"+scrollTop+"可视区高度"+windowHeight+"滚动条总高度"+scrollHeight);
            //     }
            // }
        },
        mounted() {
            // 这里是组件之间跳转的时候用this.$route.query获取路由的参数
            // console.log('home的路由' + this.$route.query.categoryId);
            // console.log(this.$route.query.categoryId === undefined);
            // console.log('home的路由' + this.$route.params.categoryId);
            if (this.$route.query.categoryId === undefined) {
                // 查全部资讯
                this.getNewsListByCategoryId(-1);
            } else {
                // 查某种分类的资讯
                this.getNewsListByCategoryId(this.$route.query.categoryId);
            }

            // 这里保存一下this也就是vue，到scroll事件里面this的指向就变了，不是vue了
            let _this = this;


            window.onscroll = function(){
                //变量scrollTop是滚动条滚动时，距离顶部的距离
                let scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
                //变量windowHeight是可视区的高度
                let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
                //变量scrollHeight是滚动条的总高度
                let scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;
                console.log(scrollTop + '+' + windowHeight + '!=' + scrollHeight);

                //滚动条到底部的条件
                // if(scrollTop + windowHeight >= scrollHeight - 1) {
                if(Math.ceil(scrollTop) + windowHeight >= scrollHeight) {
                    console.log("距顶部"+scrollTop+"可视区高度"+windowHeight+"滚动条总高度"+scrollHeight);
                    // console.log('++当前categoryId:' +  _this.currentCategoryId);
                    if (_this.listFlag) {
                        // 加载数据时的loading样式
                        const loading = _this.$loading({
                            lock: true,
                            text: '拼命加载中...',
                            spinner: 'el-icon-loading',
                            background: 'rgba(0, 0, 0, 0.7)'
                        });

                        //页码+1
                        _this.page++;
                        // 后台请求数据，追加在原有的资讯list上
                        _this.$http({
                            methods: 'get',
                            url: '/api/getNewsListByCategoryId',
                            params: {
                                categoryId: _this.currentCategoryId,
                                page: _this.page,
                                pageSize: 10
                            }
                        }).then((response) => {
                            if (response.data.toutiaoCode === 0) {
                                loading.close();
                                // 追加在原有的资讯list上
                                // console.log(response.data.toutiaoData.newsList);
                                if (response.data.toutiaoData.newsList.length == 0) {
                                    _this.listFlag = false;
                                    _this.$Message.info({
                                        content: '没有更多资讯了，您再看看别的吧 ^_^',
                                        duration: 3,
                                        closable: true
                                    });
                                } else {
                                    response.data.toutiaoData.newsList.forEach(val => {
                                        _this.newsList.push(val)
                                    })
                                    // _this.newsList.push(response.data.toutiaoData.newsList);
                                }
                            }
                        }).catch((error) => {
                            console.log(error);
                            _this.$Message.error({
                                content: '服务器开小差了，请重试！',
                                duration: 3,
                                closable: true
                            });
                        })
                    }
                }
            }
        },
        methods: {
            getNewsListByCategoryId(categoryId) {
                this.currentCategoryId = categoryId;  // 记录一下当前显示的资讯类型
                this.listFlag = true;  // 初始化一下请求一次后返回非空数据的标志
                this.page = 1;  // 初始化页码为1
                // console.log('记录：' + this.currentCategoryId);

                this.$http({
                    methods: 'get',
                    url: '/api/getNewsListByCategoryId',
                    params: {
                        categoryId: categoryId
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.newsList = response.data.toutiaoData.newsList;
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，请重试！',
                        duration: 3,
                        closable: true
                    });
                });
            },
            // 去某条资讯的详情页
            viewArticleInfo(newsId) {
                // console.log(newsId);
                this.$router.push({
                    path: '/article',
                    query: {
                        newsId: newsId
                    }
                });
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

    .new-list {
        /*background-color: #c2daea;*/
        background-color: #f0f2f5;
    }

    .new-article {
        width: 100%;
        height: 135px;
        padding: 17px 10px 17px 15px;
        margin-top: 10px;
        /*margin: 10px 0;*/
        border-bottom: 1px solid #e6e6e6;
        background-color: #fff;
        cursor: pointer;
    }

    .new-article:hover {
        background-color: #f3f3f3;
    }

    .new-picture {
        display: block;
        /*position: relative;*/
        width: 150px;
        height: 100%;
        float: left;
        margin-right: 17px;
        overflow: hidden;
    }

    .new-title-content h3 {
        height: 80px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .new-title-content h3:hover {
        color: #e10000;
    }

    .new-title-content h3:hover a {
        color: #e10000;
    }

    .new-footer {
        height: 20px;
        padding-right: 10px;
        line-height: 20px;
        color: #666;
        font-size: 12px;
    }

    .new-source a {
        margin-right: 10px;
    }

    .like-thumb {
        cursor: pointer;
    }

    .like-thumb div {
        margin-left: 10px;
        width: 40px;
        font-size: 12px;
        color: #adadad;
    }

    .like-thumb div span {
        color: #adadad;
    }

    /*.like-thumb div:hover span {*/
        /*color: #f66213;*/
        /*!*color: #ed4014;*!*/
    /*}*/

    /*.like-thumb div:hover i {*/
        /*color: #f66213;*/
        /*!*color: #ed4014;*!*/
    /*}*/

    .like-thumb div:hover * {
        color: #f66213;
        /*color: #ed4014;*/
    }

</style>
