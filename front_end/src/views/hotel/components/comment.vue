<template>
    <div>
        <a-comment>
            <template slot="actions">
      <span key="comment-basic-like">
        <a-tooltip title="Like">
          <a-icon type="like" :theme="action === 'liked' ? 'filled' : 'outlined'" @click="like" />
        </a-tooltip>
        <span style="padding-left:8px;cursor: auto">
          {{ comment.favor }}
        </span>
      </span>
                <span slot="actions" key="comment-nested-reply-to" @click="reply">
                <a-icon type="form" style="margin-right: 10px"/>
                <span>回复</span></span>
                <span slot="actions" key="comment-nested-reply-to" @click="showReplyList" v-if="comment.replyList.length>0">
                共有{{comment.replyList.length}}条回复
            </span>
                <span slot="actions" key="comment-nested-reply-to" @click="cDelete" v-if="comment.userId===userInfo.id">
                <a-icon type="delete" style="margin-right: 10px"/>
                <span>删除评论</span></span>
                <span slot="actions" key="comment-nested-reply-to" v-if="flag">
                    <a-icon type="check-circle" theme="twoTone" two-tone-color="#52c41a" style="font-size: 12px;" class="label" ></a-icon>
                    <span>来自真实入住客人</span>
                </span>

            </template>
            <div class="picList" v-if="comment.picList.length>0">

                <img  :src="item"  class="pic" @click="showBrief(item)" v-for="item in comment.picList" :key="item">
            </div>

            <span style="margin-right: 10px">用户评分</span>
            <a-rate allow-half :value="comment.score" disabled></a-rate>

            <a-list
                    v-if="replyVisible&&comment.replyList.length>0"
                    :data-source="comment.replyList"
                    item-layout="horizontal"
            >
                <a-list-item slot="renderItem" slot-scope="item" >
                    <Reply :reply="item" v-bind:key="item.id" :comment="comment"></Reply>
                </a-list-item>
            </a-list>
            <a slot="author">{{comment.userName}}</a>
            <a-avatar
                    slot="avatar"
                    :src=comment.userAvatar
                    :alt=comment.userName
            />
            <p slot="content">
                {{comment.content}}
            </p>
            <a-tooltip slot="datetime" :title="moment().format('YYYY-MM-DD HH:mm:ss')">
                <span>{{comment.createTime }}</span>
            </a-tooltip>
        </a-comment>
        <a-modal :visible="briefVisible" :footer="null" @cancel="handleCancel">
            <img alt="example" style="width: 100%" :src="briefImg" />
        </a-modal>
        <div>
            <a-drawer
                    :height=500
                    :visible="showInputModal"
                    :body-style="{ paddingBottom: '80px' }"
                    placement="bottom"
                    @close="onClose"
            >
                <a-drawer-title>回复给{{replyTitle}}</a-drawer-title>
                <a-input
                        v-model="replyVal"
                        type="textarea"
                        placeholder="请写下你的回复"
                ></a-input>
                <div
                        :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
                >
                    <a-button :style="{ marginRight: '8px' }" @click="onClose">
                        取消回复
                    </a-button>
                    <a-button type="primary" @click="handleSubmit">
                        提交回复
                    </a-button>
                </div>
            </a-drawer>
        </div>
    </div>
</template>
<script>
    import moment from 'moment/moment';
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import Reply from './reply'
    export default {
        name:"",
        props: {
            comment: {},
        },

        data() {
            return {
                action: null,
                moment,
                replyVisible:false,
                showInputModal:false,
                value:'',
                replyVal:'',
                replyTitle:'',
                flag:false,
                briefVisible:false,
                briefImg:''
            };
        },
        components:{
            Reply
        },
        computed: {
            ...mapGetters([
                'currentCommentsStatus',
                'userId',
                'currentHotelId',
                'userInfo',
                'commentFlag'
            ]),
        },
        mounted() {
            var that=this
            if(this.currentCommentsStatus.length>0) {
                this.currentCommentsStatus.filter(item => ((item.userId == that.userId) && (item.commentId == that.comment.id))).forEach(function (item) {
                    if (item.state == 1) {
                        that.action = 'liked'
                    }
                })
            }
            let res=Promise.resolve(this.getCommentFlag({userId:this.comment.userId, hotelId:this.currentHotelId}))
            let ress=res.then((v)=>{
                that.flag=v
            })
            console.log(this.flag)
        },
        methods: {
            ...mapActions([
                'increaseCommentLike' ,
                'getHotelCommentsById' ,
                'addCommentStatus' ,
                'deleteCommentStatus' ,
                'getCommentStatus',
                'addReply',
                'increaseReplyLike',
                'getCommentStatus',
                'getHotelCommentsById',
                'deleteComment',
                'getCommentFlag',
                'uploadCommentImg'
            ]) ,
            handleCancel(){
                this.briefVisible = false;
            },
            showBrief(text){
                this.briefVisible = true;
                this.briefImg=text
            },
            onClose(){
                this.showInputModal=false
            },
            handleChange(e){
                this.replyVal=e.target.value
            },
            handleSubmit(){
                if(this.replyVal!=''){
                        const data={
                            commentId:Number(this.comment.id),
                            fromId:Number(this.userId),
                            fromName:this.userInfo.userName,
                            fromAvatar:this.userInfo.avatarUrl,
                            content:this.replyVal,
                            favor:0,
                            createTime:moment().toDate(),
                            toId:Number(this.comment.userId),
                            toName:this.comment.userName,
                            toAvatar:this.comment.userAvatar,
                            state:Number(1)
                        }
                        this.addReply(data).then(() => {
                            this.replyVal=''
                            this.showInputModal=false
                        })

                    }
                },
            showReplyList(){
                this.replyVisible=!this.replyVisible
            },
            reply(){
                this.replyVal="@"+this.comment.userName+" "
                this.replyTitle=this.comment.userName
                this.showInputModal=!this.showInputModal
            },
            like() {
                    if (this.action == null) {
                        const data = {
                            userId: Number(this.userId) ,
                            commentId: Number(this.comment.id),
                            replyId:Number(-1),
                            state: Number(1) ,
                        }
                        this.addCommentStatus(data)
                        const params = {
                            id: Number(this.comment.id),
                            num:Number(1)
                        }
                        this.increaseCommentLike(params).then(() => {
                            this.getHotelCommentsById()
                        })
                        this.action = 'liked'
                    }else if(this.action=='liked'){
                        const data2 = {
                            userId: Number(this.userId) ,
                            commentId: Number(this.comment.id) ,
                        }
                        this.deleteCommentStatus(data2)
                        const params = {
                            id: Number(this.comment.id),
                            num: Number(-1)
                        }
                        this.increaseCommentLike(params).then(() => {
                            this.getHotelCommentsById()
                        })
                        this.action = null
                    }
            } ,
            cDelete(){
                const data={
                    id:this.comment.id
                }
                this.deleteComment(data)
            }

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