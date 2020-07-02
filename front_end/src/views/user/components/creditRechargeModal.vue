<template>
    <a-modal
            :visible="creditRechargeModalVisible"
            title="信用充值"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="充值额度" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写充值额度"
                        v-decorator="['amount', { rules: [{ required: true, message: '请输入原始价格' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'creditRechargeModal',
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
                'creditRechargeModalVisible'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'creditRechargeModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_creditRechargeModalVisible',
            ]),
            ...mapActions([
                'creditRecharge'
            ]),
            cancel() {
                this.set_creditRechargeModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        let data = {
                            amount: Number(this.form.getFieldValue('amount')),
                        }
                        this.creditRecharge(data)
                    }
                });
            },
        }
    }
</script>