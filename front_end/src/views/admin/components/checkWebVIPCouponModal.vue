<template>
    <div>
        <a-modal
                :visible="webVIPcouponModalVisible"
                title="优惠策略"
                width="900px"
                :footer="null"
                @cancel="cancel"
        >
            <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->
            <a-table
                    :columns="columns2"
                    :dataSource="webVIPCouponList"
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

            </a-table>
        </a-modal>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'

    const columns2 = [
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
        }
    ];
    export default {
        name: 'webVIPCouponModal',
        data() {
            return {
                columns2
            }
        },
        components: {
        },
        computed: {
            ...mapGetters([
                'webVIPcouponModalVisible',
                'webVIPCouponList',
            ])
        },
        methods: {
            ...mapMutations([
                'set_webVIPcouponVisible'
            ]),
            ...mapActions([
            ]),
            cancel() {
                this.set_webVIPcouponVisible(false)
            },
        },
    }
</script>
<style scoped>

</style>