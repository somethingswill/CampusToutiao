<template>
    <div id="userUI">
        <!--未登录后的用户的头像和头像背景-->
        <div style="position: absolute; width: 230px; height: 183px; overflow: hidden" v-show="!getCurrentUserExist">
            <img class="background-head-pic"
                 src="../../assets/img/no-login.jpg">
        </div>
        <div class="head-pic" v-show="!getCurrentUserExist">
            <a href="javascript:void(0)">
                <img style="width:100%; height:100%;"
                     src="../../assets/img/no-login.jpg">
            </a>
        </div>
        <!--登录后的用户的头像和头像背景-->
        <div style="position: absolute; width: 230px; height: 183px; overflow: hidden" v-show="getCurrentUserExist">
            <img class="background-head-pic" :src="getCurrentUser.headImageUrl">
        </div>
        <div class="head-pic" v-show="getCurrentUserExist">
            <a href="javascript:void(0)">
                <img style="width:100%; height:100%;" :src="getCurrentUser.headImageUrl">
            </a>
        </div>
        <div class="user-info" v-show="true">
            <div class="user-info-wrapper">
                <!--登录后用户的名字-->
                <el-tooltip class="item" effect="dark" :content="getCurrentUser.nickname" v-show="getCurrentUserExist"
                            placement="left" offset="0">
                    <i class="el-icon-female" style="font-size: 18px;color: #e50055;font-weight: bold"
                       v-if="getCurrentUser.gender == 1">
                        <span style="cursor: pointer">{{ getCurrentUser.nickname }}</span>
                    </i>
                    <i class="el-icon-male" style="font-size: 18px;color: #00ade9;font-weight: bold"
                       v-if="getCurrentUser.gender == 0">
                        <span style="cursor: pointer">{{ getCurrentUser.nickname }}</span>
                    </i>
                </el-tooltip>
                <!--未登录的显示-->
                <span v-show="!getCurrentUserExist" style="color: #ababab; font-size: 16px;">您尚未登录</span>
            </div>
        </div>
        <!--登录后用户的自我介绍-->
        <el-card class="box-card" v-show="getCurrentUserExist"
                 style="width: 230px; margin-top: 193px; float: left; font-size: 12px;">
            {{ getCurrentUserProfile }}
        </el-card>
        <!--未登录时，用户自我介绍的占位框，没有其他实际意义-->
        <div v-show="!getCurrentUserExist"
             style="float: left; background-color: #f0f2f5; width: 230px; margin-top: 193px"></div>
        <el-card class="box-card" style="float: left; width: 230px; margin-top: 10px">
            <div slot="header">
                <span style="font-size: 18px; font-weight: bold; font-family: 'Microsoft YaHei'">热门文章</span>
                <!--<el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
            </div>
            <ul class="rank-list">
                <!--<li v-for="(o, index) in 10" :key="o" class="rank-item">-->
                <li class="rank-item" v-for="(hotNews, index) in hotNewsList">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-paiming9" v-if="index === 0"></use>
                        <use xlink:href="#icon-paiming8" v-if="index === 1"></use>
                        <use xlink:href="#icon-paiming7" v-if="index === 2"></use>
                        <use xlink:href="#icon-paiming" v-if="index === 3"></use>
                        <use xlink:href="#icon-paiming1" v-if="index === 4"></use>
                        <use xlink:href="#icon-paiming2" v-if="index === 5"></use>
                        <use xlink:href="#icon-paiming3" v-if="index === 6"></use>
                        <use xlink:href="#icon-paiming4" v-if="index === 7"></use>
                        <use xlink:href="#icon-paiming5" v-if="index === 8"></use>
                        <use xlink:href="#icon-paiming6" v-if="index === 9"></use>
                    </svg>
                    <a href="javascript:void(0)" @click="toHotNewsPage(hotNews.id)">{{ hotNews.newsTitle }}</a>
                </li>
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming9"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">1、资讯一资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming8"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">2、资讯二资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming7"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">3、资讯三资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">4、资讯四资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming1"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">5、资讯五资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming2"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">6、资讯六资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming3"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">7、资讯七资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming4"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">8、资讯八资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming5"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">9、资讯九资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
                <!--<li class="rank-item">-->
                    <!--<svg class="icon" aria-hidden="true">-->
                        <!--<use xlink:href="#icon-paiming6"></use>-->
                    <!--</svg>-->
                    <!--<a href="javascript:void(0)">10、资讯十资讯资讯资讯资讯资讯资讯资讯资讯资讯</a>-->
                <!--</li>-->
            </ul>
        </el-card>
    </div>
</template>

<script>

    import '@/assets/rank_icon_font/iconfont.js'
    import store from '@/vuex/store.js'

    export default {
        name: "UserUI",
        store,
        computed: {
            getCurrentUserExist() {
                return this.$store.state.currentUserExist;
            },
            getCurrentUser() {
                return this.$store.state.currentUser;
            },
            getCurrentUserProfile() {
                if ('' == this.$store.state.currentUser.profile) {
                    return '这个人很懒，暂时没有自我介绍';
                } else {
                    return this.$store.state.currentUser.profile;
                }
            }
        },
        data() {
            return {
                hotNewsList: []  // 热门文章
            }
        },
        mounted() {
            // 加载热门文章列表
            this.$http({
                method: 'get',
                url: '/api/getHotNewsList'
            }).then((response) => {
                if (response.data.toutiaoCode === 0) {
                    this.hotNewsList = response.data.toutiaoData.hotNewsList;
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
        methods: {
            toHotNewsPage(newsId) {
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

    #userUI {
        width: 100%;
        height: 230px;
        position: relative;
    }

    .background-head-pic {
        position: absolute;
        -webkit-filter: blur(3px);
        opacity: 0.5;
        width: 230px;
        /*height: 195px;*/
        height: 230px;
    }

    .head-pic {
        width: 90px;
        height: 90px;
        border-radius: 50%;
        overflow: hidden;
        position: absolute;
        top: 20%;
        left: 50%;
        /*margin-top: 20px;*/
        margin-left: -45px;
        z-index: 100;
    }

    .user-info {
        /*background-color: orangered;*/
        background-color: rgba(255, 255, 255, 0.5);
        width: 100%;
        height: 60px;
        line-height: 60px;
        /*height: 80px;*/
        /*line-height: 80px;*/
        position: absolute;
        overflow: hidden;
        top: 53.5%;
        left: 0;
        /*margin-top: 20px;*/
        z-index: 50;
    }

    .user-info-wrapper {
        position: absolute;
        width: 180px;
        text-align: center;
        margin: 0 25px;
        color: #000;
        font-size: 18px;
        font-weight: bold;
        font-family: 'Microsoft YaHei';
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .icon {
        width: 2em;
        height: 2em;
        vertical-align: -0.5em;
        fill: currentColor;
        overflow: hidden;
    }

    .rank-item {
        margin-bottom: 5px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor: pointer;
    }

    .rank-item:hover {
        color: red;
    }

    .rank-item:hover a {
        color: red;
    }

    .rank-item a {
        color: #000;
        font-weight: bold;
    }

    /*.rank-item a:hover {*/
    /*color: red;*/
    /*}*/

</style>
