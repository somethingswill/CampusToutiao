<template>
    <div class="articleManage">
        <el-table align="center" header-align="center" ref="multipleTable"
            :data="tableData" @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="资讯 ID">
                            <span>{{ props.row.id }}</span>
                        </el-form-item>
                        <el-form-item label="资讯标题">
                            <span>{{ props.row.newsTitle }}</span>
                        </el-form-item>
                        <el-form-item label="资讯获赞数">
                            <span>{{ props.row.likeCount }}</span>
                        </el-form-item>
                        <el-form-item label="资讯获踩数">
                            <span>{{ props.row.dislikeCount }}</span>
                        </el-form-item>
                        <el-form-item label="资讯评论数">
                            <span>{{ props.row.commentCount }}</span>
                        </el-form-item>
                        <el-form-item label="发布时间">
                            <span>{{ props.row.createDate}}</span>
                        </el-form-item>
                        <el-form-item label="发布人">
                            <span>{{ props.row.postUsername }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column width="850px"
                label="资讯标题"
                prop="newsTitle">
            </el-table-column>
            <el-table-column width="155px"
                label="发布时间"
                prop="createDate">
            </el-table-column>
            <el-table-column
                label="发布人"
                prop="postUsername">
            </el-table-column>
            <el-table-column width="80px" align="left">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="width: 100%; height: 50px">
            <div style="float: left; margin-top: 13px; margin-left: 20%;">
                <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[10, 20, 50, 100]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="newsTotal">
                </el-pagination>
            </div>
            <div style="float: right; margin-top: 10px; margin-right: 10px">
                <el-button size="small" type="info" @click="toggleSelection()" :disabled="multipleSelection.length === 0" round>取消选择</el-button>
                <el-button size="small" type="danger" @click="batchDel()" :disabled="multipleSelection.length === 0" round>确认删除</el-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ArticleManage",
        inject: ['reload'],
        data() {
            return {
                newsTotal: 0,
                pageSize: 10,
                currentPage: 1,
                multipleSelection: [],
                tableData: []
            }
        },
        mounted() {
            // 请求数据
            this.getData(this.currentPage, this.pageSize);
            // 查询一下资讯总数
            this.getNewsCount();
        },
        methods: {
            dateFormatScoped(date) {
                if(date){
                    let oDate=new Date();
                    oDate.setTime(date);

                    let y=oDate.getFullYear();
                    let M=oDate.getMonth()+1;
                    M = M > 9 ? M : '0' + M;
                    let d=oDate.getDate();
                    d = d > 9 ? d : '0' + d;

                    let h=oDate.getHours();
                    h = h > 9 ? h : '0' + h;
                    let m=oDate.getMinutes();
                    m = m > 9 ? m : '0' + m;
                    let s=oDate.getSeconds();
                    s = s > 9 ? s : '0' + s;

                    return y + '-' + M + '-' + d + ' ' + h + ':' + m + ':' + s;
                }
            },
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                // 从第一页开始，每页val条
                this.getData(1, val);
            },
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.getData(val, this.pageSize);
            },
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            batchDel() {
                this.$confirm('此操作将会删除多条资讯, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    roundButton: true
                }).then(() => {
                    // console.log(this.multipleSelection);
                    let delNewsIdList = [];
                    for (let i = 0; i < this.multipleSelection.length; i++) {
                        delNewsIdList.push(this.multipleSelection[i].id);
                    }
                    // console.log(delNewsIdList);
                    this.$http({
                        method: 'post',
                        url: '/api/admin/batchDelNews',
                        data: {
                            delNewsIdListStr: JSON.stringify(delNewsIdList)
                        }
                    }).then((response) => {
                        if (response.data.toutiaoCode === 0) {
                            this.$message({
                                type: 'success',
                                message: '删除成功!',
                                showClose: true
                            });
                            this.reload();
                        }
                    }).catch((error) => {
                        console.log(error);
                        this.$Message.error({
                            content: '服务器开小差了，操作失败',
                            duration: 3,
                            closable: true
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消',
                        showClose: true
                    });
                });

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            handleDelete(index, row) {
                // console.log(index, row);
                // console.log(row.id);
                this.$confirm('此操作将会删除此条资讯, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    roundButton: true
                }).then(() => {
                    this.$http({
                        method: 'get',
                        url: '/api/admin/delNews',
                        params: {
                            newsId: row.id
                        }
                    }).then((response) => {
                        if (response.data.toutiaoCode === 0) {
                            this.$message({
                                type: 'success',
                                message: '删除成功!',
                                showClose: true
                            });
                            this.reload();
                        } else {
                            this.$message({
                                type: 'error',
                                message: '删除失败',
                                showClose: true
                            });
                        }
                    }).catch((error) => {
                        console.log(error);
                        this.$message({
                            type: 'error',
                            message: '服务器开小差了，操作失败!',
                            showClose: true
                        });
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消',
                        showClose: true
                    });
                });
            },
            getData(currentPage, pageSize) {
                this.$http({
                    method: 'get',
                    url: '/api/getNewsListByCategoryId',
                    params: {
                        categoryId: -1,
                        page: currentPage,
                        pageSize: pageSize
                    }
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.tableData = response.data.toutiaoData.newsList;
                        this.newsTotal = response.data.toutiaoData.newsList.length;
                        for (let i = 0; i < this.tableData.length; i++) {
                            this.tableData[i].createDate = this.dateFormatScoped(this.tableData[i].createDate);
                        }
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，操作失败',
                        duration: 3,
                        closable: true
                    });
                });
            },
            getNewsCount() {
                this.$http({
                    method: 'get',
                    url: '/api/admin/getAllNewsCount'
                }).then((response) => {
                    if (response.data.toutiaoCode === 0) {
                        this.newsTotal = response.data.toutiaoData.newsTotal;
                    }
                }).catch((error) => {
                    console.log(error);
                    this.$Message.error({
                        content: '服务器开小差了，操作失败',
                        duration: 3,
                        closable: true
                    });
                })
            }
        },
    }
</script>

<style scoped>

    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;;
    }
    .demo-table-expand .el-form-item {
        margin-left: 63px;
        margin-right: 0;
        margin-bottom: 0;
        width: 100%;
    }
    span {
        color: #20a0ff;
    }

</style>
