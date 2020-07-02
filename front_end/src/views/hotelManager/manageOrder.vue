<template>
    <div class="manageHotel-wrapper">
        <a-tabs  >

            <a-tab-pane tab="订单管理" key="2">
                <a-table
                    :columns="columns2"
                    :dataSource="orderListOfCurrentHotel"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        ￥{{ text }}
                    </span>

<!--                    加入订单状态-->
                    <span slot = "orderState"  slot-scope="record">
                         <a-tag  color="blue" >
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
<!--                    加入订单状态-->


                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record" > <!--slot-scope记录槽-->
                        <a-button type="primary" size="small" ghost  icon="profile" @click="showOrderDetail(record)">订单详情</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该订单吗？"
                            @confirm="comfirmDeleteOrder(record.id)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" icon="delete" size="small" ghost >删除订单</a-button>
                        </a-popconfirm>


<!--                        实际入住&退房-->
                        <a-divider ></a-divider>
                        <a-button  v-if="record.orderState !== '已撤销'&&record.orderState !== '已入住'"  type="primary" size="small" icon="home" ghost @click="checkedInBtn(record)">登记入住</a-button>
                        <a-button style="color: gray" v-if="record.orderState === '已撤销'"  type="defalut" size="small" icon="home" ghost @click="$message.error('用户已撤销订单')">登记入住</a-button>
                        <a-button style="color: gray" v-if="record.orderState === '已入住'"  type="defalut" size="small" icon="home" ghost @click="$message.error('已完成入住登记')">登记入住</a-button>

<!--                        实际入住&退房-->
                        <!--                        订单申诉相关-->
                            <a-divider type="vertical"  ></a-divider>
                            <a-button type="danger" ghost size="small" icon="message" v-if="record.appeal!=null && record.appeal.appealStatus=='申诉待审核'" @click="checkAppeal(record)">查看申诉</a-button>
                            <a-button type="defalut" ghost size="small" style="color: gray" icon="message" v-else-if="record.appeal!=null " @click="checkAppeal(record)">重新审核</a-button>

                            <a-button type="defalut" style="color: gray" ghost size="small" icon="message" v-if="record.appeal==null" @click="$message.error('用户尚未申诉') ">没有申诉</a-button>
                        <!--                         订单申诉相关-->
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddHotelModal></AddHotelModal>
        <UpdateHotelModal></UpdateHotelModal>
        <AddRoomModal></AddRoomModal>
        <Coupon></Coupon>
        <appeal-review-modal></appeal-review-modal>
        <order-detail-card></order-detail-card>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddHotelModal from './components/addHotelModal'
import UpdateHotelModal from './components/updateHotelModal'
import AddRoomModal from './components/addRoomModal'
import Coupon from './components/coupon'
import appealReviewModal from './components/appealReviewModal'
import orderDetailCard from "./components/orderDetailCard";
import OrderDetailCard from "../user/components/orderDetailCard";
const moment = require('moment')
const columns1 = [
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
const columns2 = [
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
        scopedSlots: { customRender: 'roomType' }
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
        scopedSlots: { customRender: 'price' },
    },
    {
        title: '状态',
        key: 'state',
        filters: [{ text: '异常', value: '异常' },{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' },{ text: '申诉待审核', value: '申诉待审核' },{ text: '申诉成功', value: '审核通过' },{ text: '申诉失败', value: '审核未通过' }],
        onFilter: (value, record) => (record.orderState.includes(value) ||  (record.appeal!=null && record.appeal.appealStatus.includes(value))),
        scopedSlots: { customRender: 'orderState' }
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns1,
            columns2,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
        }
    },
    components: {
        OrderDetailCard,
        appealReviewModal,
        AddHotelModal,
        UpdateHotelModal,
        AddRoomModal,
        Coupon,
        orderDetailCard
    },
    computed: {
        ...mapGetters([
            'userInfo',
            'userId',
            // 'orderList',
            'orderListOfCurrentHotel',
            'hotelList',
            'addHotelModalVisible',
            'updateHotelModalVisible',
            'addRoomModalVisible',
            'currentHotelId',
            'couponVisible',
            'currentHotelId',
            'currentHotelInfo',//加入currentHotelInfo用于显示剩余房间
            'orderDetailCardVisibleHM',
            'currentOrderOfHM'
        ]),
    },
    async mounted() {

        await this.getHotelByHMId(this.userId)
        await this.autoUpdateOrderStatus()
        await this.getOrderListOfCurrentHotel()
    },
    methods: {
        ...mapMutations([
            'set_appealReviewOrderId',
            'set_addHotelModalVisible',
            'set_updateHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_currentHotelId',
            'set_appealReviewModalVisible',
            'set_appealCreditRechargeimfo',
            'set_orderDetailCardVisibleHM',
            'set_currentOrderOfHM'
        ]),
        ...mapActions([
            'getHotelList',
            // 'getAllOrders',
            'getHotelByHMId',
            'getHotelCoupon',
            'deleteOrder',
            'getHotelById',
            'getAppealMessageReviewByOID',
            'deleteHotel',
            'checkedIn',
            'autoUpdateOrderStatus',
            'getOrderListOfCurrentHotel'
        ]),
        addHotel(record) {
            this.set_addHotelModalVisible(true)
        },
        updateHotel(record){//仿照addHotel样式
            this.set_currentHotelId(Number(record.id))
            this.set_updateHotelModalVisible(true)
        },
        addRoom(record) {
            this.set_currentHotelId(record.id)
            this.set_currentHotelId(Number(record.id))
            this.getHotelById()
            this.set_addRoomModalVisible(true)
        },
        showCoupon(record) {
            this.set_currentHotelId(record.id)
            this.set_couponVisible(true)
            this.getHotelCoupon()
        },
        deleteThisHotel(record){
            this.set_currentHotelId(Number(record.id))
            this.deleteHotel()
        },
        checkAppeal(record){
            this.set_appealReviewOrderId(Number(record.id))
            //设置信用充值的准备工作
            this.set_appealCreditRechargeimfo({
                id:record.userId,
                amount:record.price / 5
                //扣除的信用值是订单价格的1/5
            })
            this.getAppealMessageReviewByOID()
            this.set_appealReviewModalVisible(true)
        },
        comfirmDeleteOrder(orderId){
            this.deleteOrder(orderId)
        },
        checkedInBtn(record){
            this.checkedIn(record)
        },
        showOrderDetail(record){
            this.set_orderDetailCardVisibleHM(!this.orderDetailCardVisibleHM)
            this.set_currentOrderOfHM(record)
        },
    }
}
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
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
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    #htl {
        background: #f8f7f7 url('../../assets/background.svg') repeat 100%;
    }
</style>