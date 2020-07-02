<template>
    <a-modal
            :visible="updateManagerModalVisible"
            title="更改用户信息"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户名">
                <a-input
                        v-decorator="[
                        'name',
                        { rules: [{required: true, message: '请输入用户名', }],
                        }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                        v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="用户手机号">
                <a-input
                        v-decorator="[
                        'phoneNum',
                        { rules: [{required: true, message: '请输入手机号', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="信用值">
                <a-input
                        v-decorator="[
                        'credit',
                        { rules: [{required: true, message: '请输入信用值', }] }
                    ]"
                />
            </a-form-item>
        </a-Form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateManagerModal',
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
                'updateManagerModalVisible',
                'managerList',
                'currentUserId',
                'currentUserParams'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateManagerModal' });
        },
        mounted() {

            console.log(this.currentUserParams)
        },
        methods: {
            ...mapMutations([
                'set_updateManagerModalVisible',
            ]),
            ...mapActions([
                'updateManager',
            ]),
            cancel() {
                this.set_updateManagerModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            id:Number(this.currentUserId),
                            userName: this.form.getFieldValue('name'),
                            password: this.form.getFieldValue('password'),
                            phoneNumber: this.form.getFieldValue('phoneNum'),
                            credit: this.form.getFieldValue('credit'),
                        }
                        console.log(data)
                        this.updateManager(data)
                        }
                });
            },
        },
        watch:{
            updateManagerModalVisible(val){
                if(val==true){
                    this.$nextTick(()=>{
                    this.form.setFieldsValue({
                        "name":this.currentUserParams.userName,
                        "password":this.currentUserParams.password,
                        "phoneNum":this.currentUserParams.phoneNumber,
                        "credit":this.currentUserParams.credit
                    })
                    })
                }
            }
        }
    }
</script>