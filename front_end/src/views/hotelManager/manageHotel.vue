<template>
    <div class="manageHotel">
    <a-layout>
        <a-layout-content id="htl">
            <div class="manageHotel-HM" v-if = "userInfo.userType=='HotelManager'">
                <div class="hotel-info">
                    <a-card style="width: 240px;">
                        <img
                                alt="example"
                                :src="currentHotelInfo.hotelImg"
                                slot="cover"
                                style="height: 300px"
                                referrerPolicy="no-referrer"
                        />
                    </a-card>
                    <div class="info">
                        <div class="items">
                            <span class="label">酒店名称：</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items">
                            <span class="label">地址</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items">
                            <span class="label">评分:</span>
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items">
                            <span class="label">星级:</span>
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </div>
                        <div class="items">
                            <span class="label">酒店简介:</span>
                            <span class="value">{{ currentHotelInfo.description }}</span>
                        </div>
                        <div style="width: 100%; text-align: right; margin:20px 0">
                            <a-upload
                                    name="file"
                                    :multiple="true"
                                    :customRequest="customRequest_hotelImg"
                                    :showUploadList = false
                            >
                                <a-button type="primary" >  <a-icon type="upload" /> 上传酒店封面 </a-button>
                            </a-upload>
                            <!--上传图片-->
                            <a-divider type="vertical"></a-divider>
                            <a-button  type="primary"  @click="updateHotelHM()"> <a-icon type="edit" /> 更新信息</a-button>
                            <a-divider type="vertical"></a-divider>
                            <a-button  type="primary" @click="showMAtlas"> <a-icon type="edit" />更新酒店图册</a-button>
                        </div>
                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <div style="width: 100%; text-align: right; margin:20px 0">
                            <a-button  type="primary"  @click="addRoomHM()"><a-icon type="plus" />录入房间</a-button>
                            <a-divider></a-divider>
                            <a-table
                                    :columns="columns4"
                                    :dataSource="currentHotelInfo.rooms"
                                    :pagination="true"
                            >
                                <img
                                        style="width:150px;height:100px"
                                        slot="roomImg"
                                        slot-scope = "text"
                                        :src="text"
                                        @click="handleBrief(text)"
                                />
                                <span slot="action" slot-scope="record">
                                    <a-upload
                                            name="file"
                                            :multiple="true"
                                            :customRequest="customRequest_roomImg"
                                            :showUploadList = false
                                    >
                                        <a-button type="primary" @click="chooseRoom(record)"><a-icon type="upload" />上传图片</a-button>
                                    </a-upload>
                                    <a-divider type="vertical"></a-divider>
                                    <a-button  type="primary" @click="updateRoomHM(record)"><a-icon type="edit" />更新房间</a-button>
                                    <a-divider type="vertical"></a-divider>
                                    <a-popconfirm title="确定想删除该房间吗？" @confirm="deleteRoomHM(record)" okText="确定" cancelText="取消">
                                        <a-button  type="danger"><a-icon type="delete" />删除房间</a-button>
                                    </a-popconfirm>
                                </span>

                            </a-table>
                        </div>
                    </a-tab-pane>
                    <a-tab-pane tab="优惠策略" key="2">
                        <div style="width: 100%; text-align: right; margin:20px 0">
                            <a-button  type="primary" @click="showCouponHM()"><a-icon type="plus" />添加策略</a-button>
                            <a-divider></a-divider>
                            <a-table
                                    :columns="columns3"
                                    :dataSource="couponList"
                                    :pagination="true"
                            >

            <span slot="couponType" slot-scope="text">
                <a-tag color="red">
                    <span v-if="text =='1'">节日特惠</span>
                <span v-if="text =='2'">多间特惠</span>
                <span v-if="text =='3'">满减优惠</span>
                <span v-if="text =='4'">限时优惠</span>
                </a-tag>

            </span>
                                <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="确定想删除该优惠策略吗？"
                                @confirm="confirmDeleteHotelCoupon(record.id)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除优惠策略</a-button>
                        </a-popconfirm>
                    </span>
                            </a-table>
                        </div>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>

    <div class="manageHotel-M" v-if = "userInfo.userType=='Manager'">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                <a-table :columns="columns1" :dataSource="hotelList" bordered
                         :pagination="true"
                >
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="addRoom(record)">录入房间</a-button>
                        <a-divider type="vertical"></a-divider>

                        <a-button type="primary" size="small" @click="showCoupon(record)">优惠策略</a-button>
                        <a-divider type="vertical"></a-divider>

                        <a-button type="info" size="small" @click="updateHotel(record)">更新信息</a-button>
                        <a-divider type="vertical"></a-divider>

                        <a-popconfirm title="确定想删除该酒店吗？" @confirm="deleteThisHotel(record)" okText="确定" cancelText="取消">
                            <a-button type="danger" size="small">删除酒店</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>


        </a-tabs>
    </div>
        <AddHotelModal></AddHotelModal>
        <UpdateHotelModal></UpdateHotelModal>
        <UpdateRoomModal></UpdateRoomModal>
        <AddRoomModal></AddRoomModal>
        <AddCouponModal></AddCouponModal>
        <Coupon></Coupon>
        <MAtlas></MAtlas>
        <appeal-review-modal></appeal-review-modal>
        <a-modal :visible="briefVisible" :footer="null" @cancel="handleCancel">
            <img alt="example" style="height: 100%;width: 100%" :src="roomUrl" />
        </a-modal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddHotelModal from './components/addHotelModal'
