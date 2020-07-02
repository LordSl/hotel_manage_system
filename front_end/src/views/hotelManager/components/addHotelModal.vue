<template>
    <a-modal
        :visible="addHotelModalVisible"
        title="添加酒店"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="酒店名">
                <a-input
                    placeholder="请填写酒店名称"
                    v-decorator="['hotelName', { rules: [{ required: true, message: '请填写酒店名称' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店地址" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写酒店地址"
                    v-decorator="['address', { rules: [{ required: true, message: '请填写酒店地址' }] }]"
                />
            </a-form-item>
            <a-form-item label="商圈" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写酒店所在商圈"
                        v-decorator="['bizRegion', { rules: [{ required: true, message: '请填写酒店所在商圈' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店星级" v-bind="formItemLayout">
                <a-select
                    v-decorator="[
                    'hotelStar', 
                    { rules: [{ required: true, message: '请选择酒店星级' }] }]"
                    @change="changeStar"
                >
                    <a-select-option value="1">一星级</a-select-option>
                    <a-select-option value="2">二星级</a-select-option>
                  <a-select-option value="3">三星级</a-select-option>
                  <a-select-option value="4">四星级</a-select-option>
                  <a-select-option value="5">五星级</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="酒店评分" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写酒店评分"
                        v-decorator="['score', { rules: [{ required: true, message: '请输入酒店评分' }] }]"
                />
            </a-form-item>
            <a-form-item label="手机号" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写手机号"
                    v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店简介" v-bind="formItemLayout">
                <a-input
                    type="textarea"
                    :rows="4"
                    placeholder="请填写酒店简介"
                    v-decorator="['description', { rules: [{ required: true, message: '请填写酒店简介' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店地址经度" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写酒店地址经度"
                        v-decorator="['longitude', { rules: [{ required: true, message: '请填写酒店地址经度' }] }]"
                />
            </a-form-item>
            <a-form-item label="酒店地址纬度" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写酒店地址经度"
                        v-decorator="['latitude', { rules: [{ required: true, message: '请填写酒店地址经度' }] }]"
                />
            </a-form-item>
            <a-form-item label="管理员账号邮箱" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写管理员账号邮箱"
                        v-decorator="['email', { rules: [{ required: true, message: '请填写管理员账号邮箱' }] }]"
                />
            </a-form-item>
            <a-form-item label="上传酒店图片" v-bind="formItemLayout">
            <a-upload
                    name="file"
                    :multiple="true"
                    :customRequest="customRequest"
                    :showUploadList = false
            >
                <a-button type="primary"  icon="cloud-upload" >
                    酒店图片上传
                </a-button>
            </a-upload>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addHotelModal',
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
        }
    },
    computed: {
        ...mapGetters([
            'userId',
            'addHotelParams',
            'addHotelModalVisible'
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addHotelModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addHotelParams',
            'set_addHotelModalVisible'
        ]),
        ...mapActions([
            'addHotel',
            'uploadHotelImg'

        ]),
        cancel() {
            this.set_addHotelModalVisible(false)
        },
        changeStar(v){

        },
        customRequest (data) {
            const formData = new FormData()
            formData.append('file', data.file)
            this.uploadHotelImg(formData)
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        name: this.form.getFieldValue('hotelName'),
                        description: this.form.getFieldValue('description'),
                        bizRegion:this.form.getFieldValue('bizRegion'),
                        address: this.form.getFieldValue('address'),
                        phoneNum: this.form.getFieldValue('phoneNumber'),
                        hotelStar: Number(this.form.getFieldValue('hotelStar')),
                        managerId: Number(this.userId),
                        longitude:Number(this.form.getFieldValue('longitude')),
                        latitude:Number(this.form.getFieldValue('latitude')),
                        rate:Number(this.form.getFieldValue('score')),
                        managerEmail:this.form.getFieldValue('email'),
                    }
                    console.log(data)
                    this.addHotel(data)
                }
            });
        },
    }
}
</script>