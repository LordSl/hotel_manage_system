<template>
    <div>
        <a-comment>
            <template slot="actions">
      <span key="reply-basic-like">
        <a-tooltip title="like">
          <a-icon type="like" :theme="action === 'liked' ? 'filled' : 'outlined'" @click="likeReply" />
        </a-tooltip>
        <span style="padding-left:8px;cursor: auto">
          {{ reply.favor }}
        </span>
      </span>
                <span slot="actions" key="comment-nested-reply-to" @click="replyTo(reply.fromId, reply.fromName,reply.fromAvatar)">
                <a-icon type="form" style="margin-right: 10px"/>
                <span>回复</span></span>

                <span slot="actions" key="comment-nested-reply-to" @click="rDelete" v-if="reply.fromId===userInfo.id">
                <a-icon type="delete" style="margin-right: 10px"/>
                <span>删除回复</span></span>
            </template>
            <a slot="author">{{reply.fromName}} -> {{reply.toName}}</a>
            <a-avatar
                    slot="avatar"
                    :src=reply.fromAvatar
                    :alt=reply.fromName
            />
            <p slot="content">
                {{reply.content}}
            </p>
            <a-tooltip slot="datetime" :title="moment().format('YYYY-MM-DD HH:mm:ss')">
                <span>{{reply.createTime }}</span>
            </a-tooltip>
        </a-comment>
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
    export default {
        name: "" ,
        props: {
            reply: {} ,
            comment: {}
        } ,
        data() {
            return {
                action: null ,
                moment ,
                showInputModal: false ,
                value: '' ,
                replyVal: '' ,
                replyTitle: '' ,
            };
        } ,
        computed: {
            ...mapGetters([
                'currentCommentsStatus' ,
                'userId' ,
                'currentHotelId' ,
                'userInfo',

            ]) ,
        } ,
        mounted() {
            var that = this
            if (this.currentCommentsStatus.length > 0) {
                this.currentCommentsStatus.filter(item => ((item.userId == that.userId) && (item.replyId == that.reply.id))).forEach(function (item) {
                    if (item.state == 1) {
                        that.action = 'liked'
                    }
                })
            }

        } ,
        methods:{
            ...mapActions([
                'getHotelCommentsById' ,
                'addCommentStatus' ,
                'deleteCommentStatus' ,
                'getCommentStatus' ,
                'addReply' ,
                'increaseReplyLike' ,
                'getCommentStatus' ,
                'getHotelCommentsById',
                'deleteReplyStatus',
                'deleteReply',
            ]) ,
            onClose() {
                this.showInputModal = false
            } ,
            handleChange(e) {
                this.replyVal = e.target.value
            } ,
            handleSubmit() {
                const data = {
                    commentId: Number(this.comment.id) ,
                    fromId: Number(this.userId) ,
                    fromName: this.userInfo.userName ,
                    fromAvatar: this.userInfo.avatarUrl ,
                    content: this.replyVal ,
                    favor: 0 ,
                    createTime: moment().toDate() ,
                    toId: Number(this.reply.fromId) ,
                    toName: this.reply.fromName ,
                    toAvatar: this.reply.fromAvatar ,
                    state: Number(0)
                }
                console.log(data)
                this.addReply(data).then(() => {
                    this.replyVal = ''
                    this.showInputModal = false
                })
            },

            replyTo(id , name , avatar) {
                this.replyVal = "@" + name + " "
                this.replyTitle = name
                this.showInputModal = !this.showInputModal
            } ,
            likeReply() {
                if (this.action == null) {
                    const data = {
                        userId: Number(this.userId) ,
                        commentId: Number(-1) ,
                        replyId: Number(this.reply.id) ,
                        state: Number(1) ,
                    }
                    this.addCommentStatus(data)
                    const params = {
                        id: Number(this.reply.id) ,
                        num: Number(1)
                    }
                    this.increaseReplyLike(params).then(() => {
                        this.getHotelCommentsById()
                    })
                    this.action = 'liked'
                } else if (this.action == 'liked') {
                    const data2 = {
                        userId: Number(this.userId) ,
                        replyId: Number(this.reply.id) ,
                    }
                    this.deleteReplyStatus(data2)
                    const params = {
                        id: Number(this.reply.id),
                        num: Number(-1)
                    }
                    this.increaseReplyLike(params).then(() => {
                        this.getHotelCommentsById()
                    })
                    this.action = null
                }
            },
            rDelete(){
                const params={
                    id:Number(this.reply.id)
                }
                this.deleteReply(params)
            },
        }
    };
</script>