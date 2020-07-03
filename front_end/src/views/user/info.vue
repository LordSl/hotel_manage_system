<template>
    <div class="info-wrapper">

        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">

                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item label="头像" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                                <img
                                        alt="example"
                                        :src=this.userInfo.avatarUrl
                                        width="240px"
                                        height="240px"
                                />
                    </a-form-item>

                    <a-upload
                        name="file"
                        :multiple="true"
                        :customRequest="customRequest"
                        :showUploadList = false
                        style="margin-left: 300px"
                >
                    <a-button type="primary" > <a-icon type="upload" /> 更换头像 </a-button>
                </a-upload>

                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                            placeholder="请填写用户名"
                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>
                    
                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                            placeholder="请填写手机号"
                            v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                            placeholder="请输入新密码"
                            v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                        />
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                     <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo" style="margin-right: 30px">
                            修改信息
                        </a-button>
                         <a-button type="primary" @click="rechargeCredit">
                             信用充值
                         </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2">
                <order-detail-card ></order-detail-card>
                <a-table
                        :columns="columns"
                        :dataSource="userOrderList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>

                    <span slot = "orderState" slot-scope="record">
                            <a-tag  color="blue">
                                 {{ record.orderState}}
                            </a-tag>
                        <a-tag  color='blue' v-if="record.appeal!= null && record.appeal.appealStatus =='申诉待审核'">
                                 {{ record.appeal.appealStatus}}
                        </a-tag>
                        <a-tag  color='red' v-if="record.appeal!= null && record.appeal.appealStatus =='审核未通过'">
                                 {{ record.appeal.appealStatus}}
                        </a-tag>
                        <a-tag  color='green' v-if="record.appeal!= null && record.appeal.appealStatus =='审核通过'">
                                 {{ record.appeal.appealStatus}}
                        </a-tag>
                    </span>

                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrderDetail(record)">查看</a-button>
<!--                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>-->
                        <a-divider type="vertical"></a-divider>

                        <a-popconfirm
                            title="你确定撤销该笔订单吗？"
                            @confirm="confirmCancelOrder(record.id)"
                            @cancel="cancelCancelOrder"
                            okText="确定"
                            cancelText="取消"
                            v-if="record.orderState == '已预订'">
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>

<!--                        订单申诉-->
                        <a-button type="danger"  size = "small" v-if="record.orderState =='已撤销' &&(record.appeal == null)" @click="appealShow(record.id)">申诉</a-button>

