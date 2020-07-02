<template>
    <a-modal
            :visible="updateRoomModalVisible"
            title="更新房间"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="房间类型" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写房间类型"
                        v-decorator="['roomType', { rules: [{ required: true, message: '请输入房间类型' }] }]"
                />
            </a-form-item>
            <a-form-item label="房间数量" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写房间数量"
                        v-decorator="['roomNum', { rules: [{ required: true, message: '请输入房间数量' }] }]"
                />
            </a-form-item>
            <a-form-item label="房价" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写房价"
                        v-decorator="['price', { rules: [{ required: true, message: '请输入房价' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateRoomModal',
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
                'updateRoomModalVisible',
                'currentHotelId'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateRoomModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_updateRoomModalVisible',
                'set_updateRoomParams'
            ]),
            ...mapActions([
                'updateRoom'
            ]),
            cancel() {
                this.set_updateRoomModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                        roomType: this.form.getFieldValue('roomType'),
                        price: Number(this.form.getFieldValue('price')),
                        total: Number(this.form.getFieldValue('roomNum')),
                        curNum: Number(this.form.getFieldValue('roomNum')),
                        hotelId: this.currentHotelId,
                        }
                        console.log(data)
                        this.set_updateRoomParams(data)
                        this.updateRoom()
                    }
                });
            },
        }
    }
</script>