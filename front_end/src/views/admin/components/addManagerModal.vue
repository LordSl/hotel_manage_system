<template>
    <a-modal
        :visible="addManagerModalVisible"
        title="添加用户"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
    <a-Form :form="form">
        <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                    v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入用户邮箱', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                    v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
                />
            </a-form-item >
          <a-form-item v-if = "userInfo.userType=='Manager'" v-bind="formItemLayout" label="用户类型">
            <a-select
                v-decorator="[
                    'userType',
                    { rules: [{ required: true, message: '请选择要添加的用户类型' }] }]"
                @change="changeUserType"
            >
                <a-select-option value="Client">客户</a-select-option>
                <a-select-option value="HotelManager">酒店管理人员</a-select-option>
                <a-select-option value="Manager">网站管理人员</a-select-option>
            </a-select>
          </a-form-item>
    </a-Form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addManagerModal',
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
            'userInfo',
            'addManagerModalVisible',
            'managerList',
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addManagerModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_addManagerParams',
        ]),
        ...mapActions([
            'addManager',
        ]),
        cancel() {
            this.set_addManagerModalVisible(false)
        },
        changeUserType(v){

        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        email: this.form.getFieldValue('email'),
                        password: this.form.getFieldValue('password'),
                        userType: this.form.getFieldValue('userType'),
                    }
                    this.set_addManagerParams(data)
                    }
                    this.addManager()
            });
        },
    }
}
</script>