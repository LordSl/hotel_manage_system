<template>
    <div class="manageHotelVIPWapper">
        <a-tabs>
            <a-tab-pane tab="酒店会员管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                </div>
                <a-table
                        style="font-weight: bold"
                        :columns="columns"
                        :dataSource="vipListOfCurrentHotel"
                        bordered
                >
                    <span slot="avatarUrl" slot-scope="text">
                        <img :src="text" style="width: 50px;height:50px">
                    </span>

                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" icon="bar-chart"  @click="showHistoryDetailOfTheHotel(record)" ghost >查看该用户下单记录</a-button>
                        <a-divider></a-divider>
                        <a-popconfirm
                                title="确定想取消该用户会员资格吗？"
                                @confirm="deleteVipHotelBtn(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small" icon="delete"  ghost >取消该用户会员资格</a-button>
                    </a-popconfirm>

                    </span>

                </a-table>
            </a-tab-pane>
        </a-tabs>
    <history-order-modal></history-order-modal>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";
    import historyOrderModal from "../user/components/historyOrderModal";

    const columns = [
        {
            title: '会员头像',
            dataIndex: 'avatarUrl',
            scopedSlots: { customRender: 'avatarUrl' },
        },
        {
            title: '会员称呼',
            dataIndex: 'userName',
        },
        {
            title: '会员邮箱',
            dataIndex: 'email',
        },
        {
            title: '会员手机号',
            dataIndex: 'phoneNumber',
        },
        {
            title: '信用值',
            dataIndex: 'credit',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    export default {
        name: "manageHotelVIP",
        computed: {
            ...mapGetters([
                "vipListOfCurrentHotel",
                'orderListOfCurrentHotel',
                'userId',
                'currentHotelId'
            ])
        },
        components:{
            historyOrderModal
        },
        methods: {
            ...mapMutations([
                'set_historyOrderModalVisible',
                'set_historyOrderList'
            ]),
            ...mapActions([
                'getVipListOfCurrentHotel',
                'autoUpdateOrderStatus',
                'getOrderListOfCurrentHotel',
                'getHotelList',
                'getHotelByHMId',
                'deleteVIPHotel'
            ]),
            showHistoryDetailOfTheHotel(record){
                console.log(this.orderListOfCurrentHotel)
                let specificUserId = record.id
                let newList = []
                for(let i = 0; i < this.orderListOfCurrentHotel.length; i++){
                    if(this.orderListOfCurrentHotel[i].userId === specificUserId){
                        newList.push(this.orderListOfCurrentHotel[i])
                    }
                }
                this.set_historyOrderList(newList)
                this.set_historyOrderModalVisible(true)

            },
            deleteVipHotelBtn(record){
                this.deleteVIPHotel({
                    userId:record.id,
                    hotelId:this.currentHotelId
                })
            }
        },
        async mounted() {
            await this.getHotelByHMId(this.userId)//不能删
            await this.getVipListOfCurrentHotel()
            await this.autoUpdateOrderStatus()
            await this.getOrderListOfCurrentHotel()
        },
        data(){
            return{
                columns
            }
        },
    }
</script>

<style scoped lang="less">
    .manageHotelVIPWapper {
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
    .manageHotelVIPWapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>