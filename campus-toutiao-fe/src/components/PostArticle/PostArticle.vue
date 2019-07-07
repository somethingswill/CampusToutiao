<template>
    <div class="postArticle">
        <!--{{articleContent}}-->
        <div class="artile-title-contain">
            <el-select v-model="categoryId" clearable placeholder="资讯分类" class="news-category">
                <el-option
                    v-for="item in newsCategoryList"
                    :key="item.id"
                    :label="item.categoryName"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-input class="artile-title" v-model="articleTitle" autocomplete="off" placeholder="请输入资讯标题"></el-input>
            <el-button class="post-news" type="primary" round @click="postNews">发布资讯</el-button>
        </div>
        <!--{{ articleContent }}-->
        <TinymceEditor v-model="articleContent" style="margin-top: 10px"></TinymceEditor>
    </div>
</template>

<script>

    import store from '@/vuex/store.js'
    import TinymceEditor from "@/components/TinymceEditor/TinymceEditor";

    export default {
        name: "PostArticle",
        store,
        components: {TinymceEditor},
        computed: {
            getCurrentUserExist() {
                return this.$store.state.currentUserExist;
            },
            getCurrentUser() {
                return this.$store.state.currentUser;
            }
        },
        data() {
            return {
                newsCategoryList: [],
                categoryId: '',
                articleTitle: '',
                articleContent: ''
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
        methods: {
            postNews() {
                // console.log('资讯分类:' + this.categoryId);
                if (this.categoryId === '' || this.categoryId == null) {
                    this.$Message.error({
                        content: '资讯分类不能为空哦',
                        duration: 3,
                        closable: true
                    });
                    return;
                }
                if (this.articleTitle === '') {
                    this.$Message.error({
                        content: '资讯标题不能为空哦',
                        duration: 3,
                        closable: true
                    });
                    return;
                }
                if (this.articleContent === '') {
                    this.$Message.error({
                        content: '资讯内容不能为空哦',
                        duration: 3,
                        closable: true
                    });
                    return;
                }
                // 调后端接口发布资讯
                this.$http({
                    method: 'post',
                    url: '/api/postNews',
                    data: {
                        categoryId: this.categoryId,
                        newsTitle: this.articleTitle,
                        newsContent: this.articleContent,
                        userId: this.getCurrentUser.id
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.$Message.success({
                            content: '发布资讯成功',
                            duration: 3,
                            closable: true
                        });
                        this.categoryId = '';
                        this.articleTitle = '';
                        this.articleContent = '';
                        // 将分类div显示
                        this.$store.commit('toggleNavHeaderVisable', true);
                        // 发布完资讯后路由到首页
                        this.$router.push('/');
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，发布资讯失败',
                        duration: 3,
                        closable: true
                    });
                })
            }
        }
    }
</script>

<style scoped>

    .artile-title-contain {
        width: 100%;
        margin-top: 10px;
    }

    .news-category {
        width: 105px;
        /*position: relative;*/
    }

    .artile-title {
        /*width: 630px;*/
        width: 525px;
    }

    .post-news {
        float: right;
        margin-right: 5px;
    }

</style>
