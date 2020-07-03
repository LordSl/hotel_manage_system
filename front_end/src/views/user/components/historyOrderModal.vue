<template>
    <div>
        <a-modal
                :visible="historyOrderModalVisible"
                title="优惠策略"
                width="900px"
                :footer="null"
                @cancel="cancel"
        >
            <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->
            <a-table
                    :columns="columns"
                    :dataSource="historyOrderList"
                    bordered
            >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                <span slot="roomType" slot-scope="text">
                        <span >{{text}}</span>
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
                        <a-button type="primary" icon="search" size="small" ghost @click="showOrderDetail(record)">查看</a-button>




                    </span>
            </a-table>
            <order-detail-card></order-detail-card>
        </a-modal>

    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import orderDetailCard from "./orderDetailCard";
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
    export default {
        name: 'historyOrderMoal',
        data() {
            return {
                columns
            }
        },
        components: {
            orderDetailCard
        },
        computed: {
            ...mapGetters([
                'historyOrderModalVisible',
                'historyOrderList',
                'orderDetailCardVisible'
            ])
        },
        methods: {
            ...mapMutations([
                'set_historyOrderModalVisible',
                'set_orderDetailCardVisible',
                'set_currentOrderOfUser'
            ]),
            ...mapActions([
            ]),
            cancel() {
                this.set_historyOrderModalVisible(false)
            },
            showOrderDetail(record){
                console.log(this.orderDetailCardVisible)
                this.set_orderDetailCardVisible(!this.orderDetailCardVisible)
                this.set_currentOrderOfUser(record)
            },
        },
    }
</script>
<style scoped>

</style>