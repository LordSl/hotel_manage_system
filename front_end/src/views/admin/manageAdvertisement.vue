<template>
    <div class="manageAD-wrapper">
        <a-tabs>
            <a-tab-pane tab="广告管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">


                    <a-upload
                        name="file"
                        :multiple="true"
                        :customRequest="customRequest"
                        :showUploadList = false
                    >
                        <a-button type="primary"  icon="cloud-upload" >
                            广告图片上传
                        </a-button>
                    </a-upload>



                </div>




<!--列表显示广告缩略-->
                <a-table
                        style="font-weight: bold"
                        :columns="columns"
                        :dataSource="adManageList"
                        bordered
                >

                    <span slot="img"  slot-scope="text">
                        <img style="height: 250px;width: 700px" :src="text"/>
                    </span>

                    <span slot="binded" slot-scope="record">
                        <p v-if="record.hotelId=== -1">未绑定</p>
                        <p v-if="record.hotelId!== -1">酒店 ID : {{record.hotelId}}</p>
                        <a-divider v-if="record.hotelId!== -1"></a-divider>
                        <p v-if="record.hotelId!== -1">酒店名称 : {{record.hotel.name}}</p>
                        <a-divider v-if="record.hotelId!== -1"></a-divider>
                        <p v-if="record.hotelId!== -1">酒店地址 : {{record.hotel.address}}</p>
                    </span>

                    <span slot="action" slot-scope="record">
                        <a-button type="danger"  icon="rest" style="font-weight: bold" @click="deleteAD(record.adId)">删除广告</a-button>
                        <a-divider></a-divider>
                        <a-button v-if="record.hotel == null" type="primary"  ghost  icon="bank"  @click="bindADHotel(record.adId)" style="font-weight: bold">绑定酒店</a-button>
                        <a-button v-if="record.hotel != null" type="primary"  ghost  icon="bank"  @click="bindADHotel(record.adId)" style="font-weight: bold">修改绑定</a-button>
                    </span>

                </a-table>
            </a-tab-pane>
        </a-tabs>
        <bind-a-d-hotel-modal></bind-a-d-hotel-modal>

    </div>

</template>

<script>

    import {mapActions, mapGetters, mapMutations} from "vuex";
    import bindADHotelModal from "./components/bindADHotelModal";

    const columns = [
        {
            title: '广告预览',
            dataIndex: 'adImgUrl',
            scopedSlots: { customRender: 'img' },
        },
        {
            title:"酒店信息",
            scopedSlots: { customRender: 'binded' },
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];

    export default {
        name: "manageAdvertisement",
        components: {
            bindADHotelModal
        },
        computed: {
            ...mapGetters([
                'adManageList',
            ])
        },
        methods: {
            ...mapMutations([
                'set_bindADHotelVisible',
                'set_currentADId'
            ]),
            ...mapActions([
                'getAdvertisementList',
                'deleteADById',
                'uploadADImg',
            ]),
            deleteAD(id){
                this.deleteADById(id);
            },
            customRequest (data) {
                const formData = new FormData()
                formData.append('file', data.file)
                this.uploadADImg(formData)
            },
            bindADHotel(id){
                this.set_bindADHotelVisible(true);
                this.set_currentADId(id);
            }
        },
        data(){
            return{
                columns
            }
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'manageAdvertisement' });
        },
        async mounted() {
            await this.getAdvertisementList()
        },

    }
</script>



<style scoped lang="less">
    .manageAD-wrapper {
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
    .manageAD-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>