import UpdateHotelModal from './components/updateHotelModal'
import UpdateRoomModal from './components/updateRoomModal'
import AddRoomModal from './components/addRoomModal'
import AddCouponModal from './components/addCoupon'
import Coupon from './components/coupon'
import appealReviewModal from './components/appealReviewModal'
import MAtlas from "./components/MAtlas";
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
    },
    {
        title: '状态',
        filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' }],
        onFilter: (value, record) => record.orderState.includes(value),
        scopedSlots: { customRender: 'orderState' }
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
const columns3 = [
    {
        title: '优惠类型',
        dataIndex: 'couponType',
        scopedSlots: { customRender:'couponType'},
    },
    {
        title: '优惠卷名',
        dataIndex: 'couponName',
    },
    {
        title: '折扣',
        dataIndex: 'discount',
    },
    {
        title: '优惠简介',
        dataIndex: 'description',
    },
    {
        title: '达标金额',
        dataIndex: 'targetMoney',
    },
    {
        title: '优惠金额',
        dataIndex: 'discountMoney',
    },
    {
        title: '有效起始时间',
        dataIndex: 'startTime',
    },
    {
        title: '有效结束时间',
        dataIndex: 'endTime',
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    }
];
const columns4 = [
    {
      title: '图片',
      dataIndex: 'roomImg',
      key: 'roomImg',
      scopedSlots:{customRender:'roomImg'}
    },
    {
      title: '房型',
      dataIndex: 'roomType',
      key: 'roomType',
    },
    {
      title: '房间数量',
      key: 'curNum',
      dataIndex: 'curNum',
      scopedSlots:{customRender:'roomNum'}
    },
    {
      title: '房价',
      key: 'price',
      dataIndex: 'price',
      scopedSlots: { customRender: 'price'}
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
            columns3,
            columns4,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
            roomUrl:'',
            briefVisible:false

        }
    },
    components: {
        AddCouponModal,
        appealReviewModal,
        AddHotelModal,
        UpdateHotelModal,
        UpdateRoomModal,
        AddRoomModal,
        Coupon,
        MAtlas,
    },
    computed: {
        ...mapGetters([
            'userInfo',
            'orderList',
            'hotelList',
            'couponList',
            'addHotelModalVisible',
            'updateHotelModalVisible',
            'addRoomModalVisible',
            'updateRoomModalVisible',
            'couponVisible',
            'addCouponVisible',
            'currentHotelId',
            'currentHotelInfo',//加入currentHotelInfo用于显示剩余房间
            'webCoupon',
            'webCouponList',
            'atlasVisible'
        ]),
    },
    async mounted() {
        await this.getHotelList()
        if(this.userInfo.userType=='HotelManager'){
            await this.getHotelByHMId(this.userInfo.id)
            await this.getHotelCoupon()
            await this.getHotelById()

        }
        else if(this.userInfo.userType=='Manager'){
            await this.getHotelList()
            await this.getWebCouponList()
            await console.log(this.webCouponList)
        }
    },
    methods: {
        ...mapMutations([
            'set_appealReviewOrderId',
            'set_addHotelModalVisible',
            'set_updateHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_addCouponVisible',
            'set_currentHotelId',
            'set_appealReviewModalVisible',
            'set_appealCreditRechargeimfo',
            'set_addCouponVisible',
            'set_webCoupon',
            'set_currentRoomId',
            'set_updateRoomModalVisible',
            'set_atlasVisible'
        ]),
        ...mapActions([
            'getHotelByHMId',
            'getHotelList',
            'getHotelCoupon',
            'deleteOrder',
            'getHotelById',
            'getAppealMessageReviewByOID',
            'deleteHotel',
            'getWebCouponList',
            'deleteCoupon',
            'updateHotelImg',
            'updateRoomImg',
            'deleteRoom',
            'deleteHotelCoupon',
            'getUserInfo'
        ]),
        handleBrief(text){
            this.roomUrl=text
            this.briefVisible=true
        },
        handleCancel(){
            this.briefVisible=false
        },
        customRequest_hotelImg(data) {//上传酒店图片
            const formData = new FormData()
            formData.append('file', data.file)
            this.updateHotelImg(formData)
        },
        customRequest_roomImg(data) {//上传房间图片
            const formData = new FormData()
            formData.append('file', data.file)
            this.updateRoomImg(formData)
        },
        chooseRoom(record){
            this.set_currentRoomId(Number(record.id))
        },
        addHotel(record) {
            this.set_addHotelModalVisible(true)
        },
        updateHotel(record){//仿照addHotel样式
            this.set_currentHotelId(Number(record.id))
            this.set_updateHotelModalVisible(true)
        },
        updateHotelHM(){
            console.log("1")
            this.set_updateHotelModalVisible(true)
        },
        addRoom(record) {
            this.set_currentHotelId(record.id)
            this.set_currentHotelId(Number(record.id))
            this.getHotelById()
            this.set_addRoomModalVisible(true)
        },
        addRoomHM() {
            this.set_addRoomModalVisible(true)
        },
        updateRoomHM(record){
            this.set_currentRoomId(record.id)
            this.set_updateRoomModalVisible(true)
        },
        deleteRoomHM(record){
            console.log("del")
            this.set_currentRoomId(record.id)
            this.deleteRoom()
        },
        showCoupon(record) {
            this.set_currentHotelId(record.id)
            this.set_couponVisible(true)
            this.getHotelCoupon()
        },
        showCouponHM() {
            this.set_addCouponVisible(true)
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
        addCoupon() {
            this.set_addCouponVisible(true)
            this.set_couponVisible(false)
            this.set_webCoupon(false)
        },
        confirmDeleteCoupon(id){
            const data={
                id:Number(id)
            }
            this.deleteCoupon(data)
        },
        confirmDeleteHotelCoupon(id){
            const data={
                id:Number(id)
            }
            this.deleteHotelCoupon(data)
        },
        showMAtlas(){
            this.set_atlasVisible(true)
        }
    }
}
</script>
<style lang="less">
    .manageHotel-M {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style scoped lang="less">
    .manageHotel {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
    .manageHotel-HM {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 40px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }

    #htl {
        background: #f8f7f7 url('../../assets/background.svg') repeat 100%;
    }
</style>