<template>
    <div class="manageWebCoupon-wrapper">
        <a-tabs>
         <a-tab-pane tab="网站优惠策略管理" key="1">
            <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button v-if = "userInfo.userType=='Manager'"  type="primary" @click="addCoupon"><a-icon type="plus" />网站优惠策略</a-button>
            </div>

            <a-table
                    :columns="columns"
                    :dataSource="webCouponList"
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

        </a-tab-pane>
        </a-tabs>
        <AddCouponModal></AddCouponModal>

    </div>

</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";
    import AddCouponModal from "../hotelManager/components/addCoupon";
    const columns = [
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
        name: "manageWebCoupon",
        data(){
            return {
                columns,
                form: this.$form.createForm(this, { name: 'manageWebCoupon' }),
            }
        },
        components: {
            AddCouponModal
        },
        computed: {
            ...mapGetters([
                'userInfo',
                'addCouponVisible',
                'webCoupon',
                'webCouponList',
            ]),
        },
        async mounted() {
                await this.getWebCouponList()
                console.log(this.webCouponList)

        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible',
                'set_webCoupon'
            ]),
            ...mapActions([
                'getWebCouponList',
                'deleteCoupon'
            ]),
            addCoupon() {
                this.set_addCouponVisible(true)
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
        }
    }
</script>

<style scoped>
    .manageWebCoupon-wrapper {
        padding: 50px;
    }
    .chart {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-top: 20px
    }



    .ant-tabs-bar {
        padding-left: 30px;
    }


</style>