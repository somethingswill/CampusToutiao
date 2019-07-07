<template>
    <div class="hello">
        <!--<h1>{{ msg }}</h1>-->
        <!--<el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>

        <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="活动名称" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="活动区域" :label-width="formLabelWidth">
                    <el-select v-model="form.region" placeholder="请选择活动区域">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>-->
        <!--<p>-->
        <!--<img src="data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABkAAD/4QMxaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzA2NyA3OS4xNTc3NDcsIDIwMTUvMDMvMzAtMjM6NDA6NDIgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE1IChNYWNpbnRvc2gpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkI2RjM2NDg3NUIxMzExRTc4MkJBQ0NFOEUwQjAxNkNFIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkI2RjM2NDg4NUIxMzExRTc4MkJBQ0NFOEUwQjAxNkNFIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QjZGMzY0ODU1QjEzMTFFNzgyQkFDQ0U4RTBCMDE2Q0UiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QjZGMzY0ODY1QjEzMTFFNzgyQkFDQ0U4RTBCMDE2Q0UiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7/7gAOQWRvYmUAZMAAAAAB/9sAhAABAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAgICAgICAgICAgIDAwMDAwMDAwMDAQEBAQEBAQIBAQICAgECAgMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwP/wAARCAAyADIDAREAAhEBAxEB/8QAjAAAAgMBAQEAAAAAAAAAAAAAAggABQcBBgoBAAMBAQAAAAAAAAAAAAAAAAABAgMEEAACAQMBAwcJBAsBAAAAAAABAgMRBAUAIRIGMUFRsSITB4HBMkLSFDQ1lWFSMxXwcZGhYpKyY8NkFjYRAQACAgIBBAMAAAAAAAAAAAABEdECITFx8GESMkGBA//aAAwDAQACEQMRAD8A+iCg/eevXTbjuXd0dGi5Fym6CaAEk8gFST5Bt0cny6yFDR0ZD0OrL/UBo5HLm6OjRclcpujo0XIuQ+zoyeRDznr0iXGCw11xBlrPE2lFlunO/KwqlvBGC89w4qKrFGK09Y0HPo9xEXNGwwHCWD4dt44bGziacBe9vp0SW7nkA2u0rAmMEnYqbqjo1N22iIhb3+Lx2Uhe3yNlbXkTqVKzxI5AP3HI342HMVII0jqCw8f8GjhW+hms2eTEZAye6mQ70lrOnaktJG9dQh3o2O0rUGpUk3HLLbWvDP8AQgHs6eVZEPOevSJr/g0sJz2UZ90zpiR3FaVCNdxCcrXk2hAfsOlPS/5mP1LRNAZf4uhDwlVqby5SwMVeUOe+Vt37e6LeTTjtO/1LFqmIPZ08qyIec9ekS+4azs/DeZs8tChlEBaO5gBp7xaTALPECdgcqAyE7A6iujuKPWamzc4bO4vP2aXuLu47iJgN9QQJoHI2xXEJ7cMq84YD9mo6bRMSsZ54LaJ57maKCGJS8ks0ixxog5Wd3IVVHSdALJ4j8ZxcSXkFhjXLYnHO7ibtKL67YbhmVTSsEKVWMkVYsx5CNVEV5Zb7XxDM9NAPZ08qyIec9ekS7wfD2W4iuvdMVatOy0M0zHu7a2U1o08zdlK02LtZuYHR0cRM9Now/g+loyXF9n76O5oCwwxNlusOYXTFpZVHNVF/VpWuNKWmW8KrTJIB/wBHxC7qOz+Z3f5pCCOSsLi369Hy8H8Y6Y9xNwFnOGQ1xPGl7jgae/2gZkjBIC+9REd5bkk0qapXYG07j9onWY5/DxWhAPZ08qyu+H8JdcQ5a0xVp2XuHJlmK7yW1tH2p7hx0RpyD1mIHPpSIi5o3+Fw1hgMfBjcdCIoIV7TUBlnlIHeXE70BkmkI2k8goBQAAS2qltpBNAA6JKjxyIskciskkbqHR0cFWR1YFWVlNCDsI0ArfiLwcvDWQS8sEIw+Sd+4TaRZXQG/JaVNT3TLVoq7d0EerU3E35Zb6110zb2dPJZa74PfPMt6fyofD/j/GQ+j/a+/wDxbupnpehh/qmpWn1TQE+qaAn1TQGa+K3/AJKT8f5jY/G8npv8P/sf497Va9p2+pZ/5fR/TyabP1693//ZLyogIHx4R3YwMHxkNTMwYmIwMWY1YzE0Y2E5YWRmZDgwNGNjNDNmN2MwZSAqLw==" alt="" width="50" height="50" />-->
        <!--我是好人，我是好人</p>-->

        <!--<p>-->
        <!--<img src="data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABkAAD/4QMxaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzA2NyA3OS4xNTc3NDcsIDIwMTUvMDMvMzAtMjM6NDA6NDIgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE1IChNYWNpbnRvc2gpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkI2RjM2NDg3NUIxMzExRTc4MkJBQ0NFOEUwQjAxNkNFIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkI2RjM2NDg4NUIxMzExRTc4MkJBQ0NFOEUwQjAxNkNFIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QjZGMzY0ODU1QjEzMTFFNzgyQkFDQ0U4RTBCMDE2Q0UiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6QjZGMzY0ODY1QjEzMTFFNzgyQkFDQ0U4RTBCMDE2Q0UiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7/7gAOQWRvYmUAZMAAAAAB/9sAhAABAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAgICAgICAgICAgIDAwMDAwMDAwMDAQEBAQEBAQIBAQICAgECAgMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwP/wAARCAAyADIDAREAAhEBAxEB/8QAjAAAAgMBAQEAAAAAAAAAAAAAAggABQcBBgoBAAMBAQAAAAAAAAAAAAAAAAABAgMEEAACAQMBAwcJBAsBAAAAAAABAgMRBAUAIRIGMUFRsSITB4HBMkLSFDQ1lWFSMxXwcZGhYpKyY8NkFjYRAQACAgIBBAMAAAAAAAAAAAABEdECITFx8GESMkGBA//aAAwDAQACEQMRAD8A+iCg/eevXTbjuXd0dGi5Fym6CaAEk8gFST5Bt0cny6yFDR0ZD0OrL/UBo5HLm6OjRclcpujo0XIuQ+zoyeRDznr0iXGCw11xBlrPE2lFlunO/KwqlvBGC89w4qKrFGK09Y0HPo9xEXNGwwHCWD4dt44bGziacBe9vp0SW7nkA2u0rAmMEnYqbqjo1N22iIhb3+Lx2Uhe3yNlbXkTqVKzxI5AP3HI342HMVII0jqCw8f8GjhW+hms2eTEZAye6mQ70lrOnaktJG9dQh3o2O0rUGpUk3HLLbWvDP8AQgHs6eVZEPOevSJr/g0sJz2UZ90zpiR3FaVCNdxCcrXk2hAfsOlPS/5mP1LRNAZf4uhDwlVqby5SwMVeUOe+Vt37e6LeTTjtO/1LFqmIPZ08qyIec9ekS+4azs/DeZs8tChlEBaO5gBp7xaTALPECdgcqAyE7A6iujuKPWamzc4bO4vP2aXuLu47iJgN9QQJoHI2xXEJ7cMq84YD9mo6bRMSsZ54LaJ57maKCGJS8ks0ixxog5Wd3IVVHSdALJ4j8ZxcSXkFhjXLYnHO7ibtKL67YbhmVTSsEKVWMkVYsx5CNVEV5Zb7XxDM9NAPZ08qyIec9ekS7wfD2W4iuvdMVatOy0M0zHu7a2U1o08zdlK02LtZuYHR0cRM9Now/g+loyXF9n76O5oCwwxNlusOYXTFpZVHNVF/VpWuNKWmW8KrTJIB/wBHxC7qOz+Z3f5pCCOSsLi369Hy8H8Y6Y9xNwFnOGQ1xPGl7jgae/2gZkjBIC+9REd5bkk0qapXYG07j9onWY5/DxWhAPZ08qyu+H8JdcQ5a0xVp2XuHJlmK7yW1tH2p7hx0RpyD1mIHPpSIi5o3+Fw1hgMfBjcdCIoIV7TUBlnlIHeXE70BkmkI2k8goBQAAS2qltpBNAA6JKjxyIskciskkbqHR0cFWR1YFWVlNCDsI0ArfiLwcvDWQS8sEIw+Sd+4TaRZXQG/JaVNT3TLVoq7d0EerU3E35Zb6110zb2dPJZa74PfPMt6fyofD/j/GQ+j/a+/wDxbupnpehh/qmpWn1TQE+qaAn1TQGa+K3/AJKT8f5jY/G8npv8P/sf497Va9p2+pZ/5fR/TyabP1693//ZLyogIHx4R3YwMHxkNTMwYmIwMWY1YzE0Y2E5YWRmZDgwNGNjNDNmN2MwZSAqLw==" alt="" width="50" height="50" />-->
        <!--我是好人</p>-->

        <!--<p>我是好人我是好人我是好人我是好人我人我是好人我是好人</p>-->
        <!--<TinymceEditor v-model="myEdit" style="margin-top: 10px"></TinymceEditor>-->
        <ul>
            <li v-for="val in list">
                {{val}}
                <br/>
            </li>
        </ul>
        <button @click="add">点击</button>
    </div>
</template>

<script>
    import TinymceEditor from "@/components/TinymceEditor/TinymceEditor";
    export default {
        name: 'HelloWorld',
        components: {TinymceEditor},
        data() {
            return {
                list: ['苹果', '桃子'],
                msg: 'Welcome to Your Vue.js App',
                dialogFormVisible: false,
                formLabelWidth: '120px',
                disabled: false,
                myEdit: '',
                form: {
                    name: '',
                    region: ''
                }
            }
        },
        methods: {
            add() {
                this.list.push("香蕉")
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h1, h2 {
        font-weight: normal;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }

    p {
        width: 100%;
    }

    p img {
        margin-left: 200px;
    }

</style>
