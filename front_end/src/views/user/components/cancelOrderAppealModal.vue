
<template>
    <a-modal
        :visible="cancelOrderAppealModalVisible"
        title="订单撤销原因申诉"
        cancelText="取消"
        okText="提交申诉"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form">
            <a-form-item label="申诉信息">
                <a-textarea
                        id = "inputarea"
                        allow-clear
                        :rows="5"
                        placeholder="填写订单撤销原因，若申诉成功，恢复本次撤销订单扣除的信用值"
                        v-decorator="['appealInfo', { rules: [{ required: true, message: '未填写' }] }]"
                />
            </a-form-item>
        </a-form>

    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "cancelOrderAppealModal",
        computed: {
            ...mapGetters([
                'userId',
                'cancelOrderAppealModalVisible'
            ])
        },
        data(){
            return{
            }
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'cancelOrderAppealModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_cancelOrderAppealModalVisible',
            ]),
            ...mapActions([
                'cancelAppeal'
            ]),
            cancel() {
                this.set_cancelOrderAppealModalVisible(false)
            },
            handleSubmit(e) {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        let data = {
                            appealMessage: this.form.getFieldValue('appealInfo'),
                        }
                        if(data.appealMessage !== ""){
                            this.cancelAppeal(data)
                            this.form.setFieldsValue({'appealInfo':''})
                        }

                    }
                });

            },
        }
    }

</script>

<style>
</style>
