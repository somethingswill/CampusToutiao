<template>
    <div id="app" :key="appKey">
        <NavLogo v-if="!getAdminStatusFlag"></NavLogo>
        <div class="main-content" v-if="!getAdminStatusFlag">
            <div class="news-content">
                <NavHeader v-show="getNavHeaderVisable"></NavHeader>
                <router-view/>
            </div>
            <div style="width: 230px;height: 850px; float: right">
                <div class="user-content">
                    <UserUI></UserUI>
                </div>
            </div>
        </div>
        <router-view v-if="getAdminStatusFlag" />
    </div>
</template>

<script>

    import '@/assets/css/reset.css';

    import NavLogo from "@/components/NavLogo/NavLogo";
    import NavHeader from "@/components/NavHeader/NavHeader";
    import UserUI from "@/components/UserUI/UserUI";

    import store from '@/vuex/store.js'

    export default {
        name: 'App',
        store,
        components: {
            NavLogo,
            UserUI,
            NavHeader
        },
        computed: {
            getNavHeaderVisable() {
                return this.$store.state.navHeaderVisable;
            },
            getAdminStatusFlag() {
                return this.$store.state.adminStatus;
            }
        },
        data() {
            return {
                isRouterAlive: true,
                appKey: 1,
                bgImgHeight: '',
                // currentUser: {}
            }
        },
        // 解决同路由情况下，跳转同页面不用query时，this.$router.push不跳转，页面只有地址栏变化，页面不变化
        watch:{
            '$route' (to, from){
                this.appKey = new Date().getTime();
            }
        },
        methods: {
            /*设置背景图片高度*/
            setBgImgHeight() {
                this.bgImgHeight = document.body.scrollHeight + 'px';
            },
        }
    }
</script>


<style scoped>

    #app {
        /*margin-top: -15px;*/
        /*background-color: #c2daea;*/
        background-color: #f0f2f5;
        /*background: rgba(240, 240, 240, 0.5);*/
        /*background: rgba(7, 17, 27, 0.5);*/
        /*background: rgba(106, 175, 216, 0.5);*/
        overflow: hidden;
    }

    .main-content {
        width: 1000px;
        margin: 60px auto 0;
    }

    .news-content {
        width: 750px;
        /*height: 400px;*/
        margin-top: 10px;
        float: left;
    }

    .user-content {
        width: 230px;
        height: 183px;
        /*height: 230px;*/
        margin: 10px 10px 0 10px;
        /*border-top: 2px solid #f34540;*/
        background-color: #fff;
        float: right;
    }
</style>
