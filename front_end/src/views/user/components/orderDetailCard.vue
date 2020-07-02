<template>
    <a-modal
            :visible="orderDetailCardVisible"
            title="订单详情"
            width="700px"
            @cancel="closeDetail"
            style="top: 20px;"
            :footer="null"
    >
        <!--        详情STEP 已撤销情况-->
        <a-steps
                :current="1"
                v-if="currentOrderOfUser.orderState === '已撤销'"
        >
            <a-step  title="已下单" description="下单成功"/>
            <a-step title="已撤销" status="error" description="撤销成功 可申诉"/>
            <a-step title="已申诉" v-if="currentOrderOfUser.appeal != null" status="finish" description="申诉已提交" />
            <a-step title="申诉待审核" v-if="currentOrderOfUser.appeal != null&&currentOrderOfUser.appeal.appealStatus==='申诉待审核'" status="wait" description="等待管理员审核" />
            <a-step title="申诉失败"  status="error" v-if="currentOrderOfUser.appeal != null&&currentOrderOfUser.appeal.appealStatus==='审核未通过'"  description="审核未通过" >
                <a-icon slot="icon" type="frown" />
            </a-step>
            <a-step title="申诉成功"  status="finish" v-if="currentOrderOfUser.appeal != null&&currentOrderOfUser.appeal.appealStatus==='审核通过'"  description="审核已通过">
                <a-icon slot="icon" type="smile-o" />
            </a-step>

        </a-steps>
        <!--        详情STEP-->


        <!--        详情STEP 正常情况-->
        <a-steps
                :current="0"
                v-if="currentOrderOfUser.orderState === '已预订' || currentOrderOfUser.orderState === '已入住'"
        >
            <a-step  title="下单"  :description="currentOrderOfUser.createDate + '下单成功'" />
            <a-step title="入住" status="process" :description="'入住时间 : '+ currentOrderOfUser.checkInDate"/>
            <a-step title="退房"  status="process" :description="'退房时间 : ' + currentOrderOfUser.checkOutDate" />
        </a-steps>
        <!--        详情STEP-->


        <!--        详情卡片-->
        <a-card title="房间类型:" style="margin-top: 30px;">
            <!--          封面图片-->
            <img
                    style="width: 100%;height:250px;"
                    slot="cover"
                    :src="currentOrderOfUser.room.roomImg"
                    v-if="currentOrderOfUser.room!=undefined"
            />
            <!--            封面图片-->

            <!--            具体信息-->
            <a-row :gutter="1">
                <a-col :span="8">
                    <a-card title="客户姓名" :bordered="false">
                        <p>    {{currentOrderOfUser.clientName}}</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="酒店名称" :bordered="false">
                        <p>{{currentOrderOfUser.hotelName}}</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="订单编号" :bordered="false">
                        <p>{{currentOrderOfUser.id}}</p>
                    </a-card>
                </a-col>

            </a-row>
            <a-row :gutter="1">
                <a-col :span="8">
                    <a-card title="入住时间" :bordered="false">
                        <p>{{currentOrderOfUser.checkInDate}}</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="退房时间" :bordered="false">
                        <p>{{currentOrderOfUser.checkOutDate}}</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="下单时间" :bordered="false">
                        <p>{{currentOrderOfUser.createDate}}</p>
                    </a-card>
                </a-col>
            </a-row>

            <a-row :gutter="1">
                <a-col :span="8">
                    <a-card title="房间类型" :bordered="false">
                        <p>    {{currentOrderOfUser.roomType}}</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="单间价格" :bordered="false">
                        <p>{{currentOrderOfUser.room.price}} RMB</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="房间数量" :bordered="false">
                        <p>{{currentOrderOfUser.roomNum}}</p>
                    </a-card>
                </a-col>

            </a-row>


            <a-row :gutter="1">
                <a-col :span="8">
                    <a-card title="入住人数" :bordered="false">
                        <p>{{currentOrderOfUser.peopleNum}}人</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="订单价格" :bordered="false">
                        <p>{{currentOrderOfUser.price}} RMB</p>
                    </a-card>
                </a-col>
                <a-col :span="8">
                    <a-card title="订单状态" :bordered="false">
                        <p v-if="currentOrderOfUser.appeal!=null">{{currentOrderOfUser.orderState}} {{currentOrderOfUser.appeal.appealStatus}}</p>
                        <p v-else>{{currentOrderOfUser.orderState}} </p>
                    </a-card>
                </a-col>
            </a-row>




        </a-card>
        <!--            具体信息-->

    </a-modal>




</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "orderDetailCard",
        computed: {
            ...mapGetters([
                'orderDetailCardVisible',
                'currentOrderOfUser'
            ])
        },

        data(){
            return{
            }
        },
        beforeCreate() {

        },
        mounted() {
        },

        methods: {
            ...mapMutations([
                'set_orderDetailCardVisible'
            ]),
            ...mapActions([
            ]),
            closeDetail(){
                this.set_orderDetailCardVisible(false)
            },
        }
    }

</script>

<style>
</style>
