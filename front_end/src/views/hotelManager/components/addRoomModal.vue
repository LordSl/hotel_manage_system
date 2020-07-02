<template>
    <a-modal
        :visible="addRoomModalVisible"
        title="录入客房"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <p>当前酒店：{{currentHotelInfo.name}}</p>
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-table
                :columns="columns"
                :dataSource="currentHotelInfo.rooms"
                :pagination="true"
            >
            </a-table>  <!-- 添加已有客房表单 -->
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
            <a-form-item label="上传房间图片" v-bind="formItemLayout">
                <a-upload
                        name="file"
                        :multiple="true"
                        :customRequest="customRequest"
                        :showUploadList = false
                >
                    <a-button type="primary"  icon="cloud-upload" ghost>
                        酒店图片上传
                    </a-button>
                </a-upload>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
const columns = [
    {
      title: '房间类型',
      dataIndex: 'roomType',
      key: 'roomType',
    },
    {
      title: '房间数量',
      key: 'curNum',
      dataIndex: 'curNum',
      scopedSlots:{customRender:'roomNum'}
    },
    {
      title: '房价',
      key: 'price',
      dataIndex: 'price',
      scopedSlots: { customRender: 'price'}
    },
  ];
export default {
    name: 'addRoomModal',
    data() {
        return {
            columns,
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
            'currentHotelId',
            'currentHotelInfo',//加入currentHotelInfo用于显示剩余房间
            'addRoomModalVisible',
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addRoomModal' });
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom',
            'set_addRoomModalVisible',
            'set_addRoomParams',

        ]),
        ...mapActions([
            'addRoom',
            'uploadRoomImg'
        ]),
        cancel() {
            this.set_addRoomModalVisible(false)
        },
        customRequest (data) {
            const formData = new FormData()
            formData.append('file', data.file)
            this.uploadRoomImg(formData)
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
                    this.addRoom(data).then(() => {
                        this.form.setFieldsValue({
                            roomType:'',
                            'price': '',
                            'roomNum': '',
                        })
                    })
                }
            });
        },
    }
}
</script>