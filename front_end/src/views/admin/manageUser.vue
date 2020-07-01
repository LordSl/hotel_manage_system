<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="账户管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加用户</a-button>
                </div>
                <a-table
                    :columns="columns"
                    :dataSource="managerList"
                    bordered
                    :pagination="true"
                >
                    <span slot="userType" slot-scope="userType" v-if="userType=='Client'">客户</span>
                    <span slot="userType" slot-scope="userType" v-else-if="userType=='HotelManager'">酒店管理员</span>
                    <span slot="userType" slot-scope="userType" v-else-if="userType=='Manager'">网站管理员</span>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" @click="updateManager(record)">更改信息</a-button>
                        <a-divider type="vertical"></a-divider>
                          <a-popconfirm
                                  title="确定想删除该用户吗？"
                                  @confirm="deleteThisManager(record)"
                                  okText="确定"
                                  cancelText="取消"
                          >
                          <a-button type="danger">删除用户</a-button>
                          </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>



            <a-tab-pane tab="网站会员管理" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="checkWebVIPCouponBtn" icon="search">查看网站会员优惠策略</a-button>
                </div>
                <a-table
                        :columns="columns1"
                        :dataSource="webVIPList"
                        bordered
                        :pagination="true"
                >
                    <span slot="action" slot-scope="record" >
                        <a-button type="primary" icon="database" ghost @click="checkHistoryOrder(record)">查看历史订单</a-button>
                        <a-divider type="vertical"></a-divider>
                          <a-popconfirm
                                  title="确定想取消该用户会员资格吗？"
                                  @confirm="deleteThisVIP(record)"
                                  okText="确定"
                                  cancelText="取消"
                          >
                          <a-button type="danger" icon="delete" ghost >取消会员资格</a-button>
                          </a-popconfirm>


                    </span>
                </a-table>


            </a-tab-pane>
        </a-tabs>
        <history-order-modal></history-order-modal>
        <order-detail-card></order-detail-card>
        <AddManagerModal></AddManagerModal>
        <UpdateManagerModal></UpdateManagerModal>
        <check-web-v-i-p-coupon-modal></check-web-v-i-p-coupon-modal>

    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import checkWebVIPCouponModal from "./components/checkWebVIPCouponModal";
import UpdateManagerModal from './components/updateManagerModal'
import historyOrderModal from "../user/components/historyOrderModal";
import orderDetailCard from "../user/components/orderDetailCard";
const columns = [
    {
        title: '用户类型',
        dataIndex: 'userType',
        scopedSlots: { customRender: 'userType' },
    },
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
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

const columns1 = [
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
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
    name: 'manageUser',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            columns1,
            data: [],
            form: this.$form.createForm(this, { name: 'manageUser' }),
        }
    },
    components: {
        orderDetailCard,
        historyOrderModal,
        checkWebVIPCouponModal,
        AddManagerModal,
        UpdateManagerModal
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'updateManagerModalVisible',
            'userInfo',
            'managerList',  //实际上是该类型用户对应的可操作列表
            'webVIPList'
        ])
    },
    mounted() {
        this.getManagerList()
        this.getWebVip()
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'deleteManager',
            'getWebVip',
            'deleteWebVIP',
            'getWebVIPCoupon',
            'getVIPOrders'
        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_updateManagerModalVisible',
            'set_currentUserId',
            'set_currentUserParams',
            'set_currentManagerId',
            'set_webVIPcouponVisible',
            'set_historyOrderModalVisible'
        ]),
        checkHistoryOrder(record){
            let specificUserId = record.id
            this.getVIPOrders(specificUserId)
            this.set_historyOrderModalVisible(true)
            console.log(record)
        },
        addManager(){
            this.set_addManagerModalVisible(true)
        },
        checkWebVIPCouponBtn(){
           this.set_webVIPcouponVisible(true)
            this.getWebVIPCoupon()
        },
        updateManager(record){
            const params={
                userName:record.userName,
                password:record.password,
                phoneNumber:record.phoneNumber,
                credit:record.credit,
            }
            console.log(params)
            this.set_currentUserParams(params)
            this.set_currentUserId(Number(record.id))
            this.set_updateManagerModalVisible(true)
        },
        deleteThisManager(record){
            const data={
                userId:Number(record.id),
            }
            this.deleteManager(data)
        },
        deleteThisVIP(record){
            console.log(record)
            const data={
                userId : record.id,
                isWebVIP : 0
            }
            this.deleteWebVIP(data)
        }
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
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
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>