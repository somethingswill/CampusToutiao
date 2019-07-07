<template>
    <div class="tinymceEditor">
        <editor v-model="myValue"
                :init="init"
                :disabled="disabled"
                @onClick="onClick">
        </editor>
    </div>
</template>

<script>
    import tinymce from 'tinymce/tinymce'
    import Editor from '@tinymce/tinymce-vue'
    import 'tinymce/themes/silver'
    // 编辑器插件plugins
    // 更多插件参考：https://www.tiny.cloud/docs/plugins/
    import 'tinymce/plugins/image' // 插入上传图片插件
    // import 'tinymce/plugins/media' // 插入视频插件
    import 'tinymce/plugins/table' // 插入表格插件
    import 'tinymce/plugins/lists' // 列表插件
    import 'tinymce/plugins/wordcount' // 字数统计插件
    import 'tinymce/plugins/preview' // 预览插件
    import 'tinymce/plugins/fullscreen' // 全屏插件

    import axios from 'axios'
    // Vue.prototype.$http = axios

    export default {
        components: {
            Editor
        },
        props: {
            value: {
                type: String,
                default: ''
            },
            disabled: {
                type: Boolean,
                default: false
            },
            plugins: {
                type: [String, Array],
                default: 'lists image table wordcount preview fullscreen'
            },
            toolbar: {
                type: [String, Array],
                default: 'undo redo |  formatselect | bold italic forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | lists image table | removeformat preview fullscreen'
            }
        },
        data() {
            return {
                init: {
                    language_url: '/static/tinymce/langs/zh_CN.js',
                    language: 'zh_CN',
                    skin_url: '/static/tinymce/skins/ui/oxide',
                    // skin_url: '/tinymce/skins/ui/oxide-dark',//暗色系
                    height: 600,
                    plugins: this.plugins,
                    toolbar: this.toolbar,
                    branding: false,
                    menubar: false,
                    // 此处为图片上传处理函数，这个直接用了base64的图片形式上传图片，
                    // 如需ajax上传可参考https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_handler
                    images_upload_handler: (blobInfo, success, failure) => {
                        // const img = 'data:image/jpeg;base64,' + blobInfo.base64();
                        // success(img);
                        // let formData = new FormData();
                        // // 服务端接收文件的参数名，文件数据，文件名
                        // // formData.append('newsImage', blobInfo.blob(), blobInfo.filename());
                        // formData.append('newsImage', blobInfo.blob());
                        // // formData.set('newsImage', blobInfo.blob());
                        // axios({
                        //     method: 'POST',
                        //     // 这里是你的上传地址
                        //     url: '/api/uploadNewsImage',
                        //     data: formData
                        // }).then((res) => {
                        //     // 这里返回的是你图片的地址
                        //     success(res.toutiaoData.newsImageUrl);
                        // }).catch(() => {
                        //     failure('上传失败');
                        // });
                        // let formdata = new FormData()
                        // formdata.append('file', blobInfo.blob())
                        // axios.post('/api/uploadNewsImage', formdata).then(res => {
                        //     success(res.toutiaoData.newsImageUrl)
                        // }).catch(res => {
                        //     failure('error')
                        // })

                        // 必须用原生ajax请求上传图片
                        let xhr, formData;

                        xhr = new XMLHttpRequest();
                        xhr.withCredentials = true;
                        xhr.open('POST', '/api/uploadNewsImage');

                        xhr.onload = function () {
                            let json;
                            if (xhr.status !== 200) {
                                failure('HTTP Error: ' + xhr.status);
                                return;
                            }
                            json = JSON.parse(xhr.responseText);
                            if (!json || typeof json.toutiaoData.newsImageUrl !== 'string') {
                                failure('Invalid JSON: ' + xhr.responseText);
                                return;
                            }
                            success(json.toutiaoData.newsImageUrl);
                        };

                        formData = new FormData();
                        formData.append('newsImage', blobInfo.blob(), blobInfo.filename());
                        xhr.send(formData);
                    }
                },
                myValue: this.value
            }
        },
        mounted() {
            tinymce.init({})
        },
        methods: {
            // 添加相关的事件，可用的事件参照文档=> https://github.com/tinymce/tinymce-vue => All available events
            // 需要什么事件可以自己增加
            onClick(e) {
                this.$emit('onClick', e, tinymce)
            },
            // 可以添加一些自己的自定义事件，如清空内容
            clear() {
                this.myValue = ''
            }
        },
        watch: {
            value(newValue) {
                this.myValue = newValue
            },
            myValue(newValue) {
                this.$emit('input', newValue)
            }
        }
    }

</script>

<style scoped>

</style>
