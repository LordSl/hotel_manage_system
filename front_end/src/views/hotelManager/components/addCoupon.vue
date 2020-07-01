<template>
    <a-modal
        :visible="addCouponVisible"
        title="添加优惠策略"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'couponType',
                    { rules: [{ required: true, message: '请选择类型' }] }]"
                        @change="changeType"
                >
                    <a-select-option value="2">多间特惠</a-select-option>
                    <a-select-option value="3">满减特惠</a-select-option>
                    <a-select-option value="4">限时特惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名">
                <a-input
                        placeholder="券名"
                        v-decorator="['couponName', { rules: [{ required: true, message: '券名' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠简介"
                        v-decorator="['description', { rules: [{ required: true, message: '请填写酒店简介' }] }]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="适用时间">
                <a-range-picker
                        :disabled-date="disabledDate"
                        show-time
                        format="YYYY-MM-DD HH:mm:ss"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择适用时间' }]
                        }
                    ]"
                        :placeholder="['开始时间','结束时间']"
                />
            </a-form-item>

            <a-form-item label="达标房间数" v-bind="formItemLayout" v-if="targetRoom==true">
                <a-input
                        placeholder="请填写达标房间数"
                        v-decorator="['targetRoomNum', { rules: [{ required: true, message: '请填写达标房间数' }] }]"
                />
            </a-form-item>
            <a-form-item label="折扣" v-bind="formItemLayout" v-if="targetMoney==false">
                <a-input
                        placeholder="请填写折扣"
                        v-decorator="['discount', { rules: [{ required: true, message: '折扣' }] }]"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney', { rules: [{ required: true, message: '请填写达标金额' }] }]"
                />
            </a-form-item>

            <a-form-item label="优惠金额" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] }]"
                />
            </a-form-item>

            <a-form-item label="仅面向会员" v-bind="formItemLayout">
                <a-switch  @click="vipOrientedChange" v-model="targetVIP">
                    <a-icon slot="checkedChildren" type="check" />
                    <a-icon slot="unCheckedChildren" type="close" />
                </a-switch>
            </a-form-item>

        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
const moment = require('moment')
export default {
    name: 'addCouponModal',
    data() {
        return {
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
            targetMoney: true,
            targetTime: false,
            targetRoom: false,
            targetVIP : false
        }
    },
    computed: {
        ...mapGetters([
            'currentHotelId',
            'addCouponVisible',
            'webCoupon',
            'userInfo'
        ])
    },
    beforeCreate() {
        // 表单名默认为“form”
        this.form = this.$form.createForm(this, { name: 'addCouponModal' });
    },
    mounted() {
    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible',
        ]),
        ...mapActions([
            // addHotelCoupon：添加酒店策略接口
            'addHotelTargetMoneyCoupon',
            'addHotelTargetRoomCoupon',
            'addHotelTargetTimeCoupon',
        ]),
        cancel() {
            this.set_addCouponVisible(false)
            this.targetVIP = false
        },
        changeType(v){
            if( v == '3') {
                this.targetMoney=true
                this.targetRoom=false
                this.targetTime=false

            }else if (v=='2'){
                this.targetMoney=false
                this.targetRoom=true
                this.targetTime=false
            }else if(v=='4'){
                this.targetMoney=false
                this.targetRoom=false
                this.targetTime=true
            }
        },
        disabledDate(current) {
            // Can not select days before today and today
            return current && current < moment().startOf('day');
        },
        vipOrientedChange(){
            this.targetVIP = true; //this.targetVIP = ! this.targetVIP无效,只能点一次
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {

                    let data = {
                        // 这里添加接口参数
                        hotelId: (this.userInfo.userType==='Manager')? 0 :Number(this.currentHotelId),
                        type: this.form.getFieldValue('couponType'),
                        description: this.form.getFieldValue('description'),
                        name: (this.targetVIP === true)? (this.userInfo.userType === 'Manager' ? '网站会员专属' : '酒店会员专属')  :  (this.userInfo.userType === 'Manager'?"全网站用户共享优惠":this.form.getFieldValue('couponName')),
                        targetMoney: Number(this.form.getFieldValue('targetMoney')),
                        discountMoney: Number(this.form.getFieldValue('discountMoney')),
                        startTime: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD hh:mm:ss'),
                        endTime: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD hh:mm:ss'),
                        forHotelVip: (this.targetVIP === true)? 1 : 0
                    }
                    if(this.webCoupon){
                        data={
                            hotelId: Number(0),
                            ...data
                        }
                    }
                    console.log(data)
                    if(this.targetRoom){
                        const data1={
                            targetRoom:Number(this.form.getFieldValue('targetRoomNum')),
                            discount:Number(this.form.getFieldValue('discount')),
                            ...data
                        }
                        console.log(data1)
                        this.addHotelTargetRoomCoupon(data1).then(() => {
                            this.form.setFieldsValue({
                                'couponType': '',
                                'description': '',
                                'couponName': '',
                                'targetMoney':'',
                                'date':'',
                                'targetRoomNum':'',
                                'discount':'',
                                'discountMoney':'',
                            })
                        });
                    }
                    if(this.targetTime){
                        const data2={
                            discount:Number(this.form.getFieldValue('discount')),
                            ...data
                        }
                        this.addHotelTargetTimeCoupon(data2).then(() => {
                            this.form.setFieldsValue({
                                'couponType': '',
                                'description': '',
                                'couponName': '',
                                'targetMoney':'',
                                'date':'',
                                'targetRoomNum':'',
                                'discount':'',
                                'discountMoney':'',
                            })
                        });
                    }
                    if(this.targetMoney){
                        this.addHotelTargetMoneyCoupon(data).then(() => {
                            this.form.setFieldsValue({
                                'couponType': '',
                                'description': '',
                                'couponName': '',
                                'targetMoney':'',
                                'date':'',
                                'targetRoomNum':'',
                                'discount':'',
                                'discountMoney':'',
                            })
                        });
                    }
                    this.targetMoney=true
                    this.targetRoom=false
                    this.targetTime=false
                    this.targetVIP = false
                }
            });
        },
    }
}
</script>