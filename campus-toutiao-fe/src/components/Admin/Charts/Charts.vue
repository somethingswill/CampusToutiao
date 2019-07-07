<template>
    <div class="charts">
        <el-col :span="12">
            <div id="categoryData" style="width:100%; height:320px; margin-top: 10px; margin-left: 10px"></div>
        </el-col>
        <el-col :span="12">
            <div id="activeUserData" style="width:100%; height:320px; margin-top: 10px"></div>
        </el-col>
        <el-col :span="12">
            <div id="commentData" style="width:100%; height:330px; margin-left: 10px"></div>
        </el-col>
        <el-col :span="12">
            <div id="userGenderData" style="width:100%; height:330px;"></div>
        </el-col>
    </div>
</template>

<script>

    import echarts from 'echarts'

    export default {
        name: "Charts",
        data() {
            return {
                categoryData: null,
                activeUserData: null,
                newsData: null,
                userGenderData: null
            }
        },
        mounted() {
            this.drawCategoryDataChart();
            this.drawActiveUserDataChart();
            this.drawCommentDataChart();
            this.drawUserGenderDataChart();
        },
        methods: {
            drawCategoryDataChart() {
                this.$http({
                    method: 'get',
                   url: '/api/admin/drawCategoryDataChart'
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        let option = response.data.toutiaoData.echartsData;
                        this.categoryData = echarts.init(document.getElementById('categoryData'));
                        this.categoryData.setOption(option);
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，加载失败',
                        duration: 3,
                        closable: true
                    });
                });
            },
            drawActiveUserDataChart() {
                this.$http({
                    method: 'get',
                    url: '/api/admin/drawActiveUserDataChart'
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        let option = response.data.toutiaoData.echartsData;
                        this.activeUserData = echarts.init(document.getElementById('activeUserData'));
                        this.activeUserData.setOption(option);
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，加载失败',
                        duration: 3,
                        closable: true
                    });
                });
            },
            drawCommentDataChart() {
                this.$http({
                    method: 'get',
                    url: '/api/admin/drawCommentDataChart'
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        let option = response.data.toutiaoData.echartsData;
                        this.commentData = echarts.init(document.getElementById('commentData'));
                        this.commentData.setOption(option);
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，加载失败',
                        duration: 3,
                        closable: true
                    });
                });
            },
            drawUserGenderDataChart() {
                this.$http({
                    method: 'get',
                    url: '/api/admin/drawUserGenderDataChart'
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        let option = response.data.toutiaoData.echartsData;
                        this.userGenderData = echarts.init(document.getElementById('userGenderData'))
                        this.userGenderData.setOption(option);
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，加载失败',
                        duration: 3,
                        closable: true
                    });
                });
            }
        }
    }
</script>

<style scoped>

</style>
