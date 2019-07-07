<template>
    <div class="navHeader">
        <div class="category-content">
            <div class="category-list">
                <ul>
                    <!--<li><a href="#">首页</a></li>-->
                    <li><router-link to="/">首页</router-link></li>
                    <li v-for="(val, index) in newsCategoryList">
                        <a href="javascript:void(0)" @click="toHomeByCategoryId(val.id)">{{ val.categoryName }}</a>
                    </li>
                    <li><a href="javascript:void(0)" @click="popInfo">更多</a></li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>

    import store from '@/vuex/store.js';
    // 引入jquery
    import $ from 'jquery'

    export default {
        name: "NavHeader",
        store,
        data() {
            return {
                newsCategoryList: []
            }
        },
        mounted() {
            let categoryList = this.$store.state.newsCategoryList;
            if (categoryList.length == 0) {
                // vuex中没缓存
                this.$http({
                    method: 'get',
                    url: '/api/getAllNewsCategory'
                }).then((response) => {
                    if (response.data.toutiaoCode == 0) {
                        this.newsCategoryList = response.data.toutiaoData.newsCategoryList;
                        // 放vuex里一份，作为缓存
                        this.$store.commit('injectNewsCategoryList', response.data.toutiaoData.newsCategoryList);
                        // console.log(response.data.toutiaoData.newsCategoryList);
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，获取资讯分类失败',
                        duration: 3,
                        closable: true
                    });
                });
            } else {
                // vuex中有缓存
                this.newsCategoryList = categoryList;
            }
        },
        updated() {
            $('.category-list ul li').click(function () {
                $(this).addClass('category-active').siblings().removeClass('category-active');
            });
        },
        methods: {
            toHomeByCategoryId(categoryId) {
                // console.log(categoryId);
                this.$router.push({
                    path: '/home',
                    query: {
                        categoryId: categoryId
                    }
                });
                // this.$router.push({
                //     name: 'Home',
                //     params: {
                //         categoryId: categoryId
                //     }
                // });
            },
            popInfo() {
                this.$Message.info({
                    content: '该分类正在筹备中，敬请期待',
                    duration: 3,
                    closable: true
                });
            }
        }
    }
</script>

<style scoped>

    h1, h2, h3, h4, h5, h6, span, a {
        color: #000;
    }

    .category-content {
        width: 100%;
        height: 47px;
        /*border-top: 2px solid #f34540;*/
        /*border-bottom: 1px solid #eee;*/
        background-color: #fff;
    }

    .category-content .category-list {
        /*width: 960px;*/
        width: 100%;
        height: 47px;
        overflow: hidden;
        margin: 0 auto;
    }

    .category-list li {
        width: 100px;
        text-align: center;
        /*line-height: 47px;*/
        height: 45px;
        line-height: 45px;
        float: left;
        cursor: pointer;
    }

    .category-list li:hover {
        color: #e10000;
        /*border-bottom: 2px solid #e10000;*/
    }

    .category-list li:hover a {
        font-size: 18px;
        font-weight: bold;
        color: #e10000;
    }

    .category-list li a {
        width: 100%;
        float: left;
        /*display: inline-block;*/
        font-size: 18px;
        /*font-family: 'Microsoft Yahei';*/
        font-weight: bold;
        color: #000;
    }

    .category-active {
        color: #e10000;
        border-bottom: 2px solid #e10000;
    }

</style>
