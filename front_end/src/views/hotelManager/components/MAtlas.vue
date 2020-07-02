<template>
    <a-modal
            v-if="atlasVisible"
            :visible="true"
            :width="1260"
            @cancel="handleCancel"
            :footer="null"
    >
        <div class="atlas" :visible="atlasVisible">
            <div class="currentImg">
                <img alt="example"
                     :src=curHotelImg
                     slot="cover"
                     style="height: 420px;width: 640px"
                     referrerPolicy="no-referrer"
                     v-if="curHotelImg!=null"
                />
            </div>
            <div class="brief">
                <a-tabs >
                    <a-tab-pane tab="酒店官方" key="1">
                        <a-upload
                                name="file"
                                :multiple="true"
                                :customRequest="customRequest"
                                :showUploadList = false
                        >
                            <a-button type="primary"  icon="cloud-upload" style="margin-left: 300px;margin-bottom: 20px" ghost >
                                上传酒店图片
                            </a-button>
                        </a-upload>
                        <div class="hotelDetailImgs">

                            <div class="imgAndDel" v-for="item in hotelImgList" :key="item.id">
                                    <strong :key="item.id" v-if="item.custom===false"  style="margin-right:5px;cursor:pointer;" @click="removeHPic(item.id)">X</strong>
                                    <img :src="item.imgUrl" class="imgWindow"  :key="item.id" v-if="item.custom===false" @click="handleClick(item.imgUrl)">
                            </div>
                        </div>
                    </a-tab-pane>

                    <a-tab-pane tab="用户上传" key="2">
                        <div class="hotelDetailImgs">
                            <div class="imgAndDel" v-for="item in userImgList" :key="item.id">
                                <strong  v-if="item.custom===true"  style="margin-right:5px;cursor:pointer;" @click="removeHPic(item.id)">X</strong>
                                <img :src="item.imgUrl" class="imgWindow"    v-if="item.custom===true" @click="handleClick(item.imgUrl)">
                            </div>
                        </div>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </div>

    </a-modal>
</template>

<script>
    import { mapActions , mapGetters , mapMutations } from "vuex";
    export default {
        name: "MAtlas.vue",
        data(){
            return {
            }

        },
        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'userId',
                'atlasVisible',
                'imgList',
                'curHotelImg',
                'userImgList',
                'hotelImgList'
            ])
        },
        mounted() {
        },
        methods: {
            ...mapMutations([
                'set_currentHotelId' ,
                'set_atlasVisible',
                'set_curHotelImg'
            ]) ,
            ...mapActions([
                'getHotelById',
                'uploadHPic',
                'deleteHPic'
            ]) ,
            handleCancel(){
                this.set_atlasVisible(false)
            },
            handleClick(text){
                this.set_curHotelImg(text)
            },
            customRequest (data) {
                const formData = new FormData()
                formData.append('file', data.file)
                this.uploadHPic(formData)
            },
            /**
             * 调用这个接口删除hpic，data为hpic的id
             * @param data
             */
            removeHPic(data){
                const params={
                    id:data
                }
                this.deleteHPic(params)
            }
        }
    }
</script>

<style scoped lang="less">

    .atlas{
        display: flex;
        justify-content: space-between;
        .currentImg{
            height: 100%;
            width: 60%;
        }
        .brief{
            display: flex;
            flex-wrap: wrap;
            width: 40%;

            .hotelDetailImgs{
                display: flex;
                flex-wrap: wrap;
                overflow-y: scroll;
                height: 380px;
                justify-content: flex-start;
                align-content: flex-start;
                .imgWindow{
                    height: 80px;
                    width: 100px;
                    margin-right: 5px;
                    margin-bottom: 5px;
                }

            }
            .imgAndDel{
                display: flex;
                flex-direction: column;
                align-items: flex-end;
                margin-right: 10px;
            }

        }

    }

</style>