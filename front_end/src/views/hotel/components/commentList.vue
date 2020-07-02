<template>
    <div>
        <a-comment>
        <a-avatar
                slot="avatar"
                :src=userInfo.avatarUrl
                :alt=userInfo.userName
        />
        <div slot="content">
            <a-form-item>
                <a-textarea :rows="4" :value="value" @change="handleChange" />
            </a-form-item>
            <span style="margin-right: 10px">您的评分</span>
            <a-rate :default-value="0" allow-half v-model="comment_score" />
            <span>
                <div class="picList">
            <img :src="item" v-for="item in commentImgList" class="pic" :key="item" @click="showBrief(item)"> </div>
            </span>



            <a-form-item>
                <span style="margin-right: 30px"><a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmit" ghost>
                    添加评论
                </a-button>
                </span>

                <a-upload
                        name="file"
                        :multiple="true"
                        :customRequest="customRequest"
                        :showUploadList = false
                >
                    <a-button type="primary"  icon="cloud-upload" ghost>
                        酒店图片上传
                    </a-button>
                </a-upload>

            </a-form-item>

        </div>

    </a-comment>
        <a-modal :visible="briefVisible" :footer="null" @cancel="handleCancel">
            <img alt="example" style="width: 100%" :src="briefImg" />
        </a-modal>
        <a-list
                v-if="currentHotelComments.length"
                :data-source="currentHotelComments"
                :header="`共有${currentHotelComments.length}条评论`"
                item-layout="horizontal"
                :pagination="true"
        >
            <a-list-item slot="renderItem" slot-scope="item" >
                <Comment :comment="item" v-bind:key="item.id"></Comment>
            </a-list-item>
        </a-list>
    </div>
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import Comment from './comment'
    import moment from 'moment';
    export default {
        data() {
            return {
                submitting: false,
                value: '',
                comment_score:0,
                moment,
                briefVisible:false,
                briefImg:''
            };
        },
        components:{
            Comment,
        },
        computed: {
            ...mapGetters([
                'currentHotelComments',
                'userId',
                'currentHotelId',
                'userInfo',
                'currentCommentsStatus',
                'commentImgList'
            ]),
        },
        methods: {
            ...mapActions([
                'addComment',
                'getHotelCommentsById',
                'uploadCommentImg'
            ]),
            handleCancel(){
                this.briefVisible=false
            },
            handleSubmit() {
                if (!this.value) {
                    return;
                }
                console.log(moment().toDate())
                const data={
                    userId:Number(this.userId),
                    hotelId:Number(this.currentHotelId),
                    content:this.value,
                    createTime:moment().toDate(),
                    favor:Number(0),
                    score: Number(this.comment_score),
                    userName:this.userInfo.userName,
                    userAvatar:this.userInfo.avatarUrl,
                }
                this.addComment(data).then(()=>{
                    this.value='';
                    this.comment_score='';
                })
            },
            handleChange(e) {
                this.value = e.target.value;
            },
            onchange(){
                this.getHotelCommentsById()
            },
            customRequest (data) {
                const formData = new FormData()
                formData.append('file', data.file)
                this.uploadCommentImg(formData)
            },
            showBrief(text){
                this.briefImg=text
                this.briefVisible=true
            }
        },
        watch:{
        }
    };
</script>
<style scoped lang="less">
    .picList{
        display: flex;
        .pic{
            height: 100px;
            margin-right: 5px;
        }
    }

</style>