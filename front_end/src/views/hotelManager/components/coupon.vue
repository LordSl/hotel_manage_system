<template>
   <div>
    <a-modal
        :visible="couponVisible"
        title="优惠策略"
        width="1200px"
        :footer="null"
        @cancel="cancel"
    >
        <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->
        <a-table
                :columns="columns"
                :dataSource="couponList"
                bordered
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
                                @confirm="confirmDeleteCoupon(record.id)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除优惠策略</a-button>
                        </a-popconfirm>
                    </span>
        </a-table>
    </a-modal>
    <AddCoupon></AddCoupon>
   </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddCoupon from './addCoupon'

const columns = [
    // 这里定义列表头
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
export default {
    name: 'coupon',
    data() {
        return {
            columns
        }
    },
    components: {
        AddCoupon,
    },
    computed: {
        ...mapGetters([
            'couponVisible',
            'couponList',
        ])
    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible',
            'set_couponVisible',
        ]),
        ...mapActions([
            'getHotelCoupon',
            'deleteHotelCoupon'
        ]),
        cancel() {
            this.set_couponVisible(false)
        },
        addCoupon() {
            this.set_addCouponVisible(true),
            this.set_couponVisible(false)
        },
        confirmDeleteCoupon(id){
            const data={
                id:Number(id)
            }
            this.deleteHotelCoupon(data)
        },
    },
}
</script>
<style scoped>

</style>