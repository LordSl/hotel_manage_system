<template>
<div class="room-list">
    <div class="filter" style="margin-bottom: 20px">
        <a-range-picker
                format="YYYY-MM-DD"
                v-model='date'
                :placeholder="['入住日期','退房日期']"
                :allowClear="false"
        />

        <a-button type="primary" ghost @click="getRoom" style="margin-left: 30px">查看</a-button>
        </div>
        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="rooms"
                :pagination="true"
            >
                    <img
                            style="width:150px;height:100px;"
                            slot="roomImg"
                            slot-scope = "text"
                            :src="text"
                            @click="handleBrief(text)"
                    />

                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>

<!--                显示酒店剩余数量-->
                <span slot="roomNum" slot-scope="text">
                    <span>{{text}}</span>
                </span>
<!--                显示酒店剩余数量-->

<!--                <span slot="action" slot-scope="text, record">-->
                    <span slot="action" slot-scope="record">
                    <a-button type="primary" @click="order(record)">预定</a-button>
                </span>
            </a-table>
        </div>
        <OrderModal></OrderModal>
    <a-modal :visible="briefVisible" :footer="null" @cancel="handleCancel">
        <img alt="example" style="height: 100%;width: 100%" :src="roomUrl" />
    </a-modal>
</div>
</template>
<script>
    import moment from 'moment';
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
const columns = [
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
      title: 'Action',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
];
export default {
    name:'roomList',
    props: {
        rooms: {
            type: Array
        }
    },
    data() {
        return {
            columns,
            briefVisible:false,
            roomUrl:'',
            date:null
        }
    },
    components: {
        OrderModal
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'currentHotelInfo'
        ])
    },
    mounted() {
        // checkInDate: moment().format('YYYY-MM-DD'),
        //     checkOutDate: moment().add(1,'day').format('YYYY-MM-DD'),
        this.date=[moment(), moment().add(1,'day')]
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom'
        ]),
        ...mapActions([
            'getHotelRoom'
        ]),
        order(record) {
            this.set_currentOrderRoom(record)
            this.set_orderModalVisible(true)
        },
        handleBrief(text){
            this.roomUrl=text
            this.briefVisible=true
        },
        handleCancel(){
            this.briefVisible=false
        },
        getRoom(){
            const params={
                hotelId:Number(this.currentHotelInfo.id),
                checkInDate: moment(this.date[0]).format('YYYY-MM-DD'),
                checkOutDate: moment(this.date[1]).format('YYYY-MM-DD'),
            }
            this.getHotelRoom(params)
        }
    },
    watch: {
        orderModalVisible(val){
            if(val==false){
                this.getRoom()
            }
        }
    }
}
</script>