<!--                        订单申诉-->


                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="我的消息" key="3">
                <a-list item-layout="horizontal" :data-source="userReplyList">

                    <a-list-item slot="renderItem" slot-scope="item">
                        <a-modal :visible="detailReplyVisible" :title="toName"
                                 ok-text="回复"
                                 cancel-text="取消"
                                 @ok="replyToSon(toId,toName,toAvatar,commentId)"
                                 @cancel="handleCancle"
                        >
                            <p>{{content}}</p>
                        </a-modal>
                        <a-button type="primary" @click="handleDetail(item.fromId,item.fromName,item.fromAvatar,item.commentId,item.content)" style="margin-right: 10px">
                            查看
                        </a-button>
                        <a-button type="primary" @click="replyToSon(item.fromId,item.fromName,item.fromAvatar,item.commentId)">
                            回复
                        </a-button>

                        <a-list-item-meta
                        >
                            <a slot="description" style="color:black">{{item.content| ellipsis}}</a>
                            <a slot="title">{{item.fromName}}   {{ item.createTime }}</a>
                            <a-avatar
                                    slot="avatar"
                                    :src="item.fromAvatar"
                                    shape="square"
                            />

                        </a-list-item-meta>
                    </a-list-item>
                </a-list>
        </a-tab-pane>
            <a-tab-pane tab="会员中心" key="4">
                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item  label="是否为网站会员" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-button type="primary" style="font-weight: bold;color: #42b983" size="large" icon="crown"
                                  v-if="userInfo.isWebVIP===1" ghost >您已成为尊贵的网站会员</a-button>
                        <span v-if="userInfo.isWebVIP===0">非会员</span>
                        <a-button type="danger" size="large" icon = "fire" style="left: 30px;;color: crimson" ghost
                                  v-if="userInfo.isWebVIP===0" @click="registerWebVIPBtn">成为网站会员</a-button>
                    </a-form-item>

                    <a-table
                            :columns="columns2"
                            :dataSource="hotelListOfCurrentVIP"
                            bordered
                    >
                        <span slot="hotelImg" slot-scope="text">
                            <img :src="text" style="height: 150px;width: 150px">
                        </span>
                        <span slot="action" slot-scope="record">
                            <a-button type="primary" size="small" icon="bank" ghost @click="jumpToHotelDeteil(record)">前往酒店主页</a-button>
                            <a-divider></a-divider>
                            <a-button type="primary" size="small" icon="database" ghost @click="showHistoryDetailOfTheHotel(record)">查看酒店订单</a-button>

                        </span>
                    </a-table>


                </a-form>

            </a-tab-pane>

            <a-tab-pane tab="信用中心" key="5">
                <a-form :form="form" style="margin-top: 30px">
                    <div style="width: 100%; text-align: right; margin:30px 0">
                        <a-button type="danger"  icon = "fire" ghost
                                  v-if="userInfo.isWebVIP===0" @click="rechargeCredit">信用充值</a-button>
                    </div>

                    <a-table
                            :columns="columns3"
                            :dataSource="creditChangeList"
                            bordered
                    >
                        <span slot="hotelImg" slot-scope="text">
                            <img :src="text" style="height: 150px;width: 150px">
                        </span>
                        <span slot="action" slot-scope="record">
                            <a-button type="primary" size="small" icon="bank" ghost @click="jumpToHotelDeteil(record)">前往酒店主页</a-button>
                            <a-divider></a-divider>
                            <a-button type="primary" size="small" icon="database" ghost @click="showHistoryDetailOfTheHotel(record)">查看酒店订单</a-button>

                        </span>
                    </a-table>


                </a-form>
            </a-tab-pane>

        </a-tabs>
        <CreditRechargeModal></CreditRechargeModal>
        <cancel-order-appeal-modal></cancel-order-appeal-modal>
        <history-order-modal></history-order-modal>
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
                <a-button type="primary" @click="handleReplySubmit">
                    提交回复
                </a-button>
            </div>
        </a-drawer>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import moment from 'moment/moment';
