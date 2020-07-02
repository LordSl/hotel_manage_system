<template>
    <a-modal
            :visible="bindADHotelModalVisible"
            width="1000px"
            title="绑定酒店"
            :footer="null"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
    >
        <a-Form :form="form">

            <a-form-item v-bind="formItemLayout" label="酒店名称" style="float: left">
                <a-input
                        pressEnter="searchHotelListByName"
                        style="width: 300px"
                        placeholder="请输入该广告要绑定的酒店名称"
                        v-decorator="[
                        'hotelName',
                        { rules: [{required: true, message: '请先输入该广告要绑定的酒店名称', }] }
                    ]"
                />
            </a-form-item >
            <a-button type="primary" ghost icon="search"  style="left: 40px;top:4px;" @click="searchHotelListByName" >搜索</a-button>
        </a-Form>

        <a-table :columns="columns1" :dataSource="matchedHotelList" bordered style="clear: left"
                 :pagination="true"
        >

                    <span slot="action" slot-scope="record">
                        <!--酒店工作人员可以 录入房间、更新信息、优惠策略-->
                        <!--网站工作人员可以 更新信息、优惠策略、删除酒店-->
                        <a-button  type="primary" icon="tool" ghost  @click="bindADHotelBtn(record.id)" >绑定</a-button>
                    </span>

        </a-table>

    </a-modal>
</template>
<script>
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
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateManagerModal',
        data() {
            return {
                columns1,
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'bindADHotelModalVisible',
                'matchedHotelList'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'bindADHotelModal' });
        },
        mounted() {
        },
        methods: {
            ...mapMutations([
                'set_bindADHotelVisible',
            ]),
            ...mapActions([
                'getHotelListByName',
                'bindADHotel'
            ]),
            cancel() {
                this.set_bindADHotelVisible(false)
            },

            searchHotelListByName(){
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        this.getHotelListByName(this.form.getFieldValue('hotelName'))
                    }
                });
            },
            bindADHotelBtn(hotelId){
                this.bindADHotel(hotelId)
                this.form.setFieldsValue({'hotelName':''})
            }
        }
    }
</script>