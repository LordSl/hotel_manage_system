<template>
<div class="room-list">
    <div class="filter">

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

        }
    },
    components: {
        OrderModal
    },
    computed: {
        ...mapGetters([
            'orderModalVisible'
        ])
    },
    monuted() {

    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom'
        ]),
        ...mapActions([

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
        }
    }
}
</script>