import CreditRechargeModal from './components/creditRechargeModal'
import cancelOrderAppealModal from './components/cancelOrderAppealModal'
import orderDetailCard  from "./components/orderDetailCard";
import historyOrderModal from "./components/historyOrderModal"
const columns = [
    {  
        title: '订单号',
        dataIndex: 'id',

    },
    {  
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',

    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
    },
    {
        title: '状态',
        key: 'state',
        scopedSlots: { customRender: 'orderState' },
        filters: [{ text: '异常', value: '异常' },{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' },{ text: '申诉待审核', value: '申诉待审核' },{ text: '申诉成功', value: '审核通过' },{ text: '申诉失败', value: '审核未通过' }],
        onFilter: (value, record) => (record.orderState.includes(value) ||  (record.appeal!=null && record.appeal.appealStatus.includes(value))),
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' }
    },
    
  ];
const columns2 = [
    {
       title:'酒店图片',
       dataIndex : 'hotelImg',
       key : "img",
       scopedSlots: { customRender: 'hotelImg' },

    },
    {
        title: '酒店名',
        dataIndex: 'name',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '酒店星级',
        dataIndex: 'hotelStar'
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
const columns3 = [
    {
        title:'时间',
        dataIndex : 'time',
        key : "time"
    },
    {
        title: '变更原因',
        dataIndex: 'reason',
        key : "reason"
    },
    {
        title: '变更大小',
        dataIndex: 'value',
        key : "value"
    }
];

export default {
    name: 'info',
    data(){
        return {
            modify: false,
            formLayout: 'horizontal',
            pagination: {},
            columns,
            columns2,
            columns3,
            form: this.$form.createForm(this, { name: 'coordinated' }),
            appealed: false,
            detailReplyVisible:false,
            showInputModal:false,
            replyVal:'',
            toId:'',
            toName:'',
            toAvatar:'',
            commentId:'',
            replyTitle:'',
            content:'',
        }
    },
    filters: {
        ellipsis(value) {
            if (!value) return ''
            if (value.length > 100) {
                return value.slice(0 , 8) + '......'
            }
            return value
        }
    },
    components: {
        historyOrderModal,
        cancelOrderAppealModal,
        CreditRechargeModal,
        orderDetailCard
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'userOrderList',
            'userReplyList',
            'orderDetailCardVisible',
            'hotelListOfCurrentVIP',
            'historyOrderList',
            'creditChangeList'
        ])
    },
    async mounted() {
        await this.getUserInfo()
        await this.autoUpdateOrderStatus()
        await this.getUserOrders()
        await this.getUserReplies()
        await this.getHotelListOfCurrentVIP()
        await  this.getCreditChangeList()
    },
    methods: {
        ...mapActions([
            'getUserInfo',
            'getUserOrders',
            'updateUserInfo',
            'cancelOrder',
            'updateUserImg',
            'getUserReplies',
            'addReply',
            'registerWebVIP',
            'autoUpdateOrderStatus',
            'getHotelListOfCurrentVIP',
            'getCreditChangeList'
        ]),
        ...mapMutations([
            'set_creditRechargeModalVisible',
            'set_cancelOrderAppealModalVisible',
            'set_orderDetailCardVisible',
            'set_appealOrderId',
            'set_currentOrderOfUser',
            'set_historyOrderModalVisible',
            'set_historyOrderList'
        ]),
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                        password: this.form.getFieldValue('password')
                    }
                    this.updateUserInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },
        modifyInfo() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'userName': this.userInfo.userName,
                    'phoneNumber': this.userInfo.phoneNumber,
                })
            }, 0)
            this.modify = true
        },
        replyToSon(id,name,avatar,commentId){
            this.replyVal="@"+name+" "
            this.replyTitle=name
            this.toId=id
            this.toName=name
            this.toAvatar=avatar
            this.showInputModal=!this.showInputModal
            this.commentId=commentId
            this.detailReplyVisible=false
        },
        rechargeCredit(){
            this.set_creditRechargeModalVisible(true);
        },
        appealShow(orderId){
            this.set_cancelOrderAppealModalVisible(true);
            this.set_appealOrderId(orderId);
        },
        cancelModify() {
            this.modify = false
        },
        showOrderDetail(record){
            console.log(record)
            this.set_orderDetailCardVisible(!this.orderDetailCardVisible)
            this.set_currentOrderOfUser(record)
        },
        confirmCancelOrder(orderId){
            this.cancelOrder(orderId)
        },
        cancelCancelOrder() {

        },
        customRequest (data) {
            const formData = new FormData()
            formData.append('file', data.file)
            console.log(data)
            this.updateUserImg(formData)
        },
        handleDetail(id,name,avatar,commentId,content){
            this.detailReplyVisible=true
            this.toId=id
            this.toName=name
            this.toAvatar=avatar
            this.commentId=commentId
            this.content=content
        },
        onClose(){
            this.showInputModal=false
        },
        handleCancle(){
            this.detailReplyVisible=false
        },
        registerWebVIPBtn(){
            const data={
                userId : this.userId,
                isWebVIP : 1
            }
            this.registerWebVIP(data)
        },
        handleReplySubmit(){
            const data={
                commentId:Number(this.commentId),
                fromId:Number(this.userId),
                fromName:this.userInfo.userName,
                fromAvatar:this.userInfo.avatarUrl,
                content:this.replyVal,
                favor:0,
                createTime:moment().toDate(),
                toId:Number(this.toId),
                toName:this.toName,
                toAvatar:this.toAvatar,
                state:Number(1)
            }
            this.addReply(data).then(() => {
                this.replyVal=''
                this.showInputModal=false
                this.toId=''
                this.toName=''
                this.toAvatar=''
                this.commentId=''
            })

        },
        showHistoryDetailOfTheHotel(record){
            let specificHotelId = record.id
            let newList = []
            for(let i = 0; i < this.userOrderList.length; i++){
                if(this.userOrderList[i].hotelId === specificHotelId){
                    newList.push(this.userOrderList[i])
                }
            }
            this.set_historyOrderList(newList)
            this.set_historyOrderModalVisible(true)

        },
        onChange(item){
            console.log(item)
        },
        jumpToHotelDeteil(record){
            // console.log(record)
            this.$router.push({name:'hotelDetail',params:{hotelId:record.id}})
        }
    }
}
</script>
<style scoped lang="less">
    .info-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>